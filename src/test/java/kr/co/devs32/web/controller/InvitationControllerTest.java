package kr.co.devs32.web.controller;

import jakarta.persistence.EntityNotFoundException;
import kr.co.devs32.web.domain.Invitation;
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
}

