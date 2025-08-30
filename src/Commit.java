import java.util.*;

public class Commit {
    private String id;
    private String message;
    private List<Blob> blobs;

    public Commit(String id, String message) {
        this.id = id;
        this.message = message;
        this.blobs = new ArrayList<>();
    }


}
