package com.example.uetstudy.library.Service;

import com.example.uetstudy.library.DTO.KnowledgeBlockDTO;
import com.example.uetstudy.library.Model.KnowledgeBlock;

import java.util.List;
import java.util.Optional;

public interface KnowledgeBlockService {
    KnowledgeBlock save(KnowledgeBlock knowledgeBlock);
    KnowledgeBlock update(KnowledgeBlock knowledgeBlock);
    List<KnowledgeBlock> findAll();
    Optional<KnowledgeBlock> findById(Long id);

    List<KnowledgeBlockDTO> findByDepartment(Long idDepartment);
    void deleteKnowledgeBlockById(Long id);
}
