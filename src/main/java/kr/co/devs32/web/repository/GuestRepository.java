package kr.co.devs32.web.repository;

import kr.co.devs32.web.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    //게스트 리스트 조회
    List<Guest> findAllByInvuId(Long invuId);

    //게스트 특정 정보 조회
    Optional<Guest> findGuestByInvuIdAndUniqueName(Long invuId, String name);

}
