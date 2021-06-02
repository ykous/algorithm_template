package math;

// 最小公倍数
public class GCD {

    // 任何数与 0 的最小公倍数就是自身，所以 j==0 时 结果为 i
    // 任意自然数的最大公约数 (a,b) == (b,a mod b)
    // 显然 b > a mod b 恒成立，所以只有第一次递归有可能 a < b
    static int gcd(int i, int j) {
        return j == 0 ? i : gcd(j, i % j);
    }
}
