package Lab4.Zad2Version1;



/**
 * Created by kuba on 07.04.15.
 */
public class Kolejka {
    int first, noe;
    private int[] array;
    int size;

    public Kolejka(int N) {
        size = N;
        array = new int[N];
        first = 0;
        noe = 0;
    }

    public int haveItems() {
        return noe;
    }


    public void insertData(int data) {
        array[(first + noe) % size] = data;
        noe++;
        Main.m.finish((first + noe) % size);
    }

    public int getData() {
        int data;
        data = array[first];
        array[first] = 0;
        if (first == size - 1) {
            first = 0;
        } else {
            first = first + 1;
        }
        noe--;
        return data;
    }

    public void printState() {
        System.out.println("First is: " + first + " Number of elements is: " + noe);
        for (int e : array) {
            System.out.print(" | " + e);
        }
        System.out.println(" |");
    }

}
