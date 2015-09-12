package ActiveObject;

/**
 * Created by kuba on 06.05.15.
 */
public class Scheduler {
    private RequestQueue queue = new RequestQueue();

    public Scheduler(){
        startExecutingThread();
    }

    private void startExecutingThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    Request request = queue.getNext();
                    request.execute();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
    public void enqueue(Request request){
        queue.put(request);
    }
}