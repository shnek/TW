package ProducerConsumer;

import ActiveObject.Future;
import ActiveObject.Proxy;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Consumer implements Runnable {

    private Proxy proxy;
    private int maxNumberOfElementsToGet;
    private int totalNumberOfElementsLeft;
    private List<Future> futures = new LinkedList<>();

    public Consumer(Proxy proxy, int maxNumberOfElementsToGet, int totalNumberOfElements) {
        this.proxy = proxy;
        this.maxNumberOfElementsToGet = maxNumberOfElementsToGet;
        this.totalNumberOfElementsLeft = totalNumberOfElements;
    }

    @Override
    public void run() {
        int numberOfElements;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        while (totalNumberOfElementsLeft > 0) {

            numberOfElements = totalNumberOfElementsLeft <= maxNumberOfElementsToGet ? totalNumberOfElementsLeft : random.nextInt(maxNumberOfElementsToGet) + 1;
            totalNumberOfElementsLeft -= numberOfElements;
            Future result = proxy.get(numberOfElements);
            processData(result);
        }

    }

    private void processData(Future result) {
        int wynik = 0;
        boolean hasntyet = true;
        int temp = 0;
        for (int i = 0; i < 999; i++) {
            wynik += 9;
            if (result.done() && hasntyet) {
                temp = calculateResult(result);
                hasntyet = false;
            }
        }
        if (hasntyet) {
            result.awaitDone();
            temp = calculateResult(result);
        }
        wynik *= temp;
    }

    private int calculateResult(Future result) {
        int temp = 0;
        Integer[] wynik = (Integer[]) result.get();
        for (int e : wynik) {
            temp += e * e + e;
        }
        return temp;
    }


}
