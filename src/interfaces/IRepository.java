package interfaces;

public interface IRepository {
    void init();
    void add(String filePath);
    void commit(String message);
    void log();
    void status();
}
