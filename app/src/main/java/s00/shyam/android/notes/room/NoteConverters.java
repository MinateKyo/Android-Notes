package s00.shyam.android.notes.room;

import android.arch.persistence.room.TypeConverter;

import s00.shyam.android.notes.model.NoteType;
import s00.shyam.android.notes.model.SimpleNote;

public final class NoteConverters {

    @TypeConverter
    public static String fromNoteType(NoteType value) {
        //TODO: for now we support only simple note type.
        SimpleNote simpleNote = (SimpleNote) value;
        return simpleNote == null ? "" : simpleNote.GetContent();
    }

    @TypeConverter
    public static NoteType fromString(String value) {
        return new SimpleNote(value);
    }
}
