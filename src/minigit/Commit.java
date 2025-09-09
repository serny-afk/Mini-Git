package minigit;

import java.util.*;

/* Commit contains metadata about the content you want to want to "save"
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

public class Commit {
    private String treeOid;
    private String parents[];
    private String message;
    public String objectDirectory = "./minigit/objects";

    public Commit() {}

    public Commit(String treeOid, String message) {
        this.treeOid = treeOid;
        this.message = message;
    }

    // constructors
    public void setTreeOid(String treeOid) {
        this.treeOid = treeOid;
    }

    public void setParents(String parents[]) {
        this.parents = parents;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // accessors
    public String getTreeOid() {
        return treeOid;
    }

    public String[] getParents() {
        return parents;
    }

    public String getMessage() {
        return message;
    }

    // unique methods
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
}
