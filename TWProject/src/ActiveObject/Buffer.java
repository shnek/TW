package ActiveObject;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by kuba on 06.05.15.
 */
public class Buffer {
    private Deque<Integer> buffer;
    private int capacity;

    public Buffer(int capacity){
        this.buffer = new ArrayDeque<>(capacity);
        this.capacity = capacity;
    }
    private void printState(){
        for(int e: buffer){
            System.out.print("| " + e + "| ");
        }
        System.out.println("");
    }
    public void put(Integer[] elements){
        for(Integer i : elements){
            buffer.add(i);
//            System.out.print(i + " ");
        }
//        System.out.println("inserted to buffor");
//        printState();
    }
    public Integer[] get(int numberOfElements){
        Integer[] result = new Integer[numberOfElements];
        for(int i = 0; i < result.length; i++){
            result[i] = buffer.pollFirst();
//            System.out.println(result[i] + " ");
        }
//        System.out.println("got from buffor");
//        printState();
        return result;
    }
    public int size(){
        return buffer.size();
    }
    public int capacity(){
        return capacity;
    }
}
