package com.example.uetstudy.library.Service;

import com.example.uetstudy.library.DTO.DocumentDTO;
import com.example.uetstudy.library.Model.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {
    Document save(MultipartFile multipartFile,Document document);
    Document update(Long id, MultipartFile multipartFile,Document document);
    List<DocumentDTO> findDocumentBySubject(Long subjectID);
    Page<Document> findDocumentsByStatus(Pageable pageable);
    Page<Document> findDocumentsByStatusFalse(Pageable pageable);
    List<DocumentDTO> findDocumentByName(String keyword);
    List<DocumentDTO> findDocumentByStudentId(Long studentId);
    void deleteDocumentById(Long id);
    void approveDocumentById(Long id);
}
