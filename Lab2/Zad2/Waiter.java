package Lab2.Zad2;

/**
 * Created by kuba on 18.03.15.
 */
public class Waiter {
    Waiter(int count){
        this.s = count;
    }
    private int s;

    public synchronized void acquire() {
        while(s == 0) try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.s--;
    }

    public synchronized void release() {
        this.s++;
        this.notify();
    }
}
