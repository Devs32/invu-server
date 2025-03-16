package kr.co.devs32.web.repository;

import kr.co.devs32.web.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    //게스트 특정 정보 조회
    Guest findGuestByUniqueName(String name);

}
