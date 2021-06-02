package other;

public class BitOperation {
    static int lowBit(int n){
        return n & -n;
    }

    // 获取 n 的第 i 位
    static int getBit(int n,int i){
        return n >> i & 1;
    }

    // 0 -> 1 , 1 -> 0 由于 Java 没有 ! 操作符，就拿这个代替
    static int negation(int num){
        return num ^ 1;
    }
}
