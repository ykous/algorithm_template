package math;

// 线性筛法求素数
// 埃氏筛法的复杂度是 O(n * log log n) 虽然也很低，但是线性筛法是更高级数学算法的基础，必须掌握
// 线性筛法之所以重要并且用途广泛的原因，是它可以对范围内的每一个数只访问一边
// 同时还可以区别开素数和合数的访问
public class SievePrime {

    static final int N = 1000010;
    static final int[] primes = new int[N/2]; // 记录素数
    static int size = 0; // 素数个数
    static final boolean[] sieve = new boolean[N]; // 记录数是否被筛掉

    static void sieve(int n){
        for (int i = 2; i <= n; i++) {
            if (!sieve[i]) primes[size++] = i; // 如果没被筛掉则记录到素数集合

            // 线性筛法的核心，我们遍历记录的素数，显然，这是从小到大的遍历
            // for 循环中的判断条件，是方式 sieve 数组越界，并不是真实的退出循环的判断，真实的判断是下面的 if
            for(int j = 0; primes[j] <= n/i;i++) {
                sieve[primes[j] * i] = true;
                // 当 primes[j] 可以整除 i 的时候，说明之后的 primes[j] 不会是 primes[j] * i 的最小质因数了
                // 因此我们退出循环，这将导致，每一次我们执行 sieve[primes[j] * i] = true 的时候
                // 必定保证了 primes[j] 是 primes[j] * i 的最小的质因数，因此我们可以把后者分割成两个部分
                // 例如面对 15 我们就可以筛掉 (3) * (5^2)
                // 显然，对于每一次的 primes[j] * i 必定不会出现重复的素数分解组合
                // 因为 primes[j] 与 i 这两个数的组合首先就不会出现重复
                // 因此如果完全不同的组合相乘诞生的同样的质数分解，必然表示 primes[j] 不是其中最小的质因数
                // 因此最终的结果就是，从 2 到 n 的每一个数，都必然且仅被筛到一次
                if (i % primes[j] == 0) break;
            }
        }
    }
}
