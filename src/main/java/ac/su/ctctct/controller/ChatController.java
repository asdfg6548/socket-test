package ac.su.ctctct.controller;

import ac.su.ctctct.domain.ChatMessage;
import ac.su.ctctct.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
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
    @GetMapping("/chat/{roomId}")
    public List<ChatMessage> getChatMessages(@PathVariable Long roomId) {
        return chatService.getChatMessagesByRoomId(roomId);
    }


    // 메시지 송신 및 수신, /pub가 생략된 모습. 클라이언트 단에선 /pub/chat/{roomId}로 요청
    public void receiveMessage(@DestinationVariable Long roomId, @Payload ChatMessage chat) {
        chatService.saveChatMessage(roomId, chat);
        template.convertAndSend("/sub/chatroom/" + roomId, chat);
    }

}