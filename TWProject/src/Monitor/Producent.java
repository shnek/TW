package Monitor;

import java.util.Random;

public class Producent extends Thread {
    public int max;
    private Monitor monitor;
    private int totalNumberOfElementsLeft;

    Producent(int M, Monitor monitor, int noe) {
        this.max = M;
        this.monitor = monitor;
        this.totalNumberOfElementsLeft = noe;
    }

    private Random random = new Random();

    private int[] dane(int ile) {
        int[] data = new int[ile];
        for (int i = 0; i < ile; i++) data[i] = random.nextInt() % 100;
        return data;
    }

    public void run() {
        int numberOfElements;
        while(totalNumberOfElementsLeft > 0) {
            numberOfElements = totalNumberOfElementsLeft <= max ? totalNumberOfElementsLeft : random.nextInt(max)+1;
            totalNumberOfElementsLeft -= numberOfElements;

            monitor.wstaw(numberOfElements, dane(numberOfElements));
        }
    }
}
