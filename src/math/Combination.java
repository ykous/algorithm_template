package math;

import java.math.BigInteger;

// 组合数的模板
public class Combination {
    public static void main(String[] args) {
        preprocessArrange();
        preprocessCombination();
        System.out.println(c(10,5));
        System.out.println(c[10][5]);
        System.out.println(c(1234,123));
        System.out.println(c[1234][123]);
    }
    /**
     * a > b
     * 最基本的模板，适用于组合次数不多且数据量较小的情况
     *
     * 如果必要还可以加一步判断，如果 m > n/2 那么 n = m - n
     */
    static int combination(int a,int b) {
        int res = 1;
        for(int i = 1, j = a;i <= b;i++,j--)
            res = res * j / i;
        return res;
    }

    //=================================================

    static final int N = 2000,MOD = (int) (1e9+7);
    static long[][] c = new long[N][N];

    /**
     * 预处理出范围 n 以内的所有组合数，适用于小范围数据的多次查询操作
     * 算法是动态规划，状态转移是
     * C(a,b) = C(a-1,b-1) + C(a-1,b)
     * 复杂度 O(n*n)
     * 由于数据可能很大，通常模取一个质数
     *
     * 并不是一个常用的算法，因为数据量受限，不管的空间还是时间都不允许
     */
    static void preprocessCombination() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) c[i][j] = 1;
                else c[i][j] = (c[i-1][j-1] + c[i-1][j]) % MOD;
            }
        }
    }

    // =========================================

    static long[] fact = new long[N]; // 阶乘模取 fact[i] 表示 i!
    static long[] inFact = new long[N]; // 阶乘模取逆元

    /**
     * 更加常用的算法，预处理出范围内所有阶乘的模取值和逆元的模取值，在需要时使用定义计算
     * 时间复杂度为 O(n * log P) P 是模取的质数 空间复杂度为 O(n) 因此可以兼容百万以内的数据预处理
     */
    static void preprocessArrange() {
        fact[0] = 1;
        inFact[0] = 1;
        for (int i = 1; i < N; i++) {
            fact[i] = fact[i-1]*i%MOD;
            inFact[i] = fp(fact[i],MOD-2); // 快速幂，费马小定理求逆元
        }
    }

    // 套公式求 C(a,b)
    static long c(int a,int b) {
        return fact[a]*inFact[b]%MOD*inFact[a-b]%MOD;
    }

    static long fp(long base,long pow) {
        long res = 1 % MOD,t = base;
        while (pow != 0) {
            if ((pow & 1)== 1) res = res * t % MOD;
            t = t*t%MOD;
            pow >>= 1;
        }
        return res;
    }

    // ==========================================

    /**
     * 卢卡斯定理多用于数据范围极大但是数据的查询次数有限的组合数运算
     * 定理的内容是 C(a,b) 同余 C(a%p,b%p) * C(a/p,b/p)
     * 根据这个定理我们可以递归的求解超大数的组合数
     *
     * 请注意模取值 p 不会太大，因为无法预处理，我们需要 O(n) 的时间求解每一个 C 的值
     *
     * 时间复杂度为 O(log(max(a,b)) * p)
     */
    static long lucas(long a,long b,long p){
        if(a < p && b < p) return c(a,b,p);
        return c(a % p,b % p, p) * lucas(a / p,b / p,p) % p;
    }

    // 采用第一种方法，并使用快速幂计算分母的逆元，其实比纯粹的第一种有用
    static long c(long a,long b,long p) {
        long m = 1, d = 1;
        for(long i = 1,j = a;i <= b;i++,j--){
            m = m * j % p;
            d = d * i % p;
        }
        return m * fp(d,p-2,p) % p;
    }

    static long fp(long base,long pow,long p) {
        long res = 1 % p,t = base;
        while (pow != 0) {
            if ((pow & 1)== 1) res = res * t % p;
            t = t*t%p;
            pow >>= 1;
        }
        return res;
    }


    // ============================

    static final int[] primes = new int[N];
    static final int size = 200;
    /**
     * 高精度组合数，比较少用，用于结果不取模的情况
     * 可以使用常规的计算方法（第一种）但是高精度的运算比较慢，最好使用更快的算法
     * 基本思路是，把分子分母中的阶乘质因数分解（使用阶乘快速分解）
     * 然后上下约掉次数，最后使用剩余的次数再进行高精度运算
     * （这里没有使用阶乘分解模板，而是适配了一下，存起来代码太长，所以选择当场计算当场使用）
     */
    static BigInteger highPrecisionCombination(int a,int b) {
        // 假设素数已经求好
        BigInteger res = BigInteger.ONE;
        for (int i = 0; i < size; i++) {
            int pow = 0; // 记录当前这个素数的次数
            pow += primesPow(a,primes[i]); // 加上分子，关于素数 primes[i] 的次数
            pow -= primesPow(b,primes[i]);
            pow -= primesPow(a-b,primes[i]);
            // 如果有必要还是可以手写高精度的，因为 BigInteger 每一次都产生新的对象，太慢了
            for (int j = 0; j < pow; j++) {
                res = res.multiply(BigInteger.valueOf(primes[i]));
            }
        }
        return res;
    }

    static int primesPow(int a,int p) {
        int t = p,res = 0;
        while (t <= a) {
            res += a / t;
            t *= p;
        }
        return res;
    }

}
