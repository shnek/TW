package Lab2.Zad1;
/**
 * Created by kuba on 14.03.15.
 */
public class Starter {


    static int number = 0;
    public static void increase(){
        number = number + 1;
    }
    public static void main(String[] args){
        for(int i = 0; i < 10; i++){
            runThisProgram();
        }
    }
    public static void runThisProgram(){
        MySemaphore semaphore = new MySemaphore();
        Increaser increaser = new Increaser(semaphore);
        Decreaser decreaser = new Decreaser(semaphore);


        increaser.start();
        decreaser.start();


        try {
            increaser.join();
            decreaser.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(number);
    }
    public static void decrease(){
        number = number - 1;
    }
}
