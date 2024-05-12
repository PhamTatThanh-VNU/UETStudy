package com.example.uetstudy.library.Service.ServiceImpl;

import com.example.uetstudy.library.DTO.KnowledgeBlockDTO;
import com.example.uetstudy.library.Model.KnowledgeBlock;
import com.example.uetstudy.library.Repository.KnowledgeBlockRepository;
import com.example.uetstudy.library.Service.KnowledgeBlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KnowledgeBlocklmpl implements KnowledgeBlockService {
    @Autowired
    private final KnowledgeBlockRepository knowledgeBlockRepository;

    @Override
    public List<KnowledgeBlock> findAll() {
        return knowledgeBlockRepository.findAll();
    }
    @Override
    public Optional<KnowledgeBlock> findById(Long id) {
        return knowledgeBlockRepository.findById(id);
    }
    @Override
    public List<KnowledgeBlockDTO> findByDepartment(Long idDepartment) {
        return knowledgeBlockRepository.findByDepartment(idDepartment);
    }

    // Create KnowledgeBlock
    @Override
    public KnowledgeBlock save(KnowledgeBlock knowledgeBlock) {
        KnowledgeBlock knowledgeBlockTemp = new KnowledgeBlock();
        knowledgeBlockTemp.setBlockName(knowledgeBlock.getBlockName());
        knowledgeBlockTemp.setDepartment(knowledgeBlock.getDepartment());
        return knowledgeBlockRepository.save(knowledgeBlockTemp);
    }

    @Override
    public KnowledgeBlock update(KnowledgeBlock knowledgeBlock) {
        KnowledgeBlock knowledgeBlockUpdate = knowledgeBlockRepository.getReferenceById(knowledgeBlock.getBlockId());
        knowledgeBlockUpdate.setBlockName(knowledgeBlock.getBlockName());
        knowledgeBlockUpdate.setDepartment(knowledgeBlock.getDepartment());
        return knowledgeBlockRepository.save(knowledgeBlockUpdate);
    }

    @Override
    public void deleteKnowledgeBlockById(Long id) {
        try {
            knowledgeBlockRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Can't delete knowledgeBlock , you have to delete every subject with this knowledgeBlock id.");
        } catch (Exception e) {
            throw new RuntimeException("Can't delete knowledgeBlock", e);
        }


    }
}
