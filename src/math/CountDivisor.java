package math;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 计算一个数的约数个数通常用于求一个非常大的数，这个数通常以多个数的乘积来表示
 * 关键就在记住一个公式：
 * <p>
 * 设一个数的质数分解为 <b>P1^x1 * P2^x2 *...* Pn^xn</b> 则其约束个数为 <b>(x1 + 1) * (x2 + 1) *...* (xn + 1) </b>
 *
 * 原理是一个数的任何一个约束都可以是 <b>P1^x1 * P2^x2 *...* Pn^xn</b> 中素数的一个组合，
 * 则我们可以从 P1 中拿 [0,x1] 个数，P2 中拿 [0,x2] 个数，以此类推，
 * 则对于 P1 有 x1 + 1 种选取的可能性，P 有 x2 + 1 种选取的可能性
 */
public class CountDivisor {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        // 通常用多个数的乘积来表达一个大数
        // 这个大数的质因数分解可以由这些数的质因数分解累积而得
        for (int i = 0; i < n; i++) {
            pf(in.nextInt());
        }
        // 最后根据公式计算，由于这个数字很大，通常需要模取一个数
        long res = 1;
        for (int t : map.values()) res = res * (t + 1) % mod;
        System.out.println(res);
    }

    static final int mod = (int) (1e9 + 7);

    // 质因数分解模板
    static void pf(int x) {
        for (int i = 2; i <= x / i; i++)
            if (x % i == 0) {
                int s = 0;
                while (x % i == 0) {
                    x /= i;
                    s++;
                }
                exec(i, s);
            }
        if (x > 1) exec(x,1);
    }

    static final Map<Integer,Integer> map = new HashMap<>();

    private static void exec(int base, int pow) {
        Integer t = map.get(base);
        map.put(base,pow + (t == null ? 0 : t)); // 累计每一个因数的次数
    }


}
