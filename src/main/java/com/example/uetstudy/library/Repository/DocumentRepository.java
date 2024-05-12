package com.example.uetstudy.library.Repository;


import com.example.uetstudy.library.DTO.DocumentDTO;
import com.example.uetstudy.library.Model.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {
    @Query("SELECT new com.example.uetstudy.library.DTO.DocumentDTO(dc.documentId,dc.documentTitle,dc.documentContent,dc.documentFile,dc.dateCreated,dc.isApproved) " +
            "FROM Document dc INNER JOIN dc.subject s WHERE s.subjectId = :subjectID and dc.isApproved = true")
    List<DocumentDTO> findDocumentBySubject(Long subjectID);
    @Query("SELECT new com.example.uetstudy.library.DTO.DocumentDTO(dc.documentId,dc.documentTitle,dc.documentContent,dc.documentFile,dc.dateCreated,dc.isApproved) " +
            "FROM Document dc INNER JOIN dc.subject s WHERE LOWER(dc.documentTitle) LIKE LOWER(CONCAT('%', :keyword, '%')) and dc.isApproved = true")
    List<DocumentDTO> findDocumentByName(String keyword);
    @Query("SELECT new com.example.uetstudy.library.DTO.DocumentDTO(dc.documentId,dc.documentTitle,dc.documentContent,dc.documentFile,dc.dateCreated,dc.isApproved) " +
            "FROM Document dc INNER JOIN dc.subject s WHERE dc.isApproved = true")
    Page<Document> findDocumentsByStatus(Pageable pageable);
    @Query("SELECT new com.example.uetstudy.library.DTO.DocumentDTO(dc.documentId,dc.documentTitle,dc.documentContent,dc.documentFile,dc.dateCreated,dc.isApproved) " +
            "FROM Document dc INNER JOIN dc.subject s WHERE dc.isApproved = false")
    Page<Document> findDocumentsByStatusFalse(Pageable pageable);
    @Query("SELECT new com.example.uetstudy.library.DTO.DocumentDTO(dc.documentId,dc.documentTitle,dc.documentContent,dc.documentFile,dc.dateCreated,dc.isApproved,dc.subject) " +
            "FROM Document dc WHERE dc.student.studentId = :studentId")
    List<DocumentDTO> findDocumentByStudentId(Long studentId);
}
