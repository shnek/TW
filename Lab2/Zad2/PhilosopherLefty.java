package Lab2.Zad2;

/**
 * Created by kuba on 18.03.15.
 */

import java.util.Random;

public class PhilosopherLefty extends Thread {

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

    public PhilosopherLefty(int me, Fork left, Fork right, boolean lefty) {
        this.me = me;
        this.left = left;
        this.right = right;
        this.isLefty = lefty;
    }

    protected static Random random = new Random();
    protected int me;
    protected int time = 3;
    protected Fork left, right;
    boolean kill = false;
    boolean gotLeft = false;
    boolean gotRight = false;
    int timesEaten = 0;
    boolean isLefty;

    public void run() {
        while (!kill) {
            try {
                Thread.sleep((long) (random.nextFloat() * time));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isLefty) {
                right.waitFor(me);
                gotRight = true;
                left.waitFor(me);
                gotLeft = true;
                timesEaten++;
                try {
                    Thread.sleep((long) (random.nextFloat() * time));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                right.put(me);
                gotRight = false;
                left.put(me);
                gotLeft = false;
            } else {
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
    }


    public static void main(String args[]) {
        Fork fork[] = new Fork[5];
        for (int n = 0; n < 5; ++n) fork[n] = new Fork(n);
        PhilosopherLefty p[] = new PhilosopherLefty[5];
        p[0] = new PhilosopherLefty(0, fork[4], fork[0], true);
        for (int n = 1; n < 5; ++n) p[n] = new PhilosopherLefty(n, fork[n - 1], fork[n], false);
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

