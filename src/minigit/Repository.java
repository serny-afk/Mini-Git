/* All trees, subtrees, commit and blobs are hashed to give oids (object ids),
 * the actual object files are then saved in minigit/objects.
 * 
 * Trees are lists of oids that point to other objects,
 * these oids can point to subtrees,
 * oids act as filepaths to object files
 * 
 * How to access commits?
 * Start with latest commit,
 * search upwards through parents until specific commit is found
 * Graph traversal
 * Also can find directly via oid *
 */

package minigit;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.*;

import interfaces.IBlob;
import interfaces.IIndex;
import interfaces.IRepository;

public class Repository implements IRepository {

    private final IIndex index; // staging area
    private final String repoPath = ".minigit"; // root folder/

    public Repository(IIndex index) {
        this.index = index;
        this.index.load();
    }

    @Override
    public void init() {
        // creates a ".minigit/blobs" folder
        try {
            Files.createDirectories(Paths.get(repoPath, "blobs"));
            Files.createDirectories(Paths.get(repoPath, "commits"));
            Files.writeString(Paths.get(repoPath, "HEAD"), ""); // empty head
            System.out.println("Initialized empty MiniGit repo in " + repoPath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize repository", e);
        }
    }

    @Override
    public void add(String filename) {
        // blob creation and saving
        IBlob blob = new Blob(filename);
        blob.saveBlob();

        // stage in index
        this.index.add(filename, blob.getHash());

        System.out.println("Added " + filename + " -> " + blob.getHash());
    }

    @Override
    public void commit(String message) {
        Map<String, String> snapshot = new HashMap<>();

        // essentially takes staged files and stores them with a message
        for (String file : this.index.listFiles()) {
            snapshot.put(file, this.index.getBlobHash(file));
        }

        String parent = getHEAD();
        Commit newCommit = new Commit(message, parent, snapshot);

        // hash the commit data to get a commit ID
        String commitHash = Utils.sha1(message + System.currentTimeMillis());

        saveCommit(newCommit, commitHash);
        updateHEAD(commitHash);

        System.out.println("Committed: " + message + " (" + commitHash + ")");
    }

    @Override
    public void log() {
        String current = getHEAD();
        if (current == null) {
            System.out.println("No commits yet.");
            return;
        }

        while (current != null) {
            Commit c = loadCommit(current);
            if (c == null) { break; }

            System.out.println("=== Commit " + current + " ===");
            System.out.println("Date: " + new java.util.Date(c.getTimestamp()));
            System.out.println("Message: " + c.getMessage());
            System.out.println("Files:");
            c.getFiles().forEach((f, h) -> System.out.println("  " + f + " -> " + h));
            System.out.println();

            current = c.getParent();
        }
    }

    @Override
    public void status() {
        System.out.println("Staged files:");
        for (String file : this.index.listFiles()) {
            System.out.println(file + " -> " + this.index.getBlobHash(file));
        }
    }

    private void saveCommit(Commit commit, String commitHash) {
        try {
            Path dir = Paths.get(repoPath, "commits");
            Files.createDirectories(dir);
            try (ObjectOutputStream out = new ObjectOutputStream(
                    Files.newOutputStream(dir.resolve(commitHash)))) {
                out.writeObject(commit);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save commit.");
        }
    }

    private Commit loadCommit(String commitHash) {
        try (ObjectInputStream in = new ObjectInputStream(
                Files.newInputStream(Paths.get(repoPath, "commits", commitHash)))) {
            return (Commit) in.readObject();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load commit.");
        }
    }

    private void updateHEAD(String commitHash) {
        try {
            Files.writeString(Paths.get(repoPath, "HEAD"), commitHash);
        } catch (IOException e) {
            throw new RuntimeException("Failed to update HEAD.");
        }
    }

    private String getHEAD() {
        Path headPath = Paths.get(repoPath, "HEAD");
        if (!Files.exists(headPath)) return null;
        try {
            return Files.readString(headPath).trim();
        } catch (IOException e) {
            throw new RuntimeException("Failed to get HEAD. (hehe)");
        }
    }
}
