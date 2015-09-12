package Lab1.Zad2;


/**
 * Created by kuba on 14.03.15.
 */
public class Increaser extends Thread {
    public void run(){
        for(int i = 0; i < 3000; i++){
            Starter.increase();
        }
    }
}
