package Lab6.Zad444;

import java.util.Random;

/**
 * Created by kuba on 01.04.15.
 */
public class Consumer extends Thread {
    private final int time = 1000;
    private int M;
    public int ID;

    Consumer(int M, int id) {
        this.M = M;
        this.ID = id;
    }

    Random random = new Random();

    public void run() {
        while (true) {
            int temp = random.nextInt();
            if (temp < 0) temp *= -1;
            temp %= M;
            if (temp == 0) temp++;
            Main.monitor.pobierz(1, ID);
//            for(int e: data){
//                System.out.print(e + " ");
//            }
//            System.out.println(" <- this is what consumer got!");
            try {
                Thread.sleep((long) (random.nextFloat() * time));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

