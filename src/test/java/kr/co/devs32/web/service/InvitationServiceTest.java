package kr.co.devs32.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import kr.co.devs32.web.domain.Invitation;
import kr.co.devs32.web.repository.InvitationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InvitationServiceTest {

    @Mock
    private InvitationRepository invitationRepository;
    @InjectMocks
    private InvitationService invitationService;


    @Test
    void findInvitation() throws JsonProcessingException {
        //given
//        Long id = 6L;
//        String jsonValue = "{ \"title\": \"안녕하세요\" }";
//        Invitation mock = new Invitation(id, "돌잔치", jsonValue, "", null, null);
//
//        when(invitationRepository.findById(id)).thenReturn(Optional.of(mock));
//
//        //when
//        Invitation foundInvitation = invitationService.findInvitation(id);
//
//        //then
//        assertNotNull(foundInvitation);
//        assertEquals(jsonValue, foundInvitation.getInvuJson());
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(foundInvitation.getInvuJson());
//        assertEquals("안녕하세요", jsonNode.get("title").asText());

    }

    @Test
    void findInvitation_notFound(){
//        // Given
//        Long id = 999L;
//
//        // When
//        when(invitationRepository.findById(id)).thenReturn(Optional.empty());
//
//        // Then
//        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
//                () -> invitationService.findInvitation(id));
//
//        assertEquals("Invitation not found with id: 999", exception.getMessage());
//
//        // Verify
//        verify(invitationRepository, times(1)).findById(id);
    }
}