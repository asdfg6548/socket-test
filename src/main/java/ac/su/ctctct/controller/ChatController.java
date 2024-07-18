package ac.su.ctctct.controller;

import ac.su.ctctct.domain.ChatMessage;
import ac.su.ctctct.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessageSendingOperations template;
    private final ChatService chatService;

    //메세지 송신 및 수신
    @MessageMapping("/message")
    public Mono<ResponseEntity<Void>> receiveMessage(@RequestBody RequestMessageDto chat) {
        return chatService.saveChatMessage(chat).flatMap(message -> {
            // 메시지를 해당 채팅방 구독자들에게 전송
            template.convertAndSend("/sub/chatroom/" + chat.getRoomId(),
                    ResponseMessageDto.of(message));
            return Mono.just(ResponseEntity.ok().build());
        });
    }
}