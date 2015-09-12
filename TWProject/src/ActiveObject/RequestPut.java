package ActiveObject;

/**
 * Created by kuba on 06.05.15.
 */
public class RequestPut implements  Request{

    private Buffer buffer;
    private FuturePut result;
    private Integer[] elements;

    public RequestPut(Buffer buffer, FuturePut result, Integer[] elements){
        this.buffer = buffer;
        this.result = result;
        this.elements = elements;
    }
    @Override
    public boolean canBeExecuted() {
        if(buffer.size() + elements.length <= buffer.capacity()){
            return true;
        }else return false;
    }

    @Override
    public void execute() {
        buffer.put(elements);
        result.markAsDone();
    }
}
