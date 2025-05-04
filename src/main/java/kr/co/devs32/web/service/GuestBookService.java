package kr.co.devs32.web.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import kr.co.devs32.web.domain.GuestBook;
import kr.co.devs32.web.dto.GuestBookRequestDto;
import kr.co.devs32.web.dto.GuestBookResponseDto;
import kr.co.devs32.web.handler.CustomException.BusinessException;
import kr.co.devs32.web.repository.GuestBookRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GuestBookService {
    private final GuestBookRepository guestBookRepository;

    public void save(GuestBookRequestDto dto){
        guestBookRepository.save(dto.toEntity());
    }

    public List<GuestBookResponseDto> findAllGuestBookByInvuId(Long id) {
       List<GuestBook> bookList = guestBookRepository.findAllGuestBookByInvuIdAndIsVisible(id, 1);
       if(bookList.isEmpty()){
           throw new EntityNotFoundException("해당 invuId로 조회된 리스트가 없습니다.");
       }
        return bookList.stream()
            .sorted((a, b) -> b.getCreated_at().compareTo(a.getCreated_at()))
            .map(GuestBookResponseDto::new)
            .toList();
    }

    public GuestBookResponseDto findGuestBookByInvuIdAndId(Long invuId, Long bookId) {
        GuestBook guestBook = guestBookRepository.findGuestBookByInvuIdAndIdAndIsVisible(invuId, bookId, 1)
                .orElseThrow(()-> new EntityNotFoundException("GuestBook not found with BookId: "+ bookId));
        return new GuestBookResponseDto(guestBook);
    }

    private GuestBook findGuestBookEntityByInvuIdAndId(Long invuId, Long bookId) {
        return guestBookRepository.findGuestBookByInvuIdAndIdAndIsVisible(invuId, bookId, 1)
                .orElseThrow(()-> new EntityNotFoundException("GuestBook not found with BookId: "+ bookId));
    }

    @Transactional
    public void updateGuestBookMessage(Long invuId, Long bookId, GuestBookRequestDto dto){
        //방명록 조회
        GuestBook guestBook = findGuestBookEntityByInvuIdAndId(invuId, bookId);

        //비번이 다른 경우
        if(!guestBook.getPassword().equals(dto.getPassword())){
            throw new BusinessException("비밀번호가 일치하지 않습니다.");
        }

        //업데이트
        guestBook.setMessage(dto.getMessage());
        guestBook.setUpdated_at(LocalDateTime.now());

    }

    @Transactional
    public void deleteGuestBook(Long invuId, Long bookId, GuestBookRequestDto dto){
        //방명록 조회
        GuestBook guestBook = findGuestBookEntityByInvuIdAndId(invuId, bookId);

        //비번이 다른 경우
        if(!guestBook.getPassword().equals(dto.getPassword())){
            throw new BusinessException("비밀번호가 일치하지 않습니다.");
        }

        //업데이트
        guestBook.setIsVisible(0);
        guestBook.setUpdated_at(LocalDateTime.now());
    }
}
