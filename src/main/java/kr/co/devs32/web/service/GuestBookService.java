package kr.co.devs32.web.service;

import kr.co.devs32.web.dto.GuestBookRequestDto;
import kr.co.devs32.web.repository.GuestBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuestBookService {
    private final GuestBookRepository guestBookRepository;

    public void save(GuestBookRequestDto dto){
        guestBookRepository.save(dto.toEntity());
    }
}
