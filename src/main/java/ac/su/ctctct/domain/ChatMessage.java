package ac.su.ctctct.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatMessage {

    private Long id;
    private Long senderId; // 추가된 필드
    private String name;
    private String message;

    public ChatMessage(Long id, Long senderId, String name, String message) {
        this.id = id;
        this.senderId = senderId;
        this.name = name;
        this.message = message;
    }
}