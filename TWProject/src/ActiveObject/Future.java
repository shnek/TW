package ActiveObject;

public interface Future {
    boolean done();

    Object get();

    void awaitDone();

}
