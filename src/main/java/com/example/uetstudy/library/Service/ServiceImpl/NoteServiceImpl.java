package com.example.uetstudy.library.Service.ServiceImpl;

import com.example.uetstudy.library.DTO.NoteDTO;
import com.example.uetstudy.library.Model.Note;
import com.example.uetstudy.library.Repository.NoteRepository;
import com.example.uetstudy.library.Service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    @Autowired
    private final NoteRepository noteRepository;



    @Override
    public Note save(Note note) {
        Note noteTemp = new Note();
        noteTemp.setNoteTitle(note.getNoteTitle());
        noteTemp.setNoteText(note.getNoteText());
        noteTemp.setDateCreated(note.getDateCreated());
        noteTemp.setStudent(note.getStudent());
        return noteRepository.save(noteTemp);
    }

    @Override
    public Note update(Long id, Note note) {
        Note noteUpdate = noteRepository.getReferenceById(id);
        noteUpdate.setNoteTitle(note.getNoteTitle());
        noteUpdate.setNoteText(note.getNoteText());
        return noteRepository.save(noteUpdate);
    }

    @Override
    public List<NoteDTO> findNoteByStudentId(Long studentId) {
        return noteRepository.findNoteByStudentId(studentId);
    }

    @Override
    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }
}
