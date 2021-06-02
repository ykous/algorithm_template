package math;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 约数之和，通常用于求一个大数的约数之和
// 对于任意自然数表示成质因数分解 P1^x1 * P2^x2 *...* Pn^xn，其中各项以不同幂次的组合即是该数的各个约束
// 那么就有公式 (P1^0 + P1^1 + ... + P1 ^ x1) *...* (Pn^0 + Pn^1 + ... + Pn ^ xn) 即是所有约束之和
// 不需要证明，只需要把上式展开，各项就是所有的约数
public class DivisorSum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        // 一个大数用多个数相乘来表示
        // 把每一个数的质因数分解累积就是大数的分解
        for(int i = 0;i < n;i++) {
            pf(in.nextInt());
        }
        // 通常来说，结果很大，需要模取一个数
        long res = 1;
        for (int base : map.keySet()){
            long temp = 1;
            int pow = map.get(base);
            for (int i = 0; i < pow; i++) temp = (temp * base + 1) % mod; // 秦九韶算法来求
            res = res*temp%mod;
        }
        System.out.println(res);
    }

    static final int mod = (int) (1e9 + 7);

    static void pf(int x) {
        for (int i = 2; i <= x / i; i++)
            if (x % i == 0) {
                int s = 0;
                while (x % i == 0) x /= i;
                exec(x, s);
            }
        if (x > 1) exec(x,1);
    }

    static Map<Integer,Integer> map = new HashMap<>();

    static void exec(int base, int pow) {
        Integer t = map.get(base);
        map.put(base,pow + (t == null ? 0 : t));
    }
}
