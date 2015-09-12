package Lab2.Zad1;

/**
 * Created by kuba on 18.03.15.
 */
public class MySemaphore {
    private boolean s = false;

    public synchronized void take() {
        while(s == true) try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.s = true;
    }

    public synchronized void release() {
        this.s = false;
        this.notify();
    }
}
