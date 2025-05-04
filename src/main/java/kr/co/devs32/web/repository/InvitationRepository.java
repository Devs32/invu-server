package kr.co.devs32.web.repository;

import kr.co.devs32.web.domain.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    Optional<Invitation> findByCode(String code);
    @Query("SELECT i.id FROM Invitation i WHERE i.code = :code")
    Optional<Long> findIdByCode(@Param("code") String code);
}
