package ActiveObject;

import java.util.ArrayDeque;
import java.util.Deque;

public class RequestQueue {
    private  int capacity;
    private Deque<Request> getRequests = new ArrayDeque<>(capacity);
    private Deque<Request> putRequests = new ArrayDeque<>(capacity);
    private Deque<Request> Requests = new ArrayDeque<>(capacity);

    RequestQueue(int queueSize){
        this.capacity = queueSize;
    }

    public synchronized Request getNext() {
        Request request = getNextRequest();
        while (request == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            request = getNextRequest();
        }
        return request;
    }

    private Request getNextRequest() {
        if (!putRequests.isEmpty() && putRequests.getFirst().canBeExecuted()) {
            return putRequests.removeFirst();
        }
        if (!getRequests.isEmpty() && getRequests.getFirst().canBeExecuted()) {
            return getRequests.removeFirst();
        }
        if (!Requests.isEmpty()) {
            if (Requests.getFirst().canBeExecuted()) {
                return Requests.removeFirst();
            } else {
                if (Requests.getFirst().getClass() == RequestGet.class) {
                    getRequests.add(Requests.removeFirst());
                } else {
                    putRequests.add(Requests.removeFirst());
                }
                return getNextRequest();
            }
        }
        return null;
    }

    public synchronized void put(Request request) {
        Requests.add(request);
        notify();
    }
}
