package minigit;
import java.util.*;
import interfaces.IIndex;

public class Index implements IIndex{
    // acts as staging area (i.e. git add .)
    private final Map<String, String> stagedFiles = new HashMap<>();

    @Override
    public void add(String filename, String blobHash) {
        this.stagedFiles.put(filename, blobHash);
    }

    @Override
    public List<String> listFiles() {
        return new ArrayList<>(this.stagedFiles.keySet());
    }

    @Override
    public String getBlobHash(String filename) {
        return this.stagedFiles.get(filename);
    }
}
