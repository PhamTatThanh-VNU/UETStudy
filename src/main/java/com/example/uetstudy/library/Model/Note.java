package com.example.uetstudy.library.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "note", uniqueConstraints = @UniqueConstraint(columnNames = "note_id"))
public class Note{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private Long noteId;

    @Column(name = "note_title", length = 255)
    private String noteTitle;

    @Column(name = "note_text")
    private String noteText;


    @Column(name = "date_created")
    @CreationTimestamp
    private Timestamp dateCreated;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
