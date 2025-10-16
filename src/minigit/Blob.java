package minigit;

import interfaces.IBlob;

import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class Blob implements IBlob {
    private String filename;
    private String hash;
    private final String blobDir = ".minigit/blobs";

    public Blob(String filename) {
        this.filename = filename;
        try {
            String content = Files.readString(Paths.get(filename), StandardCharsets.UTF_8);
            this.hash = Utils.sha1(content);
        } catch (IOException e) {
            e.printStackTrace();
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
        Path blobPath = Paths.get(blobDir, this.hash);
        if (!Files.exists(blobPath)) {
            try {
                String content = Files.readString(Paths.get(filename), StandardCharsets.UTF_8);
                Files.writeString(blobPath, content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
