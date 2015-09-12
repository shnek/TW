package Lab4.Zad1;

/**
 * Created by kuba on 01.04.15.
 */
public class Main {
    private static int pCount = 5;
    private static int cCount = 5;
    public static Monitor m = new Monitor();
    public static void main(String[] args){

        Producent p[] = new Producent[pCount];
        Consumer c[] = new Consumer[cCount];
        for(int i = 0; i < cCount; i++){
            c[i] = new Consumer(i);
            c[i].start();
        }
        for(int i = 0; i < pCount; i++){
            p[i] = new Producent(i);
            p[i].start();
        }
    }
}
