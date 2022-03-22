package com.example.worksearch.entities;

import com.example.worksearch.entities.enums.MIMEType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    private long id;

    @ManyToOne
    @JoinColumn(name = "applicant_id", nullable = false)
    @Getter @Setter
    private Applicant applicant;

    @Column(name = "content", nullable = false)
    @Getter @Setter
    private byte[] content;

    @Column(name = "mime", nullable = false)
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private MIMEType mime;

    @Column(name = "created_at", nullable = false)
    @Getter @Setter
    private LocalDate createdAt;

    @Column(name = "updated_at")
    @Getter @Setter
    private LocalDate updatedAt;
}
