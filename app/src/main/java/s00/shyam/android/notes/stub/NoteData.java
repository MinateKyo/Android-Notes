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

    static NoteData getInstance() {
        return ourInstance;
    }

    private NoteData() {
        noteList = new ArrayList<>();
    }

    public List<Note> GetNotes() {
        noteList.addAll(GetSimpleNotes());
        noteList.addAll(GetListNotes());
        return noteList;
    }

    private List<Note> GetSimpleNotes() {
        return Arrays.stream(SIMPLE_NOTES).collect(Collectors.toList());
    }

    private List<Note> GetListNotes() {
        return Arrays.stream(LIST_NOTES).collect(Collectors.toList());
    }

    private static final Note[] SIMPLE_NOTES = {
            new Note("Demo 1", new SimpleNote("This is a simple note")),
            new Note("Demo 2", new SimpleNote("This is a simple note")),
            new Note("Test Simple Note", new SimpleNote("This is a simple note")),
            new Note("Test 2", new SimpleNote("This is a simple note")),
            new Note("Hello", new SimpleNote("World"))
    };

    private static final Note[] LIST_NOTES = {
            new Note("Demo List1", new ListNote("Item1", "Item2", "Item3")),
            new Note("Shop List2", new ListNote("Item4", "Item5", "Item6")),
            new Note("Fruit List", new ListNote("Apple", "Orange", "Mango")),
            new Note("Games", new ListNote("Fortnite", "Need For Speed", "God of War"))

    };
}

