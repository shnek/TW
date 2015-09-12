package Monitor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class MonitorRun {
    private  int pCount;
    private  int cCount;
    private int M;
    public  Monitor monitor;
    private int noe;
    private List<Thread> threads;
    private FileWriter writer;

    public MonitorRun(int pcount, int ccount, int m, int noe, FileWriter writer){
        this.pCount = pcount;
        this.cCount = ccount;
        this.M = m;
        this.monitor = new Monitor(M);
        this.noe = noe;
        this.threads = new ArrayList<>(pcount + ccount);
        this.writer = writer;
    }

    public void run(){
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        createConsumers();
        createProducers();
        long start = System.currentTimeMillis();
        startThreads();
        joinThreads();
        long end = System.currentTimeMillis();
        try {
            bufferedWriter.write("1," + pCount + "," + cCount + "," + (end - start) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("M - P: " + pCount + " C: " + cCount + " time: " + (System.currentTimeMillis() - start));
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
        for(int i=0; i<pCount; i++) {
            threads.add(new Thread(new Producent(M, monitor, noe)));
        }
    }

    private void createConsumers() {
        for(int i=0; i<cCount; i++) {
            threads.add(new Thread(new Consumer(M, monitor, noe)));
        }
    }

}
