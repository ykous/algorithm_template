package math;

// 试除法判定质数
public class PrimeDetermined {
    static boolean isPrime(int x) {
        if(x < 2) return false;
        for(int i = 2;i <= x/i;i++) if (x % i == 0) return false;
        return true;
    }
}
