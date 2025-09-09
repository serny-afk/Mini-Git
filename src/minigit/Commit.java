package minigit;

import java.util.*;

/* Commit contains metadata about the content you want to want to "save"
 * Metadata contains
 * tree_oid
 * parent (commits)
 * author
 * commiter
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
 */

public class Commit {
    int treeOid;
    String message;
    Blob blob;

    public Commit() {}

    public Commit(String message, String content) {
        this.message = message;
        blob
    }

    public int createCommit() {

    }
}
