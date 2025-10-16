package interfaces;
import java.util.*;

public interface IIndex {
    void add(String filename, String blobhash);
    List<String> listFiles();
    String getBlobHash(String filename);

    void load();
    void save();
}
