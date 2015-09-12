package ProducerConsumer;

import ActiveObject.Future;
import ActiveObject.Proxy;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {

    private Proxy proxy;
    private int maxNumberOfElementsToPut;
    private int totalNumberOfElementsLeft;
    private List<Future> futures = new LinkedList<>();

    public Producer(Proxy proxy, int maxNumberOfElementsToPut, int totalNumberOfElements) {
        this.proxy = proxy;
        this.maxNumberOfElementsToPut = maxNumberOfElementsToPut;
        this.totalNumberOfElementsLeft = totalNumberOfElements;
    }

    @Override
    public void run() {
        int numberOfElements;
        Integer[] elements;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Future result;
        while (totalNumberOfElementsLeft > 0) {
            numberOfElements = totalNumberOfElementsLeft <= maxNumberOfElementsToPut ? totalNumberOfElementsLeft : random.nextInt(maxNumberOfElementsToPut) + 1;
            totalNumberOfElementsLeft -= numberOfElements;
            elements = new Integer[numberOfElements];
            for (int i = 0; i < numberOfElements; i++) {
                elements[i] = random.nextInt(10);
            }
            result = proxy.put(elements);
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
                hasntyet = false;
            }
        }
        if (hasntyet) {
            result.awaitDone();
            temp = 1;
        }
        wynik *= temp;
    }
}
