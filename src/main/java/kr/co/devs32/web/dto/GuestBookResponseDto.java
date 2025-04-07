package kr.co.devs32.web.dto;

import kr.co.devs32.web.domain.GuestBook;

import java.time.LocalDateTime;

public class GuestBookResponseDto {
    private Long id;
    private String guestName;
    private String message;
    private int isVisible;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private Long invuId;

    // Constructor to convert from entity
    public GuestBookResponseDto(GuestBook guestBook) {
        this.id = guestBook.getId();
        this.guestName = guestBook.getGuestName();
        this.message = guestBook.getMessage();
        this.isVisible = guestBook.getIsVisible();
        this.created_at = guestBook.getCreated_at();
        this.updated_at = guestBook.getUpdated_at();
        this.invuId = guestBook.getInvuId();
    }
}
