package s00.shyam.android.notes.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

import s00.shyam.android.notes.model.Note;
import s00.shyam.android.notes.model.SimpleNote;

@Database(entities = {Note.class}, version = 1)
@TypeConverters({ NoteConverters.class })
public abstract class NotesRoomDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();

    public static NotesRoomDatabase INSTANCE;

    static NotesRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (NotesRoomDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NotesRoomDatabase.class, "notes_database")
                        .addCallback(OnDbOpen)
                        .build();
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback OnDbOpen = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            //new LoadDummyData(INSTANCE).execute();
        }
    };

    private static class LoadDummyData extends AsyncTask<Void, Void, Void> {

        private NoteDao noteDao;
        LoadDummyData(NotesRoomDatabase noteDao) {
            this.noteDao = noteDao.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            List<Note> notes = noteDao.getAllNotes().getValue();
            if (notes == null ||  notes.size() == 0) {
                noteDao.insertNote(new Note("Test", new SimpleNote("Hello World")));
                noteDao.insertNote(new Note("Hello", new SimpleNote("Hello World")));
                noteDao.insertNote(new Note("Dummy", new SimpleNote("Hello World")));
            }
            return null;
        }
    }

}
