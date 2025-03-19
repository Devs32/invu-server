package kr.co.devs32.web.service;

import jakarta.persistence.EntityNotFoundException;
import kr.co.devs32.web.domain.Guest;
import kr.co.devs32.web.dto.GuestRequestDto;
import kr.co.devs32.web.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestService {
    private final GuestRepository guestRepository;

    public void save(GuestRequestDto dto){
        Guest guest = dto.toEntity();
        guestRepository.save(guest);
    }

    public List<Guest> findAllByInvuId(Long invuId){
       List<Guest> guests = guestRepository.findAllByInvuId(invuId);
       if(guests.isEmpty()) {
           throw new EntityNotFoundException("해당 invuId로 조회된 리스트가 없습니다.");
       }
       return guests;
    }

    public Guest findGuestByInvuIdAndUniqueName(Long invuId, String name) {
        return guestRepository.findGuestByInvuIdAndUniqueName(invuId, name)
                .orElseThrow(()-> new EntityNotFoundException("GuestInfo not found with UniqueName: "+ name));
    }

}
