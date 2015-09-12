package ActiveObject;

/**
 * Created by kuba on 06.05.15.
 */
public class FutureGet implements Future {
    private Integer[] result;

    public synchronized  boolean done(){
        return result != null;
    }

    public synchronized Integer[] get(){
        if(result == null){
            throw new IllegalStateException();
        }
        return result;
    }

    public synchronized void setResult(Integer[] result){
        this.result = result;
        notify();
    }

    public synchronized void awaitDone(){
        while(!done()){
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
