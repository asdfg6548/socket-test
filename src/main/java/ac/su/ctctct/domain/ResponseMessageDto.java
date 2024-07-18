package ac.su.ctctct.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseMessageDto {
    private Long chatId;
    private Long roomId;
    private Long senderId;
    private String content;
    private LocalDateTime timestamp;

    public static ResponseMessageDto of(Chat chat) {
        ResponseMessageDto dto = new ResponseMessageDto();
        dto.setChatId(chat.getChatId());
        dto.setRoomId(chat.getRoom().getRoomNum());
        dto.setSenderId(chat.getSender().getId());
        dto.setContent(chat.getContent());
        dto.setTimestamp(chat.getTimestamp());
        return dto;
    }
}