package Lab4.Zad2Version1;

/**
 * Created by kuba on 01.04.15.
 */
public class Consumer extends Thread{

    Consumer(int i){
        System.out.println("Starting consumer "+i);
    }
    public void run(){
        while(true) Main.m.get();
    }
}

