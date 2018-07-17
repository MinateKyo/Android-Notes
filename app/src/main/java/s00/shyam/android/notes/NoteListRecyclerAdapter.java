package s00.shyam.android.notes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public final class NoteListRecyclerAdapter extends RecyclerView.Adapter<NoteListRecyclerAdapter.ViewHolder> {
    @Override
    public NoteListRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(NoteListRecyclerAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
