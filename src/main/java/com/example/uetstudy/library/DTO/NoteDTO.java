package com.example.uetstudy.library.DTO;

import com.example.uetstudy.library.Model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class NoteDTO {
    private Long noteId;
    private String noteTitle;
    private String noteText;
    private Timestamp dateCreated;
    private Student student;

    public NoteDTO(Long noteId, String noteTitle, String noteText, Timestamp dateCreated) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteText = noteText;
        this.dateCreated = dateCreated;
    }
}
