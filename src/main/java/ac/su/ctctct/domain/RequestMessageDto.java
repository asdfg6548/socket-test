package ac.su.ctctct.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestMessageDto {
    private Long roomId;
    private Long writerId;
    private String content;
}