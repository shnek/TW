package ProducerConsumer;

import ActiveObject.Proxy;

import java.util.ArrayList;
import java.util.List;

public class Main {
	private static final int BUFFER_CAPACITY = 5;
	private static final int CONSUMER_THREADS = 10;
	private static final int PRODUCER_THREADS = 10;
	private static final int ELEMENTS_PER_THREAD = 10;

	private List<Thread> threads = new ArrayList<>(CONSUMER_THREADS+PRODUCER_THREADS);
	private Proxy proxy = new Proxy(BUFFER_CAPACITY);
	public static void main(String[] args){
		Main app = new Main();
	}

	public Main() {
		createConsumers();
		createProducers();
		startThreads();
		joinThreads();
	}

	private void startThreads() {
		for(Thread t : threads) {
			t.start();
		}
	}

	private void joinThreads() {
		for(Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// ignore
			}
		}
	}

	private void createProducers() {
		for(int i=0; i<PRODUCER_THREADS; i++) {
			threads.add(new Thread(new Producer(proxy, BUFFER_CAPACITY/2, ELEMENTS_PER_THREAD)));
		}
	}

	private void createConsumers() {
		for(int i=0; i<CONSUMER_THREADS; i++) {
			threads.add(new Thread(new Consumer(proxy, BUFFER_CAPACITY/2, ELEMENTS_PER_THREAD)));
		}
	}

}
