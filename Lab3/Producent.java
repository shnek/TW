package Lab3;

import java.util.Random;

/**
 * Created by kuba on 01.04.15.
 */
public class Producent extends Thread{
    private final int time = 1000;
    public void run(){
        Random random = new Random();

        while(true){
            Main.m.set(random.nextInt());
            try {
                Thread.sleep((long) (random.nextFloat() * time));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
