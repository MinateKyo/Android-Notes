package s00.shyam.android.notes.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import s00.shyam.android.notes.model.Note;
import s00.shyam.android.notes.room.NotesRepository;

public final class NoteViewModel extends AndroidViewModel {

    private NotesRepository repository;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NotesRepository(application);
    }

    public LiveData<List<Note>> getAllNotes() {
        return repository.getAllNotes();
    }

    public void insertNote(Note note) {
        repository.insertNote(note);
    }
}
