package s00.shyam.android.notes.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import s00.shyam.android.notes.model.Note;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note_table")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM note_table WHERE id = :id")
    LiveData<Note> getNoteById(int id);

    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    void insertNote(Note note);

    @Delete
    void deleteNote(Note ... notes);

    @Query("DELETE FROM note_table WHERE id = :id")
    void deleteNoteById(int id);

    @Update
    void updateNote(Note ... notes);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();
}
