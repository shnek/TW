package Lab5.Zad443;

import java.util.Random;

/**
 * Created by kuba on 01.04.15.
 */
public class Producent extends Thread {
    private int M;
    private final int time = 1000;
    public int ID;

    Producent(int M, int id) {
        this.M = M;
        this.ID = id;
    }

    private Random random = new Random();

    private int[] dane(int ile) {
        int[] data = new int[ile];
        for (int i = 0; i < ile; i++) data[i] = random.nextInt() % 100;
        return data;
    }

    public void run() {
        while (true) {
            int temp = random.nextInt();
            if (temp < 0) temp *= -1;
            temp %= M;
            if (temp == 0) temp++;
            Main.monitor.wstaw(temp, dane(temp), ID);
            try {
                Thread.sleep((long) (random.nextFloat() * time));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
