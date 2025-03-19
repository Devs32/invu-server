package kr.co.devs32.web.service;

import kr.co.devs32.web.domain.AttendanceStatus;
import kr.co.devs32.web.domain.Guest;
import kr.co.devs32.web.dto.GuestRequestDto;
import kr.co.devs32.web.repository.GuestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;




@Transactional // 트랜잭션 추가 (테스트 후 자동 롤백)
@ExtendWith(MockitoExtension.class)
class GuestServiceTest {

    @InjectMocks
    private GuestService guestService;

    @Mock
    private GuestRepository guestRepository;

//    @Test
//    public void testSaveGuest() {
//        // Given
//        GuestRequestDto dto = new GuestRequestDto();
//        dto.setGuestName("John Doe");
//        dto.setAttendCount(2);
//        dto.setStatus("Y");
//
//        Guest guest = new Guest(1L, "John Doe", 2, "", AttendanceStatus.YES, new Date(), new Date(), "unique123");
//
//        when(guestRepository.save(any(Guest.class))).thenReturn(guest);
//
//        // When
//        Guest savedGuest = guestService.save(dto);
//
//        // Then
//        assertNotNull(savedGuest);
//        assertEquals("John Doe", savedGuest.getGuestName());
//    }

//    @Test
//    public void testFindGuestByUniqueName() {
//        // Given
//        Guest guest = new Guest(null, "Jane Doe", 1, "", AttendanceStatus.YES, new Date(), new Date(), "unique123");
//
//        when(guestRepository.findGuestByUniqueName("unique123")).thenReturn(guest);
//
//        // When
//        Guest foundGuest = guestService.findGuestByUniqueName("unique123");
//
//        // Then
//        assertNotNull(foundGuest);
//        assertEquals("Jane Doe", foundGuest.getGuestName());
//    }
}