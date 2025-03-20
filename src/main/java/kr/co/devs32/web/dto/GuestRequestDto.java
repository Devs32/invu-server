package kr.co.devs32.web.dto;

import kr.co.devs32.web.domain.AttendanceStatus;
import kr.co.devs32.web.domain.Guest;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;

@Data
public class GuestRequestDto {
    private String guestName;
    private int attendCount;
    private String nameNotes;
    private String status;
    private String uniqueName;
    private Long invuId;

    public Guest toEntity() {
        if (this.uniqueName == null || this.uniqueName.isEmpty()) {
            this.uniqueName = RandomStringUtils.randomAlphanumeric(8);  // Apache commons
        }
        return new Guest(
                null,
                this.guestName,
                this.attendCount,
                null,
                AttendanceStatus.valueOf(this.status),
                new Date(),
                null,
                this.uniqueName,
                this.invuId
        );
    }

}
