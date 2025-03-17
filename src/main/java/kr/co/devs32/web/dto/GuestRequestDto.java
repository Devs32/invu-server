package kr.co.devs32.web.dto;

import kr.co.devs32.web.domain.AttendanceStatus;
import kr.co.devs32.web.domain.Guest;
import lombok.Data;

import java.util.Date;

@Data
public class GuestRequestDto {
    private String guestName;
    private int attendCount;
    private String nameNotes;
    private String status;
    private String uniqueName;

    public Guest toEntity() {
        return new Guest(
                null,
                this.guestName,
                this.attendCount,
                null,
                AttendanceStatus.valueOf(this.status),
                new Date(),
                null,
                this.uniqueName
        );
    }

}
