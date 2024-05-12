package com.example.uetstudy.library.Repository;

import com.example.uetstudy.library.DTO.KnowledgeBlockDTO;
import com.example.uetstudy.library.Model.KnowledgeBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KnowledgeBlockRepository extends JpaRepository<KnowledgeBlock,Long> {
    @Query("SELECT NEW com.example.uetstudy.library.DTO.KnowledgeBlockDTO(k.blockId, k.blockName) FROM KnowledgeBlock k INNER JOIN k.department d WHERE d.departmentId = :departmentId")
    List<KnowledgeBlockDTO> findByDepartment(Long departmentId);
}
