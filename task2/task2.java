import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class task2 {
    public static float Distance(float p1, float p2, float p3, float p4)
    {
        return (float)Math.sqrt(Math.pow(p1 - p3, 2) + Math.pow((p2 - p4), 2));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sfile1 = scanner.nextLine();
        String sfile2 = scanner.nextLine();

        File file1 = new File(sfile1);
        File file2 = new File(sfile2);
        Scanner scanner1;
        Scanner scanner2;
        try {
            scanner1 = new Scanner(file1);
            scanner2 = new Scanner(file2);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        float p1 = scanner1.nextFloat(), p2 = scanner1.nextFloat();
        float r = scanner1.nextFloat();

        float rt;
        while(scanner2.hasNext())
        {
            rt = Distance(p1, p2, scanner2.nextFloat(), scanner2.nextFloat());
            if(rt == r)
            {
                System.out.println(0);
            } else if (rt > r) {
                System.out.println(2);
            }
            else
            {
                System.out.println(1);
            }
        }
    }
}
