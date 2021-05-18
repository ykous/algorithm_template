package math;

/**
 * 质因数分解模板
 */
public class PrimeFactorization {

    public static void main(String[] args) {
        divide(7 * 25);
    }

    static void divide(int n) {
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0) {
                int t = 0;
                while (n % i == 0) {
                    t++;
                    n /= i;
                }
                exec(i, t);
            }
        if (n > 1) exec(n, 1);
    }

    private static void exec(int i, int t) {
        System.out.println(i + " " + t);
    }
}
