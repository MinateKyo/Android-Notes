package s00.shyam.android.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import s00.shyam.android.notes.model.Note;
import s00.shyam.android.notes.model.SimpleNote;
import s00.shyam.android.notes.stub.NoteData;

public class SimpleNoteActivity extends AppCompatActivity {

    public static final String NOTE_ID = "s00.shyam.android.notes.NOTE_ID";
    private List<Note> mNotes;
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_note);

        mTitle = (TextView)findViewById(R.id.note_stub);

        Load();
    }

    private void Load() {
        Intent intent = getIntent();
        int noteId = intent.getIntExtra(NOTE_ID, -1);

        mNotes = NoteData.getInstance().GetNotes();
        Note note = mNotes.get(noteId);
        mTitle.setText(note.getTitle());

    }
}
