package com.works.services;

import com.works.entities.Admin;
import com.works.entities.Note;
import com.works.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

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

    public boolean noteDelete(String nid) {
        boolean status = false;
        try {
            Long lNid = Long.parseLong(nid);
            Admin admin = control();
            if (admin != null) {
                Optional<Note> optionalNote = noteRepository.findByNidEquals(lNid);
                if (optionalNote.isPresent()) {
                    if ( optionalNote.get().getAid() == admin.getAid() ) {
                        noteRepository.deleteById(lNid);
                        return true;
                    }
                }

            }
        }catch (Exception ex) {}
        return status;
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
