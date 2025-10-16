package minigit;

import interfaces.IBlob;

import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class Blob implements IBlob {
    // a 'snapshot' object of the file
    // i.e. the content of the file
    private String filename;
    private String hash;
    private final String blobDir = ".minigit/blobs";

    public Blob(String filename) {
        this.filename = filename;
        try {
            // read the file content (supports only UTF-8 .txt file encoding for now)
            String content = Files.readString(Paths.get(filename), StandardCharsets.UTF_8);
            // generate hash
            this.hash = Utils.sha1(content);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create blob.");
        }
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
        //
        Path blobPath = Paths.get(blobDir, this.hash);
        // check if a file with this hash already exists in .minigit/blobs/
        if (!Files.exists(blobPath)) {
            try {
                // writes file content to the blob hash
                // content only handles string text for now
                String content = Files.readString(Paths.get(filename), StandardCharsets.UTF_8);
                Files.writeString(blobPath, content);
            } catch (IOException e) {
                throw new RuntimeException("Failed to save blob.");
            }
        }
    }
}
