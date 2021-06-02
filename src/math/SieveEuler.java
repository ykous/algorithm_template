package math;

// 线性筛法求范围内每个数的欧拉函数值
// 可以在 O(n) 范围内求出所有数的欧拉函数值
// 线性筛法由于对于每一个数都只遍历一次，因此作为一种素数合数区分的遍历方式，在数论中非常常用
public class SieveEuler {

    static final int N = 1000010;
    static final int[] euler = new int[N], primes = new int[N];
    static int size = 0;
    static boolean[] sieve = new boolean[N];

    static void sieveEuler(int n) {
        euler[1] = 1; // 根据定义初始化
        for (int i = 2; i <= n; i++) {
            if (!sieve[i]) {
                primes[size++] = i;
                euler[i] = i - 1; // 素数的欧拉函数为自身-1
            }
            for (int j = 0; primes[j] <= n / i; j++) {
                sieve[primes[j] * i] = true;
                if (i % primes[j] == 0) {
                    // 这个更新公式的推导为
                    // 此时 euler[i] 要么作为素数被上面的 if 定义，要么作为合数被之前的某个循环更新过，因此必定已经得出
                    // 根据 i % primes[j] == 0 ，此时二者的欧拉公式除了 N 不同，后面的部分完全相同
                    // euler[i] = i * (剩余部分) ，euler[primes[j]*i] = primes[j]*i * (剩余部分)
                    // 因此显而易见
                    euler[i * primes[j]] = euler[i] * primes[j];
                    break;
                }
                // 这里的 primes[j] 必然不属于 i 的素数分解中
                // 因此有 euler[i] = i * (剩余部分)
                // euler[primes[j]*i] = primes[j]*i * (剩余部分) * (primes[j] - 1) / primes[j]
                // 约分后得下列公式
                euler[i * primes[j]] = euler[i] * (primes[j] - 1);
            }
        }
    }
}
