public class Blob {
    private String filename;
    private String hash;

    // tracks and stores file content (string / byte array)
    // SHA-1 hash

    public Blob(String filename, String hash) {
        this.filename = filename;
        this.hash = hash;
    }
}
