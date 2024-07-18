package ac.su.ctctct.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
public class ResponseChatRoomDto {

    private Long id;
    private String title;
    private LocalDateTime createDate;

    public static ResponseChatRoomDto of(Room chatRoom) {
        return new ResponseChatRoomDto(chatRoom.getRoomNum(), chatRoom.getRoomTitle(),
            chatRoom.getRoomCreateAt());
    }
}