package kr.co.devs32.web.dto;

import kr.co.devs32.web.domain.Guest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GuestRequestDtoTest {

    private GuestRequestDto guestRequestDto;

    @BeforeEach
    void setUp() {
        guestRequestDto = new GuestRequestDto();
        guestRequestDto.setGuestName("홍길동");
        guestRequestDto.setAttendCount(2);
        guestRequestDto.setNameNotes("동행1,동행2");
        guestRequestDto.setStatus("YES");
        guestRequestDto.setInvuId(123L);
    }
    @Test
    void toEntity() {
        // given
        guestRequestDto.setUniqueName(null);  // uniqueName이 null인 경우

        // when
        Guest guest = guestRequestDto.toEntity();

        // then
        assertThat(guest.getUniqueName()).hasSize(8);
    }
}