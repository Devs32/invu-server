package kr.co.devs32.web.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kr.co.devs32.web.domain.AttendanceStatus;
import kr.co.devs32.web.domain.Guest;

class GuestRequestDtoTest {

    private GuestRequestDto guestRequestDto;

    @BeforeEach
    void setUp() {
        guestRequestDto = new GuestRequestDto();
        guestRequestDto.setGuestName("홍길동");
        guestRequestDto.setAttendNumber(2);
        guestRequestDto.setNameNotes("동행1,동행2");
        guestRequestDto.setStatus("YES");
        guestRequestDto.setInvuId(123L);
    }
    @Test
    void toEntity_withNullUniqueName_generatesRandomUniqueName() {
        // given
        guestRequestDto.setUniqueName(null);  // uniqueName이 null인 경우

        // when
        Guest guest = guestRequestDto.toEntity();

        // then
        assertThat(guest.getUniqueName()).hasSize(8);  // 랜덤 uniqueName이 8자리로 생성되었는지 확인
    }

    @Test
    void toEntity_withEmptyUniqueName_generatesRandomUniqueName() {
        // given
        guestRequestDto.setUniqueName("");  // uniqueName이 빈 문자열인 경우

        // when
        Guest guest = guestRequestDto.toEntity();

        // then
        assertThat(guest.getUniqueName()).hasSize(8);  // 랜덤 uniqueName이 8자리로 생성되었는지 확인
    }

    @Test
    void toEntity_withProvidedUniqueName_usesProvidedUniqueName() {
        // given
        String providedUniqueName = "ABC12345";
        guestRequestDto.setUniqueName(providedUniqueName);  // uniqueName이 제공된 경우

        // when
        Guest guest = guestRequestDto.toEntity();

        // then
        assertThat(guest.getUniqueName()).isEqualTo(providedUniqueName);  // 제공된 uniqueName이 사용되었는지 확인
    }

    @Test
    void toEntity_correctlyMapsFields() {
        // given
        guestRequestDto.setUniqueName("ABC12345");

        // when
        Guest guest = guestRequestDto.toEntity();

        // then
        assertThat(guest.getGuestName()).isEqualTo("홍길동");
        assertThat(guest.getAttendNumber()).isEqualTo(2);
        assertThat(guest.getNameNotes()).isNull();  // nameNotes는 null로 설정되어 있음
        assertThat(guest.getStatus()).isEqualTo(AttendanceStatus.YES);  // AttendanceStatus enum 값이 제대로 매핑되었는지
        assertThat(guest.getUniqueName()).isEqualTo("ABC12345");
        assertThat(guest.getInvuId()).isEqualTo(123L);
    }
}
