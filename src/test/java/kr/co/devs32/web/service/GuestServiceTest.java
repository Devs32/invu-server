package kr.co.devs32.web.service;

import jakarta.persistence.EntityNotFoundException;
import kr.co.devs32.web.domain.Guest;
import kr.co.devs32.web.dto.GuestRequestDto;
import kr.co.devs32.web.repository.GuestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GuestServiceTest {
    @Mock
    private GuestRepository guestRepository;

    @InjectMocks
    private GuestService guestService;

    private GuestRequestDto guestRequestDto;
    private Guest guest;

    @BeforeEach
    void setUp() {
        guestRequestDto = new GuestRequestDto();
        guestRequestDto.setInvuId(6L);
        guestRequestDto.setGuestName("Test Guest");
        guestRequestDto.setAttendCount(2);
        guestRequestDto.setNameNotes("테스터1,테스터2");
        guestRequestDto.setStatus("YES");

        guest = guestRequestDto.toEntity();
    }

    @Test
    void save() {
        // when
        guestService.save(guestRequestDto);

        // then
        verify(guestRepository, times(1)).save(any(Guest.class));
        assertThat(guestRequestDto.getUniqueName()).hasSize(8); // 랜덤 uniqueName이 8자리로 생성됐는지 확인

    }

    @Test
    void findAllByInvuId_success() {
        // given
        when(guestRepository.findAllByInvuId(6L)).thenReturn(List.of(guest));

        // when
        List<Guest> result = guestService.findAllByInvuId(6L);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getInvuId()).isEqualTo(6L);
    }

    @Test
    void findAllByInvuId_notFound() {
        // given
        when(guestRepository.findAllByInvuId(6L)).thenReturn(Collections.emptyList());

        // when / then
        assertThatThrownBy(() -> guestService.findAllByInvuId(6L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("해당 invuId로 조회된 리스트가 없습니다.");
    }
    @Test
    void findGuestByInvuIdAndUniqueName_success() {

        // given
        Guest testGuest = guestRequestDto.toEntity();  // uniqueName 자동 세팅됨
        when(guestRepository.findGuestByInvuIdAndUniqueName(1L, testGuest.getUniqueName()))
                .thenReturn(Optional.of(testGuest));

        // when
        Guest result = guestService.findGuestByInvuIdAndUniqueName(1L, testGuest.getUniqueName());

        // then
        assertThat(result).isNotNull();
        assertThat(result.getUniqueName()).isEqualTo(testGuest.getUniqueName());
    }

    @Test
    void findGuestByInvuIdAndUniqueName_notFound() {
        // given
        when(guestRepository.findGuestByInvuIdAndUniqueName(1L, "NOT_EXIST"))
                .thenReturn(Optional.empty());

        // when / then
        assertThatThrownBy(() -> guestService.findGuestByInvuIdAndUniqueName(1L, "NOT_EXIST"))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("GuestInfo not found with UniqueName: NOT_EXIST");
    }
}