package ActiveObject;

/**
 * Created by kuba on 06.05.15.
 */
public class Proxy {

    private Buffer buffer;
    private Scheduler scheduler;

    public Proxy(int bufferCapacity){
        buffer = new Buffer(bufferCapacity);
        scheduler = new Scheduler();
    }

    public Future put(Integer[] elements){
        FuturePut result = new FuturePut();
        scheduler.enqueue(new PutRequest(buffer, result, elements));
        return result;

    }
    public Future get(int numberOfElements){
        FutureGet result = new FutureGet();
        scheduler.enqueue(new GetRequest(buffer, result, numberOfElements));
        return result;
    }
}
