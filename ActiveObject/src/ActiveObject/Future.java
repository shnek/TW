package ActiveObject;

/**
 * Created by kuba on 06.05.15.
 */
public interface Future {
    boolean done();

    Object get();

    void awaitDone();

}
