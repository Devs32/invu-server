package kr.co.devs32.web.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "guest")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id")
    private Long id;

    @Column(name = "guest_name")
    private String guestName;
    @Column(name = "attend_number")
    private int attendCount;
    @Column(name = "name_notes")
    private String nameNotes;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "CHAR(1)", nullable = false)
    private AttendanceStatus status;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "unique_name")
    private String uniqueName;

}
