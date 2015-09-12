package Lab5.Zad443;

import Lab5.Zad443.Addition1.Kolejka;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by kuba on 01.04.15.
 */
public class Monitor {
    public Kolejka buffor;
    private int M;
    public boolean isFP = false;
    public boolean isFC = false;

    Monitor(int M) {
        this.M = M;
        this.buffor = new Kolejka(2 * M);
    }

    private final Lock lock = new ReentrantLock(true);
    private final Condition producent = lock.newCondition();
    private final Condition konsument = lock.newCondition();
    private final Condition firstProducent = lock.newCondition();
    private final Condition firstConsument = lock.newCondition();


    public void wstaw(int ile, int[] data, int ID) {
        lock.lock();
//        System.out.println("Chce wstawic " + ile);
        try {
            while (isFP == true) {
                producent.await();
            }
            isFP = true;
            while (2 * M - buffor.haveItems() <= ile) {
                firstProducent.await();
            }
//            System.out.println("Nr " + ID + ", wstawiam: " + ile + "!");
            buffor.insertData(ile, data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            isFP = false;
            isFC = false;
            firstConsument.signal();
            producent.signal();
            lock.unlock();
        }
    }

    public int[] pobierz(int ile, int ID) {
        lock.lock();
//        System.out.println("Chce pobrac "+ ile);
        int[] data = new int[ile];
        try {
            while (isFC == true) {
                konsument.await();
            }
            isFC = true;
            while (buffor.haveItems() <= ile) {
                firstConsument.await();
            }
//            System.out.println("Nr " + ID + ", pobieram: " + ile + "!");
            data = buffor.getData(ile);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            isFC = false;
            isFP = false;
            firstProducent.signal();
            konsument.signal();
            lock.unlock();
        }
        return data;
    }

}
