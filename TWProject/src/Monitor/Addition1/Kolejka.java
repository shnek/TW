package Monitor.Addition1;

/**
 * Created by kuba on 07.04.15.
 */
public class Kolejka {
    int first, noe;
    private int[] array;
    int size;
    public Kolejka(int N){
        size = N;
        array = new int[N];
        first = 0;
        noe = 0;
    }

    public int haveItems(){
        return noe;
    }


    public void insertData(int ile, int data[]){
        for(int i = 0; i < ile; i++){
            array[(first + noe)%size] = data[i];
            noe++;
        }
    }

    public int[] getData(int ile){
        int data[] = new int[ile];
        for(int i = 0; i < ile; i++){
            data[i] = array[first];
            array[first] = 0;
            if(first == size-1){
                first = 0;
            }else{
                first = first + 1;
            }
            noe--;
        }
        return data;
    }
    public void printState() {
        System.out.println("First is: " + first + " Number of elements is: " + noe);
        for(int e: array){
            System.out.print(" | " + e);
        }
        System.out.println(" |");
    }

}
