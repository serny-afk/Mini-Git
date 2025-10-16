package minigit;

import java.util.*;
import interfaces.IIndex;
import java.io.*;

public class Index implements IIndex, Serializable {
    private static final long serialVersionUID = 1L;
    private final Map<String, String> stagedFiles = new HashMap<>();
    private final String indexFilePath = ".minigit/index.dat";

    @Override
    public void add(String filename, String blobHash) {
        this.stagedFiles.put(filename, blobHash);
        save();
    }

    @Override
    public List<String> listFiles() {
        return new ArrayList<>(this.stagedFiles.keySet());
    }

    @Override
    public String getBlobHash(String filename) {
        return this.stagedFiles.get(filename);
    }

    // save staged files to disk
    @Override
    public void save() {
        try {
            File dir = new File(".minigit");
            if (!dir.exists()) { dir.mkdirs(); }

            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(indexFilePath))) {
                out.writeObject(stagedFiles);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // load staged files from disk
    @SuppressWarnings("unchecked")
    public void load() {
        File file = new File(indexFilePath);
        if (!file.exists()) return;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Map<String, String> loaded = (Map<String, String>) in.readObject();
            stagedFiles.clear();
            stagedFiles.putAll(loaded);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
