package s00.shyam.android.notes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public final class NoteListRecyclerAdapter extends RecyclerView.Adapter<NoteListRecyclerAdapter.ViewHolder> {

    private LayoutInflater inflater;

    NoteListRecyclerAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }


    @Override
    public NoteListRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listView = inflater.inflate(R.layout.list_items, parent, false);
        return new ViewHolder(listView);
    }

    @Override
    public void onBindViewHolder(NoteListRecyclerAdapter.ViewHolder holder, int position) {
        holder.mTitle.setText(String.format("Item %s", Integer.toString(position)));
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;

        ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.note_title);

        }
    }
}
