package com.example.uetstudy.Controller;

import com.example.uetstudy.library.Model.Note;
import com.example.uetstudy.library.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class NoteController {

    @Autowired
    private NoteService noteService;


    @GetMapping("/delete-note")
    public String deleteNote(Long id) {
        noteService.deleteNoteById(id);
        return "redirect:/note";
    }

    @PostMapping("/update-note/{id}")
    public String update(@PathVariable Long id, Long noteId, @ModelAttribute("note") Note note) {
        note.setNoteId(noteId);
        noteService.update(id, note);
        return "redirect:/note";
    }


}
