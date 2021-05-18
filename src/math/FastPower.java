package math;

// 简单的快速幂
public class FastPower {
    static int power(int base, int power) {
        // 代码的逻辑是，幂次数可以分解成 2 的幂次数之和
        // 从而把 2 的 7 次方，分解成 2^1 * 2^2 * 2^4 ，的组合
        // 下列的代码就是如此，t表达的是组合中的某一个数
        int res = 1, t = base;
        while (power > 0) {
            if ((power & 1) == 1) res *= t;
            t *= t;
            power >>= 1;
        }
        return res;
    }

    // 更多用的情况，保留模取的结果
    static int power(int base, int power, int mod) {
        int res = 1 % mod /* 兼容 mod = 1 */, t = base;
        while (power > 0) {
            if ((power & 1) == 1) res = res * t % mod;
            t = t * t % mod;
            power >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(power(2, 33,1));
        System.out.printf("%.6f",1e-6);
    }
}
