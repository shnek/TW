package ActiveObject;

public class FuturePut implements Future {

    private boolean isDone;

    @Override
    public boolean done() {
        return isDone;

    }

    @Override
    public Object get() {
        return null;
    }

    public synchronized void markAsDone() {
        isDone = true;
        notify();
    }

    @Override
    public synchronized void awaitDone() {
        while (!done()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
