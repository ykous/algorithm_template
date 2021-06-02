package math;

/**
 * 欧拉函数求数比 N 小且与之互质的数的个数
 * <p>
 * 公式为 f(N) = N * (p1 - 1) / p1 * (p2 - 1) / p2 ..... (pn - 1) / pn 其中 pi 表示该数的各个质因子
 */
public class Euler {


    // 根据公式，只需要跑一边质因数分解模板即可
    static int euler(int n) {
        int res = n;
        for (int i = 2; i <= n / i; i++)
            if (n % i == 0) {
                while (n % i == 0) n /= i;
                res = res / i * (i - 1); // 推荐先除再乘
            }
        if (n > 1) res = res / n * (n - 1);
        return res;
    }


}
