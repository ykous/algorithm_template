package math;

/**
 * 质因数分解模板
 */
public class PrimeFactorization {

    public static void main(String[] args) {
        divide(7 * 25);
    }

    static void divide(int n) {
        for (int i = 2; i  <= n / i; i++)
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

    // ============================================

    static final int N = 5000;
    static final int[] primes = new int[N/2];
    static int size;
    /**
     * 对于阶乘而言，我们可以使用传统方法，对每一个数做一次质因数分解然后累加，但是这样太慢
     * 我们可以对先筛出数据范围内的素数，然后使用某个素数的不同次方除 n ，可以获得这个次方下阶乘中有关该次方的倍数的个数
     * 所有的次方倍数的数相加，即可得到这个素数在所有阶乘中数中的幂次之和
     *
     * 时间复杂度可以达到 O(N)
     */
    static void divideArrange(int n){
        // 假设素数已经筛好了
        for (int i = 0; primes[i] <= n && i < size; i++) {
            int s = 0, p = primes[i];
            while (p <= n) {
                s += n/p;
                p *= primes[i];
            }
            exec(primes[i],s);
        }
    }
}
