package com.example.uetstudy.library.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "knowledgeblock", uniqueConstraints = @UniqueConstraint(columnNames = "block_id"))
public class KnowledgeBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "block_id")
    private Long blockId;

    @Column(name = "block_name", length = 255)
    private String blockName;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
