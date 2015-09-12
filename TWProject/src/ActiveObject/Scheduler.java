package ActiveObject;

public class Scheduler {
    private RequestQueue queue;

    public Scheduler(int queueSize) {
        this.queue = new RequestQueue(queueSize);
        startExecutingThread();
    }


    private void startExecutingThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Request request = queue.getNext();
                    request.execute();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void enqueue(Request request) {
        queue.put(request);
    }
}