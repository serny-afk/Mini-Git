package interfaces;
import java.util.*;

public interface ICommit{
    String getMessage();
    String getParent();
    Map<String, String> getFiles();
    long getTimestamp();

}