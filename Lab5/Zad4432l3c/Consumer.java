package Lab5.Zad4432l3c;

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
//            int data = Main.monitor.pobierz(temp, ID);
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

