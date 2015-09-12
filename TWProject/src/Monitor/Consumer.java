package Monitor;

import java.util.Random;

public class Consumer extends Thread {

    public int max;
    private Monitor monitor;
    private int totalNumberOfElementsLeft;

    Consumer(int M, Monitor monitor, int noe) {

        this.max = M;
        this.monitor = monitor;
        this.totalNumberOfElementsLeft = noe;
    }

    Random random = new Random();

    public void run() {
        int numberOfElements;
        while(totalNumberOfElementsLeft > 0) {

            numberOfElements = totalNumberOfElementsLeft <= max ? totalNumberOfElementsLeft : random.nextInt(max)+1;
            totalNumberOfElementsLeft -= numberOfElements;
            int[] data = monitor.pobierz(numberOfElements);
            processData(data);
        }
    }
    private void processData(int[] data){
        int result = 0;
        for(int i = 0; i < 999; i++){
            result += 9;
        }
        int temp = 0;
        for(int e : data){
            temp += e*e+e;
        }
        result *= temp;
    }
}

