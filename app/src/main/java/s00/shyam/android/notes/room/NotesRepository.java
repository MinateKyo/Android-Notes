package s00.shyam.android.notes.room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import s00.shyam.android.notes.model.Note;

public class NotesRepository {
    private NoteDao noteDao;

    public NotesRepository(Application application) {
        NotesRoomDatabase database = NotesRoomDatabase.getDatabase(application);
        noteDao = database.noteDao();
    }

    public LiveData<List<Note>> getAllNotes()
    {
        try {
            return new getAllAsync(noteDao).execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertNote(Note note) {
        new insertAsync(noteDao).execute(note);
    }

    private static class getAllAsync extends AsyncTask<Void, Void, LiveData<List<Note>>> {

        private NoteDao noteDao;

        getAllAsync(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected LiveData<List<Note>> doInBackground(Void... voids) {
            return noteDao.getAllNotes();
        }
    }

    private static class insertAsync extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;

        insertAsync(NoteDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insertNote(notes[0]);
            return null;
        }
    }
}
