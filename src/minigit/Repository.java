package minigit;
import interfaces.IBlob;
import interfaces.IIndex;
import interfaces.IRepository;

public class Repository implements IRepository {

    // TODO: manage commits, blobs, index

    public Repository() {
        // constructor
    }

    @Override
    public void init() {
        System.out.println("Repo Initialized");
        // i.e. git init method
    }

    @Override
    public void add(String filename) {

    }

    @Override
    public void commit(String message) {

    }

    @Override
    public void log() {

    }

    @Override
    public void status() {

    }

}
