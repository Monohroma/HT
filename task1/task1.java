import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        m--;
        int l = 0;
        do {
            System.out.print(l+1);
            l += m;
            l = l % n;
        } while(l!=0);
    }
}