import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        m--;
        int l = 0;
        do {
            System.out.print(l+1);
            l += m;
            l = l % n;
        } while(l!=0);
    }
}