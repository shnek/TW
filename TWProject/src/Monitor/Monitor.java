package Monitor;

import Monitor.Addition1.Kolejka;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by kuba on 01.04.15.
 */
public class Monitor {
    public Kolejka buffor;
    private int M;


    Monitor(int M) {
        this.M = M;
        this.buffor = new Kolejka(2 * M);
    }

    private final Lock lock = new ReentrantLock(true);
    private final Lock ProducentLock = new ReentrantLock(true);
    private final Lock ConsumerLock = new ReentrantLock(true);

    public void wstaw(int ile, int[] data) {
        ProducentLock.lock();
        boolean test = true;
        while(test){
            lock.lock();
            if(2 * M - buffor.haveItems() >= ile){
                test = false;
            }else{
                lock.unlock();
            }
        }
        buffor.insertData(ile, data);
        lock.unlock();
        ProducentLock.unlock();
    }

    public int[] pobierz(int ile) {
        ConsumerLock.lock();
        int[] data;
        boolean test = true;
        while(test){
            lock.lock();
            if(buffor.haveItems() >= ile){
                test = false;
            }else {
                lock.unlock();
            }
        }
        data = buffor.getData(ile);
        lock.unlock();
        ConsumerLock.unlock();
        return data;
    }

}
