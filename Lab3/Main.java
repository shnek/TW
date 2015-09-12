package Lab3;

/**
 * Created by kuba on 01.04.15.
 */
public class Main {
    private static int pCount = 100;
    private static int cCount = 1000;
    public static Monitor m = new Monitor();
    public static void main(String[] args){

        Producent p[] = new Producent[pCount];
        Consumer c[] = new Consumer[cCount];
        for(int i = 0; i < cCount; i++){
            c[i] = new Consumer();
            c[i].start();
        }
        for(int i = 0; i < pCount; i++){
            p[i] = new Producent();
            p[i].start();
        }
    }
}
