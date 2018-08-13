package s00.shyam.android.notes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import s00.shyam.android.notes.model.Note;
import s00.shyam.android.notes.stub.NoteData;

import static s00.shyam.android.notes.SimpleNoteActivity.NOTE_ID;

public final class NoteListRecyclerAdapter extends RecyclerView.Adapter<NoteListRecyclerAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Note> mNotes;
    private Context mContext;

    NoteListRecyclerAdapter(Context context, List<Note> notes) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        mNotes = notes;
    }

    @Override
    public NoteListRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listView = inflater.inflate(R.layout.list_items, parent, false);
        return new ViewHolder(listView);
    }

    @Override
    public void onBindViewHolder(NoteListRecyclerAdapter.ViewHolder holder, int position) {
        Note note = mNotes.get(position);
        holder.mTitle.setText(String.format(note.getTitle()));
        holder.mContent.setText(String.format(note.getmNoteContent().GetContent().toString()));
        holder.setNotePosition(position);
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private TextView mContent;
        private int pos;

        ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.note_title);
            mContent = (TextView) itemView.findViewById(R.id.note_content);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, SimpleNoteActivity.class);
                intent.putExtra(NOTE_ID, pos);
                mContext.startActivity(intent);
            });
        }

        public int getNotePosition() {
            return pos;
        }

        public void setNotePosition(int position) {
            pos = position;
        }
    }
}
