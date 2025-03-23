package kr.co.devs32.web.dto;

import kr.co.devs32.web.domain.GuestBook;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class GuestBookRequestDto {

    private String guestName;
    private String password;
    private int isVisible;
    private String message;
    private Long invuId;

    public GuestBook toEntity(){
        return GuestBook.builder()
                .guestName(this.guestName)
                .password(this.password)
                .isVisible(1)
                .message(this.message)
                .invuId(this.invuId)
                .build();
    }
}
