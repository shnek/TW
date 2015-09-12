package Lab2.Zad1;


/**
 * Created by kuba on 14.03.15.
 */
public class Decreaser extends Thread {
    MySemaphore semaphore = null;
    public Decreaser(MySemaphore theSemaphore){
        this.semaphore = theSemaphore;
    }
    public void run(){
        for(int i = 0; i < 3000; i++){
            semaphore.take();
            Starter.decrease();
            semaphore.release();
        }
    }
}
