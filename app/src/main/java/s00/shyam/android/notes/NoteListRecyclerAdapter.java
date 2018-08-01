package s00.shyam.android.notes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import s00.shyam.android.notes.model.Note;
import s00.shyam.android.notes.stub.NoteData;

public final class NoteListRecyclerAdapter extends RecyclerView.Adapter<NoteListRecyclerAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Note> mNotes;

    NoteListRecyclerAdapter(Context context, List<Note> notes) {
        inflater = LayoutInflater.from(context);
        mNotes = notes;
        //48700 * 72055 *
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
        holder.pos = position;
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
        }

        public int getNotePosition() {
            return pos;
        }
    }
}
