package ac.su.ctctct.controller;

import ac.su.ctctct.domain.Chat;
import ac.su.ctctct.domain.ChatMessage;
import ac.su.ctctct.domain.RequestMessageDto;
import ac.su.ctctct.domain.ResponseMessageDto;
import ac.su.ctctct.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessageSendingOperations template;
    private final ChatService chatService;

    // 채팅 리스트 반환
    @GetMapping("/chat/{roomNum}")
    public ResponseEntity<List<Chat>> getChatMessages(@PathVariable Long roomNum){
        List<Chat> chatMessages = chatService.getChatMessagesByRoomNum(roomNum);
        return ResponseEntity.ok().body(chatMessages);
    }

    // 메시지 송신 및 수신, /pub가 생략된 모습. 클라이언트 단에선 /pub/message로 요청
    @MessageMapping("/message")
    public ResponseEntity<Void> receiveMessage(@RequestBody RequestMessageDto chat) {
        Chat savedChat = chatService.saveChatMessage(chat);
        template.convertAndSend("/sub/chatroom/" + chat.getRoomId(), ResponseMessageDto.of(savedChat));
        return ResponseEntity.ok().build();
    }
}