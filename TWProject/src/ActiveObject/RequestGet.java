package ActiveObject;

/**
 * Created by kuba on 06.05.15.
 */
public class RequestGet implements Request {
    private Buffer buffer;
    private FutureGet result;
    private int numberOfElements;

    public RequestGet(Buffer buffer, FutureGet result, int numberOfElements){
        this.buffer = buffer;
        this.result = result;
        this.numberOfElements = numberOfElements;
    }

    @Override
    public boolean canBeExecuted() {
        if(buffer.size() >= numberOfElements){
            return true;
        }else return false;
    }

    @Override
    public void execute() {
        result.setResult(buffer.get(numberOfElements));
    }
}
