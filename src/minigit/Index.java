package minigit;
import java.util.*;
import interfaces.IBlob;
import interfaces.IIndex;
import interfaces.IRepository;

public class Index implements IIndex{
    // acts as staging area (i.e. git add .)
    private List<String> stagedFiles;

    public Index() {
        this.stagedFiles = new ArrayList<>();
    }

    @Override
    public void add(String filename, String blobhash) {
        return;
    }

    @Override
    public List<String> listFiles() {
        return null;
    }

    @Override
    public String getBlobHash(String filename) {
        return null;
    }

}
