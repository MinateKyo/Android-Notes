package s00.shyam.android.notes.model;

public abstract class NoteType<T> {
    private T mContent;

    protected NoteType(T noteContent) {
        mContent = noteContent;
    }

    public T GetContent() {
        return mContent;
    }

    protected void SetContent(T content) {
        mContent = content;
    }
}

