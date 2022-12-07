package com.works.controllers;

import com.works.entities.Note;
import com.works.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    final NoteService noteService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Note> listPage = noteService.noteList(page);
        if ( listPage != null ) {
            int[] pages = new int[listPage.getTotalPages()];
            model.addAttribute("list", listPage );
            model.addAttribute("pages", pages);
        }
        return "dashboard";
    }

    @PostMapping("/noteAdd")
    public String noteAdd(Note note) {
        noteService.noteSave(note);
        return "redirect:/dashboard";
    }

    @GetMapping("/deleteNote/{nid}")
    public String deleteNote(@PathVariable String nid) {
        noteService.noteDelete(nid);
        return "redirect:/dashboard";
    }

}
