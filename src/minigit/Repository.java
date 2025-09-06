package minigit;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.*;

import interfaces.IBlob;
import interfaces.IIndex;
import interfaces.IRepository;

public class Repository implements IRepository {

    // TODO: manage commits, blobs, index
    private final IIndex index; // staging area
    private final String repoPath = ".minigit"; // root folder
    private final List<Map<String, String>> commits = new ArrayList<>(); // commits


    public Repository(IIndex index) {
        this.index = index;
    }

    @Override
    public void init() {
        // creates a ".minigit/blobs" folder
        try {
            Path blobsDir = Paths.get(repoPath, "blobs");
            Files.createDirectories(blobsDir);
            System.out.println("Initialized empty minigit repo in " + this.repoPath);
        } catch (IOException e) {
            e.printStackTrace(); // replace with throw new exception later on
        }
    }

    @Override
    public void add(String filename) {
        try {
            //read file content
            String content = Files.readString(Paths.get(filename));

            // blob creation and saving
            IBlob blob = new Blob(content);
            blob.saveBlob();

            // stage in index
            this.index.add(filename, blob.getHash());

        } catch (IOException e) {
            e.printStackTrace(); // replace with throw new exception later on
        }
    }

    @Override
    public void commit(String message) {
        Map<String, String> snapshot = new HashMap<>();

        // essentially takes staged files and stores them with a message
        for (String file : this.index.listFiles()) {
            snapshot.put(file, this.index.getBlobHash(file));
        }
        this.commits.add(snapshot);

        // simulates commit -m "message"
        System.out.println("Committed: " + message);
    }

    @Override
    public void log() {
        // prints out commits in reverse order
        for (int i = this.commits.size() - 1; i >= 0; i--) {
            System.out.println("Commit #" + (i + 1));
            this.commits.get(i).forEach((file, hash) -> System.out.println(file + " -> " + hash));
            System.out.println();
        }
    }

    @Override
    public void status() {
        System.out.println("Staged files:");
        for (String file : this.index.listFiles()) {
            System.out.println(file + " -> " + this.index.getBlobHash(file));
        }
    }
}
