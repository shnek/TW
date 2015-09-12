package ActiveObject;

/**
 * Created by kuba on 06.05.15.
 */
public interface Request  {
    boolean canBeExecuted();
    void execute();
}
