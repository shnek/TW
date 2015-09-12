package Lab2.Zad1;


/**
 * Created by kuba on 14.03.15.
 */
public class Increaser extends Thread {
    MySemaphore semaphore = null;

    public Increaser(MySemaphore theSemaphore) {
        this.semaphore = theSemaphore;
    }

    public void run() {
        for (int i = 0; i < 3000; i++) {
            semaphore.take();
            Starter.increase();
            semaphore.release();
        }
    }
}