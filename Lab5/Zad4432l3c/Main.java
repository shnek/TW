package Lab5.Zad4432l3c;

/**
 * Created by kuba on 01.04.15.
 */
public class Main {
    private static int pCount = 10;
    private static int cCount = 10;
    static int M = 10;
    public static Monitor monitor = new Monitor(M);

    public static void main(String[] args) {

        Producent p[] = new Producent[pCount];
        Consumer c[] = new Consumer[cCount];
        for (int i = 0; i < cCount; i++) {
            c[i] = new Consumer(M, i);
            c[i].start();
        }
        for (int i = 0; i < pCount; i++) {
            p[i] = new Producent(M, i);
            p[i].start();
        }
        while(true) {
            monitor.buffor.printState();
//            System.out.println("isfirstC: " + monitor.isFC + " isfirstP: " + monitor.isFP);
            try {
                Thread.sleep((long) (1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
