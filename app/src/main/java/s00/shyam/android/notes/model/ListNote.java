package s00.shyam.android.notes.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ListNote extends NoteType<List<String>> {

    public ListNote(List<String> noteContent) {
        super(noteContent);
    }

    public ListNote(String... params) {
        super(Stream.of(params).collect(Collectors.toList()));
    }
}
