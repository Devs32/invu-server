package kr.co.devs32.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import kr.co.devs32.web.domain.AttendanceStatus;
import kr.co.devs32.web.domain.Guest;
import kr.co.devs32.web.domain.Invitation;
import kr.co.devs32.web.dto.GuestRequestDto;
import kr.co.devs32.web.service.GuestService;
import kr.co.devs32.web.service.InvitationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(InvitationController.class)
class InvitationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvitationService invitationService;

    @MockBean
    private GuestService guestService;


    @Test
    void getInvitation_Success() throws Exception {
        // Given
        Long id = 1L;
        String jsonValue = "{ \"date\": \"2025.03.06\" }";
        Invitation mockInvitation = new Invitation(id, "돌잔치", jsonValue, "CODE123", new Date(), new Date());

        when(invitationService.findInvitation(id)).thenReturn(mockInvitation);

        // When & Then
        mockMvc.perform(get("/api/v1/invitation/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data.title").value("돌잔치"))
                .andExpect(jsonPath("$.data.invuJson").value(jsonValue));
    }

    @Test
    void getInvitation_NotFound() throws Exception {
        // Given
        Long id = 999L;
        when(invitationService.findInvitation(id)).thenThrow(new EntityNotFoundException("Invitation not found with id: " + id));

        // When & Then
        mockMvc.perform(get("/api/v1/invitation/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Invitation not found with id: 999"));
    }

    @Test
    public void testSaveGuest() throws Exception {
        // Given
        GuestRequestDto dto = new GuestRequestDto();
        dto.setGuestName("John Doe");
        dto.setAttendCount(2);
        dto.setStatus("Y");

        Guest savedGuest = new Guest(1L, "John Doe", 2, "", AttendanceStatus.Y, new Date(), new Date(), "unique123");

        when(guestService.save(any(GuestRequestDto.class))).thenReturn(savedGuest);

        // When & Then
        mockMvc.perform(post("/api/v1/invitation/6/guests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.guestName").value("John Doe"))
                .andExpect(jsonPath("$.data.attendCount").value(2))
                .andExpect(jsonPath("$.data.uniqueName").value("unique123"));
    }

    @Test
    public void testGetGuestByUniqueName() throws Exception {
        // Given
        Guest guest = new Guest(1L, "Jane Doe", 1, "", AttendanceStatus.Y, new Date(), new Date(), "unique123");

        when(guestService.findGuestByUniqueName("unique123")).thenReturn(guest);

        // When & Then
        mockMvc.perform(get("/api/v1/invitation/6/guests/unique123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.guestName").value("Jane Doe"))
                .andExpect(jsonPath("$.data.uniqueName").value("unique123"));
    }

}

