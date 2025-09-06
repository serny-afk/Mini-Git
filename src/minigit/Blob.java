package minigit;

import interfaces.IBlob;
import interfaces.IIndex;
import interfaces.IRepository;

public class Blob implements IBlob {
    private String content;
    private String hash;

    // tracks and stores file content (string / byte array)
    // SHA-1 hash

    public Blob(String content) {
        this.content = content;
        // this.hash = Utils.hashContent(content);

        // ** Note: Utils.hashContent(String content) is a method to be implemented later on
        // which will be under utils.java, to compute SHA-1 hash **
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
