package mininotion;

import java.util.*;

public class NoteManager {
    private Map<String, Notebook> notebooks;
    private Map<String, List<Note>> tagMap;
    private int createdCount = 0, updatedCount = 0, deletedCount = 0;

    public NoteManager() {
        notebooks = new HashMap<>();
        tagMap = new HashMap<>();
    }

    public void createNotebook(String name) {
        if (!notebooks.containsKey(name)) {
            notebooks.put(name, new Notebook(name));
            System.out.println("Notebook created: " + name);
        }
    }

    public void addNote(String notebookName, String title, String content, Set<String> tags) {
        if (!notebooks.containsKey(notebookName)) {
            System.out.println("Notebook not found.");
            return;
        }
        Note note = new Note(title, content, tags);
        notebooks.get(notebookName).addNote(note);
        for (String tag : tags) {
            tagMap.putIfAbsent(tag, new ArrayList<>());
            tagMap.get(tag).add(note);
        }
        createdCount++;
        System.out.println("Note added to notebook: " + notebookName);
    }

    public void viewAllNotes(String notebookName) {
        if (!notebooks.containsKey(notebookName)) {
            System.out.println("Notebook not found.");
            return;
        }
        List<Note> notes = notebooks.get(notebookName).getNotes();
        customSort(notes);
        for (Note note : notes) {
            note.display();
        }
    }

    public void updateNote(String notebookName, int noteId, String newTitle, String newContent) {
        if (!notebooks.containsKey(notebookName)) {
            System.out.println("Notebook not found.");
            return;
        }
        for (Note note : notebooks.get(notebookName).getNotes()) {
            if (note.getId() == noteId) {
                note.setTitle(newTitle);
                note.setContent(newContent);
                updatedCount++;
                System.out.println("Note updated.");
                return;
            }
        }
        System.out.println("Note not found.");
    }

    public void deleteNote(String notebookName, int noteId) {
        if (notebooks.containsKey(notebookName)) {
            notebooks.get(notebookName).removeNoteById(noteId);
            deletedCount++;
            System.out.println("Note deleted.");
        } else {
            System.out.println("Notebook not found.");
        }
    }

    public void searchByTag(String tag) {
        if (!tagMap.containsKey(tag)) {
            System.out.println("No notes found for tag: " + tag);
            return;
        }
        for (Note note : tagMap.get(tag)) {
            note.display();
        }
    }

    public void undoNote(String notebookName, int noteId) {
        if (!notebooks.containsKey(notebookName)) {
            System.out.println("Notebook not found.");
            return;
        }
        for (Note note : notebooks.get(notebookName).getNotes()) {
            if (note.getId() == noteId) {
                note.undo();
                System.out.println("Undo successful.");
                return;
            }
        }
        System.out.println("Note not found.");
    }

    public void pinNote(String notebookName, int noteId) {
        if (!notebooks.containsKey(notebookName)) {
            System.out.println("Notebook not found.");
            return;
        }
        for (Note note : notebooks.get(notebookName).getNotes()) {
            if (note.getId() == noteId) {
                note.setPinned(true);
                System.out.println("Note pinned.");
                return;
            }
        }
        System.out.println("Note not found.");
    }

    public void sessionSummary() {
        System.out.println("Note Session Summary:");
        System.out.println("Notes Created: " + createdCount + ", Updated: " + updatedCount +
                ", Deleted: " + deletedCount);
    }

    private void customSort(List<Note> notes) {
        // Bubble sort: pinned first, then most recent timestamps
        for (int i = 0; i < notes.size() - 1; i++) {
            for (int j = 0; j < notes.size() - 1 - i; j++) {
                Note n1 = notes.get(j);
                Note n2 = notes.get(j + 1);
                if (compareNotes(n1, n2) < 0) {
                    notes.set(j, n2);
                    notes.set(j + 1, n1);
                }
            }
        }
    }

    private int compareNotes(Note n1, Note n2) {
        // pinned notes come first
        if (n1.isPinned() && !n2.isPinned()) return 1;
        if (!n1.isPinned() && n2.isPinned()) return -1;
        // then newer notes come first
        return Long.compare(n2.getTimestamp(), n1.getTimestamp());
    }

    public Map<String, Notebook> getNotebooks() {
        return notebooks;
    }

    public void setNotebooks(Map<String, Notebook> loadedNotebooks) {
        this.notebooks = loadedNotebooks;
    }
}
