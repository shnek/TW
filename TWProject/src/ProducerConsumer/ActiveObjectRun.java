package ProducerConsumer;

import ActiveObject.Proxy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ActiveObjectRun {
	private int BUFFER_CAPACITY = 4;
	private int CONSUMER_THREADS = 3;
	private int PRODUCER_THREADS = 3;
	private int ELEMENTS_PER_THREAD = 2;

	private List<Thread> threads;
	private Proxy proxy;
	private FileWriter writer;

	public ActiveObjectRun(int bc, int ct, int pt, int el, FileWriter writer) {
		this.BUFFER_CAPACITY = bc;
		this.CONSUMER_THREADS = ct;
		this.PRODUCER_THREADS = pt;
		this.ELEMENTS_PER_THREAD = el;
		this.threads = new ArrayList<>(CONSUMER_THREADS+PRODUCER_THREADS);
		this.proxy = new Proxy(BUFFER_CAPACITY, ct+pt);
		this.writer = writer;
	}

	public void run(){
		createConsumers();
		createProducers();
		BufferedWriter bufferedWriter = new BufferedWriter(writer);
		long start = System.currentTimeMillis();
		startThreads();
		joinThreads();
		long end = System.currentTimeMillis();
		try {
			bufferedWriter.write("2," + PRODUCER_THREADS + "," + CONSUMER_THREADS + "," + (end - start) + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
				e.printStackTrace();
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
