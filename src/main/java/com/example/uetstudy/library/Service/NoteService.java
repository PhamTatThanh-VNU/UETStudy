package com.example.uetstudy.library.Service;

import com.example.uetstudy.library.DTO.NoteDTO;
import com.example.uetstudy.library.Model.Note;

import java.util.List;

public interface NoteService {
    Note save(Note note);

    Note update(Long id, Note note);

    List<NoteDTO> findNoteByStudentId(Long studentId);

    void deleteNoteById(Long id);

}
