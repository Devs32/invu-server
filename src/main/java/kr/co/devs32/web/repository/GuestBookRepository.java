package kr.co.devs32.web.repository;

import kr.co.devs32.web.domain.GuestBook;
import kr.co.devs32.web.service.GuestBookService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuestBookRepository extends JpaRepository<GuestBook, Long> {
    //방명록 리스트 조회
   List<GuestBook> findAllGuestBookByInvuId(Long id);

   //특정 방명록 조회
   Optional<GuestBook> findGuestBookByInvuIdAndId(Long ivnuId, Long id);
}
