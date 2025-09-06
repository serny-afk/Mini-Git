package minigit;

import interfaces.IBlob;
import interfaces.IIndex;
import interfaces.IRepository;

public class Blob implements IBlob {
    private String filename;
    private String hash;

    // tracks and stores file content (string / byte array)
    // SHA-1 hash

    public Blob(String filename, String hash) {
        this.filename = filename;
        this.hash = hash;
    }

    @Override
    public String getHash() {
        return null;
    }

    @Override
    public String getFilename() {
        return null;
    }

    @Override
    public void saveBlob() {
        return;
    }
}
