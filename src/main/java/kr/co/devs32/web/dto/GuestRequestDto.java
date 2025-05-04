package kr.co.devs32.web.dto;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

import io.micrometer.common.util.StringUtils;
import kr.co.devs32.web.domain.AttendanceStatus;
import kr.co.devs32.web.domain.Guest;
import lombok.Data;

@Data
public class GuestRequestDto {
    private String guestName;
    private int attendNumber;
    private int childNumber;
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
                this.attendNumber,
                this.childNumber,
                this.nameNotes,
            (StringUtils.isNotBlank(getStatus())) ? AttendanceStatus.valueOf(getStatus()) : null,
                new Date(),
                null,
                this.uniqueName,
                this.invuId
        );
    }

}
