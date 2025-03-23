package kr.co.devs32.web.service;

import jakarta.persistence.EntityNotFoundException;
import kr.co.devs32.web.domain.GuestBook;
import kr.co.devs32.web.dto.GuestBookRequestDto;
import kr.co.devs32.web.repository.GuestBookRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestBookService {
    private final GuestBookRepository guestBookRepository;

    public void save(GuestBookRequestDto dto){
        guestBookRepository.save(dto.toEntity());
    }

    public List<GuestBook> findAllGuestBookByInvuId(Long id) {
       List<GuestBook> bookList = guestBookRepository.findAllGuestBookByInvuId(id);
       if(bookList.isEmpty()){
           throw new EntityNotFoundException("해당 invuId로 조회된 리스트가 없습니다.");
       }
        return bookList;
    }

    public GuestBook findGuestBookByInvuIdAndId(Long ivnuId, Long bookId) {
        return guestBookRepository.findGuestBookByInvuIdAndId(ivnuId, bookId)
                .orElseThrow(()-> new EntityNotFoundException("GuestBook not found with BookId: "+ bookId));
    }
}
