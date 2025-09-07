package minigit;

import interfaces.IBlob;

import java.io.IOException;
import java.nio.file.*;

// import utils.Utils; ** later on when utils.java is implemented

public class Blob implements IBlob {
    private String filename;
    private String hash;
    private final String blobDir = ".minigit/blobs";

    // tracks and stores file content (string / byte array)
    // SHA-1 hash

    public Blob(String filename) {
        this.filename = filename;
        try {
            String content = Files.readString(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
            // throw new RuntimeException(e);
        }
        // this.hash = Utils.hashContent(content);

        // ** Note: Utils.hashContent(String content) is a method to be implemented later on
        // which will be under utils.java, to compute SHA-1 hash **
    }

    @Override
    public String getHash() {
        return this.hash;
    }

    @Override
    public String getFilename() {
        return this.filename;
    }

    @Override
    public void saveBlob() {
        Path blobPath = Paths.get(blobDir, this.hash);
        if (!Files.exists(blobPath)) {
            try {
                String content = Files.readString(Paths.get(filename));
                Files.writeString(blobPath, content);
            } catch (IOException e) {
                e.printStackTrace();
                // throw new RuntimeException(e);
            }
        }
    }
}
