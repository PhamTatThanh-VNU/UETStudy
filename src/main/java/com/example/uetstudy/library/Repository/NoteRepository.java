package com.example.uetstudy.library.Repository;


import com.example.uetstudy.library.DTO.NoteDTO;
import com.example.uetstudy.library.Model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {
    @Query("SELECT new com.example.uetstudy.library.DTO.NoteDTO(nt.noteId,nt.noteTitle,nt.noteText,nt.dateCreated) " +
            "FROM Note nt WHERE nt.student.studentId = :studentId")
    List<NoteDTO> findNoteByStudentId(Long studentId);
}
