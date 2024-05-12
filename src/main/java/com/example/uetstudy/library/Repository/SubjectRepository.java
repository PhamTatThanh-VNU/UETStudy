package com.example.uetstudy.library.Repository;

import com.example.uetstudy.library.DTO.SubjectDTO;
import com.example.uetstudy.library.Model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
    @Query("SELECT new com.example.uetstudy.library.DTO.SubjectDTO(sj.subjectId, sj.subjectName) FROM Subject sj " +
            "INNER JOIN KnowledgeBlock k ON k.blockId = sj.knowledgeBlock.blockId " +
            "INNER JOIN Department d ON d.departmentId = k.department.departmentId " +
            "WHERE k.blockId = :blockId")
    List<SubjectDTO> filterSubject(Long blockId);
    @Query("select new com.example.uetstudy.library.DTO.SubjectDTO(sj.subjectId,sj.subjectName) from Subject sj ")
    List<SubjectDTO> findAllSubjectDTO();

}
