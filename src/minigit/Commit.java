package minigit;

import java.io.Serial;
import java.util.Map;
import java.io.Serializable;
import interfaces.ICommit;

/* Commit contains metadata about the content you want to "save"
 * Metadata contains
 * tree_oid
 * parent (commits)
 * author (optional)
 * commiter (optional)
 * commit message
 * 
 * How to make a commit
 * Create blob
 * Add blob to tree
 * Create a commit and save all necessary metadata
 * Save commit in git/objects
 * 
 * How to recreate file contents using commit
 * Use tree_oid to find main tree that contains the commited content
 * Recursively go down the tree
 * If another tree_oid, go down that tree
 * If blob oid, recreate blob in working directory to "load" state
 * Repeat until end of tree reach, oid = None
 * 
 * 
 */

public class Commit implements ICommit, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Map<String, String> files;
    private final String parent;
    private final String message;
    private final long timestamp;

    public Commit(String message, String parent, Map<String, String> files) {
        this.message = message;
        this.parent = parent;
        this.files = files;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public String getParent() {
        return this.parent;
    }

    public Map<String, String> getFiles() {
        return files;
    }

    public long getTimestamp() {
        return timestamp;
    }

    // unique methods
    /*
    public String parseData() {
        StringBuilder sb = new StringBuilder();
        sb.append(treeOid);
        for (String parent : parents) {
            sb.append(parent);
        }
        sb.append(message);
        return sb.toString();
    }

    public void commit() {
        String data = parseData();
        String hash = Utils.sha1(Utils.prefix("commit", data));
    }
     */

}
