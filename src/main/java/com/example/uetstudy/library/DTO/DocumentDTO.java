package com.example.uetstudy.library.DTO;

import com.example.uetstudy.library.Model.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class DocumentDTO {
    private Long documentId;
    private String documentTitle;
    private String documentContent;
    private String documentFile;
    private Timestamp dateCreated;
    private boolean isApproved;
    private Subject subject;

    public DocumentDTO(Long documentId, String documentTitle, String documentContent, String documentFile, Timestamp dateCreated, boolean isApproved) {
        this.documentId = documentId;
        this.documentTitle = documentTitle;
        this.documentContent = documentContent;
        this.documentFile = documentFile;
        this.dateCreated = dateCreated;
        this.isApproved = isApproved;
    }
}
