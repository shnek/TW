package Lab2.Zad2;

/**
 * Created by kuba on 18.03.15.
 */

import java.util.Random;

public class Philosopher extends Thread {

    protected static class Fork {
        protected int me;
        protected boolean inUse;

        public Fork(int me) {
            this.me = me;
        }

        public synchronized boolean get(int who) {
            return inUse ? false : (inUse = true);
        }

        public synchronized void put(int who) {
            inUse = false;
            notify();
        }

        public synchronized void waitFor(int who) {
            while (!get(who))
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }

    public Philosopher(int me, Fork left, Fork right) {
        this.me = me;
        this.left = left;
        this.right = right;
    }

    protected static Random random = new Random();
    protected int me;
    protected int time = 20;
    protected Fork left, right;
    boolean kill = false;
    boolean gotLeft = false;
    boolean gotRight = false;
    int timesEaten = 0;

    public void run() {
        while (!kill) {
            try {
                Thread.sleep((long) (random.nextFloat() * time));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            left.waitFor(me);
            gotLeft = true;
            right.waitFor(me);
            gotRight = true;
            timesEaten++;
            try {
                Thread.sleep((long) (random.nextFloat() * time));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            left.put(me);
            gotLeft = false;
            right.put(me);
            gotRight = false;
        }
    }


    public static void main(String args[]) {
        Fork fork[] = new Fork[5];
        for (int n = 0; n < 5; ++n) fork[n] = new Fork(n);
        Philosopher p[] = new Philosopher[5];
        p[0] = new Philosopher(0, fork[4], fork[0]);                        // backwards
        for (int n = 1; n < 5; ++n) p[n] = new Philosopher(n, fork[n - 1], fork[n]);
        for (int n = 0; n < 5; ++n) p[n].start();
        for (int n = 0; n < 100; n++) {
            for (int i = 0; i < 5; i++) {
                System.out.print(p[i].me + " left: " + p[i].gotLeft + " right: " + p[i].gotRight + " eaten: " + p[i].timesEaten + " | ");
            }
            System.out.println("");
            try {
                Thread.sleep((long) (1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 5; i++) {
            p[i].kill = true;
        }
    }
}

