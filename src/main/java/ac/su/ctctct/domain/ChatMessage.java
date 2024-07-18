package ac.su.ctctct.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessage {
    private Long roomId;
    private Long senderId;
    private String senderNickname;
    private String content;

    public ChatMessage(Long roomId, Long senderId, String senderNickname, String content) {
        this.roomId = roomId;
        this.senderId = senderId;
        this.senderNickname = senderNickname;
        this.content = content;
    }
}