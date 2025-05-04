package kr.co.devs32.web.service;

import jakarta.persistence.EntityNotFoundException;
import kr.co.devs32.web.domain.Invitation;
import kr.co.devs32.web.repository.InvitationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvitationService {
    private final InvitationRepository invitationRepository;

    /**
     * 초대장 조회 by ID
     */
    public Invitation findInvitation(String code) {
        return invitationRepository.findByCode(code)
                .orElseThrow(()-> new EntityNotFoundException("Invitation not found with code: "+ code));
    }
}
