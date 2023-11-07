import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class task4 {
    public static ArrayList<Integer> vals = new ArrayList<>();
    public static ArrayList<Integer> indVals = new ArrayList<>();
    public static int DistanceTo(int i)
    {
        int rez = 0;
        for (int j: vals) {
            rez += Math.abs(j - i);
        }
        return rez;
    }

    public static void main(String[] args) {
        String fileU = args[0];
        File file = new File(fileU);
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int t;

        while(fileScanner.hasNextInt())
        {
            t = fileScanner.nextInt();
            vals.add(t);
            if(!indVals.contains(t))
                indVals.add(t);
        }

        t = Integer.MAX_VALUE;

        for (int i: indVals) {
            t = Math.min(t, DistanceTo(i));
        }

        System.out.print(t);
    }
}