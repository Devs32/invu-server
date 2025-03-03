package kr.co.devs32.web.service;

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
    public Optional findInvitaion(Long id) {
        return invitationRepository.findById(id);
    }
}
