package s00.shyam.android.notes.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity(tableName = "note_table")
public class Note {

    @ColumnInfo()
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    @NonNull
    private String mTitle;

    @ColumnInfo(name = "created_date")
    @NonNull
    private String mCreatedDate;

    @ColumnInfo(name = "modified_date")
    private String mModifiedDate;

    @ColumnInfo(name = "content")
    private NoteType mNoteContent;

    @Ignore
    private SimpleDateFormat dateFormat;

    public Note() {
        this("", new SimpleNote(""));
    }
    public Note(@NonNull String title, NoteType content) {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        mTitle = title;
        mNoteContent = content;
        mCreatedDate = dateFormat.format(new Date());
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        this.mCreatedDate = createdDate;
    }

    public String getModifiedDate() {
        return mModifiedDate;
    }

    public NoteType getNoteContent() {
        return mNoteContent;
    }

    public void setNoteContent(NoteType noteContent) {
        this.mNoteContent = noteContent;
    }

    public void setModifiedDate(String mModifiedDate) {
        this.mModifiedDate = mModifiedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

