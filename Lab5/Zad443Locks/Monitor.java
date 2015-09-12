package Lab5.Zad443Locks;

import Lab5.Zad443Locks.Addition1.Kolejka;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by kuba on 01.04.15.
 */
public class Monitor {
    public Lab5.Zad443Locks.Addition1.Kolejka buffor;
    private int M;
    public boolean isFP = false;
    public boolean isFC = false;

    Monitor(int M) {
        this.M = M;
        this.buffor = new Lab5.Zad443Locks.Addition1.Kolejka(2 * M);
    }

    private final Lock lock = new ReentrantLock(true);
    private final Lock ProducentLock = new ReentrantLock(true);
    private final Lock ConsumerLock = new ReentrantLock(true);
    private final Condition pcondition = lock.newCondition();
    private final Condition ccondition = lock.newCondition();

    public void wstaw(int ile, int[] data, int ID) {
        ProducentLock.lock();

        lock.lock();

        while(2 * M - buffor.haveItems() <= ile){
            try {
                pcondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buffor.insertData(ile, data);
        ccondition.signal();
        lock.unlock();
        ProducentLock.unlock();
    }

    public int[] pobierz(int ile, int ID) {
        ConsumerLock.lock();
        int[] data = new int[ile];
        lock.lock();
        while(buffor.haveItems() <= ile){
            try {
                ccondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        data = buffor.getData(ile);
        pcondition.signal();
        lock.unlock();
        ConsumerLock.unlock();
        return data;
    }

}
