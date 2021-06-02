package math;

// 卡特兰数是一个非常常用的模型，对于符合模型的问题可以直接抽象成公式求解
// 具体推导看 md 最终得出公式为 C(2n,n) - C(2n,n-1) = C(2n,n)/n+1
// 查看例题 AcWing 889 https://www.acwing.com/problem/content/891/
public class Catalan {

    // 就是小改组合数，占个位置，主要是理解模型和背好公式
    static long catalan(int n,int p) {
        long m = 1,d = 1;
        for (int i = 1,j = 2*n; i <= n; i++,j--) {
            m = m * j % p;
            d = d * i % p;
        }
        d = d * (n+1) % p;
        return m * fp(d,p-2,p);
    }

    private static long fp(long base, int pow, int p) {
        long res = 1 % p,t = base;
        while (pow != 0) {
            if((pow & 1)== 1) res = res * t % p;
            t = t* t % p;
            pow >>= 1;
        }
        return res;
    }
}
