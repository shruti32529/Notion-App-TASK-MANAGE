package mininotion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Notebook implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private List<Note> notes;

    public Notebook(String name) {
        this.name = name;
        this.notes = new ArrayList<>();
    }

    public String getName() { return name; }
    public List<Note> getNotes() { return notes; }
    public void addNote(Note note) { notes.add(note); }
    public void removeNoteById(int id) {
        notes.removeIf(note -> note.getId() == id);
    }
}
