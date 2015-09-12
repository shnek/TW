package ActiveObject;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by kuba on 06.05.15.
 */
public class RequestQueue {
    private static final int capacity = 1000;
    private Deque<Request> getRequests = new ArrayDeque<>(capacity);
    private Deque<Request> putRequests = new ArrayDeque<>(capacity);

    public synchronized Request getNext(){
        Request request = getNextRequest();
        while(request == null){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            request = getNextRequest();
        }
        return request;
    }
    private Request getNextRequest(){
        if(!putRequests.isEmpty() && putRequests.getFirst().canBeExecuted()){
            return putRequests.removeFirst();
        }
        if(!getRequests.isEmpty() && getRequests.getFirst().canBeExecuted()){
            return getRequests.removeFirst();
        }
        return null;
    }

    public synchronized void put(Request request){
        if(request instanceof GetRequest){
            getRequests.add(request);
        }else if( request instanceof PutRequest){
            putRequests.add(request);
        }
        else{
            throw new IllegalArgumentException();
        }
        notify();
    }
}
