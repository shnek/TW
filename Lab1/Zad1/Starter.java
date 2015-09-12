package Lab1.Zad1;

import java.util.concurrent.ExecutorService;

/**
 * Created by kuba on 14.03.15.
 */
public class Starter {
    public static void main(String[] args){
        System.out.println(Thread.currentThread().getName());
        for(int i = 0; i < 5; i++){
            new Threads().start();
        }

        for(int i = 0; i < 5; i++){
            new RunnableThreads().run();
        }
    }
}
