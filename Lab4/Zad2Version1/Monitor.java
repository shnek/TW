package Lab4.Zad2Version1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by kuba on 01.04.15.
 */
public class Monitor {
    private final int problemSize = 10;
    private Lab4.Zad2Version1.Kolejka buffor = new Lab4.Zad2Version1.Kolejka(problemSize);
    private int first = 0;
    private int size = 0;
    private boolean[] canAccess = new boolean[problemSize];

    Monitor() {
        for (int i = 0; i < problemSize; i++) {
            canAccess[1] = true;
        }
    }

    private final Lock lock = new ReentrantLock();
    private final Condition kProducent = lock.newCondition();
    private final Condition kKonsument = lock.newCondition();

    public void finish(int whatPlace) {
        canAccess[whatPlace] = true;
    }

    public void get() {
//        lock.lock();
//        while (set == false) {
//            try {
////                System.out.println("waiting");
//                kKonsument.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
////        System.out.println("Got number " + i);
//        set = false;
//        kProducent.signal();
//        lock.unlock();
    }

    public void set(int value) {
        lock.lock();
        if(!canAccess[(first + size) % problemSize]){
            canAccess[(first + size) % problemSize] = false;
            buffor.insertData(value);
        }
        kKonsument.signal();
        lock.unlock();
    }

}
