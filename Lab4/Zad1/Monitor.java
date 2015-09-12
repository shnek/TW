package Lab4.Zad1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by kuba on 01.04.15.
 */
public class Monitor {
    private boolean set = false;
    private int i = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition kProducent = lock.newCondition();
    private final Condition kKonsument = lock.newCondition();

    public void get(){
        lock.lock();
        while(set == false){
            try {
//                System.out.println("waiting");
                kKonsument.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Got number " + i);
        set = false;
        kProducent.signal();
        lock.unlock();
    }

    public void set(int value){
        lock.lock();
        while(set == true){
            try {
                kProducent.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        i = value;
        System.out.println("Set number " + i);
        set = true;
        kKonsument.signal();
        lock.unlock();
    }

}
