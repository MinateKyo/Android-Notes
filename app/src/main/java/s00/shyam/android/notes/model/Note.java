package s00.shyam.android.notes.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {
    private String mTitle;
    private String mCreatedDate;
    private String mModifiedDate;
    private NoteType mNoteContent;

    private SimpleDateFormat dateFormat;

    public Note(String title, NoteType content) {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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

    public String getModifiedDate() {
        return mModifiedDate;
    }

    public NoteType getmNoteContent() {
        return mNoteContent;
    }

    public void setmModifiedDate(String mModifiedDate) {
        this.mModifiedDate = mModifiedDate;
    }
}

