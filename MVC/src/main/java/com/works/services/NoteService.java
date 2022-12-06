package com.works.services;

import com.works.entities.Admin;
import com.works.entities.Note;
import com.works.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    final NoteRepository noteRepository;
    final HttpServletRequest req;

    public Note noteSave(Note note) {
        try {
            Admin admin = control();
            if (admin != null) {
                note.setAid(admin.getAid());
                noteRepository.save(note);
            }

            return note;
        }catch (Exception ex) {
            return null;
        }
    }

    public List<Note> noteList() {
        Admin admin = control();
        if (admin != null) {
            return noteRepository.findByAidEquals(admin.getAid());
        }
        return null;
    }

    public Admin control() {
        Object obj = req.getSession().getAttribute("admin");
        if (obj != null) {
            Admin adm = (Admin) obj;
            return adm;
        }
        return null;
    }

}
