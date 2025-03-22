package kr.co.devs32.web.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "guestbook")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuestBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guestbook_id")
    private Long id;
    @Column(name = "guest_name")
    private String guestName;

    @Column(name = "message")
    private String message;

    @Column(name = "is_visible")
    private int isVisible;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    @Column(name = "password")
    private String password;

    @Column(name = "invu_id")
    private Long invuId;
}
