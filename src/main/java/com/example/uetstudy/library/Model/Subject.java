package com.example.uetstudy.library.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subject", uniqueConstraints = @UniqueConstraint(columnNames = "subject_id"))
public class Subject{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "subject_name", length = 255)
    private String subjectName;

    @ManyToOne
    @JoinColumn(name = "block_id")
    private KnowledgeBlock knowledgeBlock;
}
