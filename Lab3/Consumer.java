package Lab3;

/**
 * Created by kuba on 01.04.15.
 */
public class Consumer extends Thread{

    public void run(){
        while(true) Main.m.get();
    }
}

