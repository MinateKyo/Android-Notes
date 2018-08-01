package s00.shyam.android.notes.stub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import s00.shyam.android.notes.model.ListNote;
import s00.shyam.android.notes.model.Note;
import s00.shyam.android.notes.model.SimpleNote;

public final class NoteData {
    private static final NoteData ourInstance = new NoteData();
    private List<Note> noteList;

    public static NoteData getInstance() {
        return ourInstance;
    }

    private NoteData() {
        noteList = new ArrayList<>();
        RefreshNotes();
    }

    public List<Note> GetNotes() {
        return noteList;
    }

    public Note RemoveNote(int index) {
        Note removed = null;
        try {
            removed = noteList.remove(index);
        }

        catch (IndexOutOfBoundsException ex) {
            removed = null;
        }

        return removed;
    }

    private void RefreshNotes()
    {
        noteList.addAll(SIMPLE_NOTES);
        noteList.addAll(LIST_NOTES);
    }

    private final List<Note> SIMPLE_NOTES = Arrays.asList(new Note("Demo 1", new SimpleNote("This is a simple note")),
            new Note("Demo 2", new SimpleNote("This is a simple note")),
            new Note("Test Simple Note", new SimpleNote("This is a simple note")),
            new Note("Test 2", new SimpleNote("This is a simple note")),
            new Note("Hello", new SimpleNote("World")));

    private final List<Note> LIST_NOTES = Arrays.asList(
            new Note("Demo List1", new ListNote("Item1", "Item2", "Item3")),
            new Note("Shop List2", new ListNote("Item4", "Item5", "Item6")),
            new Note("Fruit List", new ListNote("Apple", "Orange", "Mango")),
            new Note("Games", new ListNote("Fortnite", "Need For Speed", "God of War")));
}

