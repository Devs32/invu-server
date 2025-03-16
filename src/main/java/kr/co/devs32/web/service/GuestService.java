package kr.co.devs32.web.service;

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

    public Guest save(GuestRequestDto dto){
        dto.setUniqueName(RandomStringUtils.randomAlphanumeric(8));
        Guest guest = dto.toEntity();
        return guestRepository.save(guest);
    }

    public List<Guest> findAll(){
       return guestRepository.findAll();
    }

    public Guest findGuestByUniqueName(String name) {
        return guestRepository.findGuestByUniqueName(name);
    }

}
