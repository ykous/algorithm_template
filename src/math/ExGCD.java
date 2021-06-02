package math;

// 扩展欧几里得算法，求裴蜀定理等式的特解
public class ExGCD {


    // 也可以顺便求一下 gcd
    static int x,y;
    static void exGCD (int a,int b) {
        if (b == 0) {
            x = 1;
            y = 0;
            return;
        }
        exGCD(b , a % b);
        int t = y;
        y = x - a/b *y;
        x = t;
    }
}
