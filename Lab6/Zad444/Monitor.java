package Lab6.Zad444;

import Lab6.Zad444.Addition1.Kolejka;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by kuba on 01.04.15.
 */
public class Monitor {
    public Kolejka buffor;
//    private int M;
    private LinkedList<Integer> wolne = new LinkedList();
    private LinkedList<Integer> zajete = new LinkedList();

    Monitor(int M) {
//        this.M = M;
        this.buffor = new Kolejka(2 * M);
        for (int i = 0; i < 2 * M; i++) {
            wolne.add(i);
        }
    }

    private void startInserting(int i, int[] data) {
//        System.out.println("inserting " + data[0]);
        buffor.insertSpecific(i, data[0]);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        endInserting(i);
    }

    private void startGetting(int i) {
        int temp = buffor.getSpecific(i);
//        System.out.println("got " + temp);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        endGetting(i);
    }

    private final Lock ProducentLock = new ReentrantLock(true);
    private final Lock ConsumerLock = new ReentrantLock(true);
    private final Condition ProducentCondition = ProducentLock.newCondition();
    private final Condition ConsumerCondition = ConsumerLock.newCondition();

    public void wstaw(int ile, int[] data, int ID) {
        ProducentLock.lock();
        while (wolne.isEmpty()) {
            try {
                ProducentCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int i = wolne.getFirst();
        wolne.removeFirst();

        ProducentLock.unlock();
        startInserting(i, data);
    }

    public void pobierz(int ile, int ID) {
        ConsumerLock.lock();
        while (zajete.isEmpty()) {
            try {
                ConsumerCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int i = zajete.getFirst();
        zajete.removeFirst();

        ConsumerLock.unlock();
        startGetting(i);
    }

    private void endInserting(int i) {
        ConsumerLock.lock();
        zajete.add(i);
        ConsumerCondition.signal();
        ConsumerLock.unlock();
    }

    private void endGetting(int i) {
        ProducentLock.lock();
        wolne.add(i);
        ProducentCondition.signal();
        ProducentLock.unlock();
    }
}