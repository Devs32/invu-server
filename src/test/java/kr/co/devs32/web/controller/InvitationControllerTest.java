package kr.co.devs32.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.devs32.web.domain.Guest;
import kr.co.devs32.web.dto.GuestRequestDto;
import kr.co.devs32.web.service.*;

@WebMvcTest(InvitationController.class)
class InvitationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvitationService invitationService;

    @MockBean
    private GuestService guestService;

    @MockBean
    private GuestBookService guestBookService;


    @Test
    void getInvitation_Success() throws Exception {
        // Given
//        Long id = 1L;
//        String jsonValue = "{ \"date\": \"2025.03.06\" }";
//        Invitation mockInvitation = new Invitation(id, "돌잔치", jsonValue, "CODE123", new Date(), new Date());
//
//        when(invitationService.findInvitation(id)).thenReturn(mockInvitation);
//
//        // When & Then
//        mockMvc.perform(get("/api/v1/invitation/{id}", id)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.status").value(200))
//                .andExpect(jsonPath("$.message").value("Success"))
//                .andExpect(jsonPath("$.data.title").value("돌잔치"))
//                .andExpect(jsonPath("$.data.invuJson").value(jsonValue));
    }

    @Test
    void getInvitation_NotFound() throws Exception {
        // Given
//        Long id = 999L;
//        when(invitationService.findInvitation(id)).thenThrow(new EntityNotFoundException("Invitation not found with id: " + id));
//
//        // When & Then
//        mockMvc.perform(get("/api/v1/invitation/{id}", id)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.message").value("Invitation not found with id: 999"));
    }

    @Test
    void saveGuest() throws Exception {
        // given
        Long invitationId = 6L;
        GuestRequestDto dto = new GuestRequestDto();
        dto.setGuestName("홍길동");
        dto.setAttendNumber(2);
        dto.setNameNotes("동행1,동행2");
        dto.setStatus("YES");

        String requestBody = new ObjectMapper().writeValueAsString(dto);

        // when & then
        mockMvc.perform(post("/api/v1/invitation/{id}/guests", invitationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Created"));

        verify(guestService, times(1)).save(any(GuestRequestDto.class));
    }

    @Test
    void getGuestsList() throws Exception {
        // given
        Long invitationId = 6L;
        List<Guest> guestList = List.of(
                Guest.builder().guestName("홍길동").build(),
                Guest.builder().guestName("김철수").build()
        );

        when(guestService.findAllByInvuId(invitationId)).thenReturn(guestList);

        // when & then
        mockMvc.perform(get("/api/v1/invitation/{id}/guests", invitationId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].guestName").value("홍길동"))
                .andExpect(jsonPath("$.data[1].guestName").value("김철수"));

        verify(guestService, times(1)).findAllByInvuId(invitationId);
    }

    @Test
    void getGuestInfo() throws Exception {
        // given
        Long invitationId = 6L;
        String uniqueName = "ABC12345";
        Guest guest = Guest.builder().guestName("홍길동").uniqueName(uniqueName).build();

        when(guestService.findGuestByInvuIdAndUniqueName(invitationId, uniqueName)).thenReturn(guest);

        // when & then
        mockMvc.perform(get("/api/v1/invitation/{id}/guests/{name}", invitationId, uniqueName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.guestName").value("홍길동"));

        verify(guestService, times(1)).findGuestByInvuIdAndUniqueName(invitationId, uniqueName);
    }



}

