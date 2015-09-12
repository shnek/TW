package Lab3;

/**
 * Created by kuba on 01.04.15.
 */
public class Monitor {
    private boolean set = false;
    private int i = 0;

    public synchronized void get(){
        while(set == false){
            try {
                notify();
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Got number " + i);
        set = false;
        notify();
    }

    public synchronized void set(int value){
        while(set == true){
            try {
                notify();
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        i = value;
        System.out.println("Set number " + i);
        set = true;
        notify();
    }

}
