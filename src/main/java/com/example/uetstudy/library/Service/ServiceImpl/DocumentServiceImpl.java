package com.example.uetstudy.library.Service.ServiceImpl;

import com.example.uetstudy.library.DTO.DocumentDTO;
import com.example.uetstudy.library.Model.Document;
import com.example.uetstudy.library.Repository.DocumentRepository;
import com.example.uetstudy.library.Service.DocumentService;
import com.example.uetstudy.library.Service.Utils.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private final DocumentRepository documentRepository;
    private final FileStorageService fileStorageService;
    @Override
    public List<DocumentDTO> findDocumentBySubject(Long subjectID) {
        return documentRepository.findDocumentBySubject(subjectID);
    }

    @Override
    public Document save(MultipartFile file, Document document) {
        //Handle file name
        String fileName = fileStorageService.storeFile(file);
        //Handle Timestamp
        Document documentTemp = new Document();
        documentTemp.setDocumentTitle(document.getDocumentTitle());
        documentTemp.setDocumentContent(document.getDocumentContent());
        documentTemp.setDocumentFile(fileName);
        documentTemp.setDateCreated(document.getDateCreated());
        documentTemp.setSubject(document.getSubject());
        documentTemp.setStudent(document.getStudent());
        documentTemp.setApproved(false);
        return documentRepository.save(documentTemp);
    }

    @Override
    public Document update(Long id, MultipartFile file, Document document) {
        String fileNameUpdate = fileStorageService.storeFile(file);
        //Handle Timestamp
        Document documentUpdate = documentRepository.getReferenceById(id);
        documentUpdate.setDocumentTitle(document.getDocumentTitle());
        documentUpdate.setDocumentContent(document.getDocumentContent());
        documentUpdate.setDocumentFile(fileNameUpdate);
        documentUpdate.setDateCreated(document.getDateCreated());
        documentUpdate.setSubject(document.getSubject());
        return documentRepository.save(documentUpdate);
    }

    @Override
    public Page<Document> findDocumentsByStatus(Pageable pageable) {
        return documentRepository.findDocumentsByStatus(pageable);
    }

    @Override
    public Page<Document> findDocumentsByStatusFalse(Pageable pageable) {
        return documentRepository.findDocumentsByStatusFalse(pageable);
    }

    @Override
    public List<DocumentDTO> findDocumentByStudentId(Long studentId) {
        return documentRepository.findDocumentByStudentId(studentId);
    }

    @Override
    public void deleteDocumentById(Long id) {
        documentRepository.deleteById(id);
    }

    @Override
    public void approveDocumentById(Long id) {
        Document documentApproved = documentRepository.getReferenceById(id);
        documentApproved.setApproved(true);
        documentRepository.save(documentApproved);
    }

    @Override
    public List<DocumentDTO> findDocumentByName(String keyword) {
        return documentRepository.findDocumentByName(keyword);
    }
}
