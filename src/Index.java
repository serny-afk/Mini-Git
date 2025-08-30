import java.util.*;

public class Index {
    // acts as staging area (i.e. git add .)
    private List<String> stagedFiles;

    public Index() {
        this.stagedFiles = new ArrayList<>();
    }

    public void add(String filename) {
        stagedFiles.add(filename);
    }
}
