package other;

public class BitOperation {
    static int lowBit(int n){
        return n & -n;
    }

    // 获取 n 的第 i 位
    static int getBit(int n,int i){
        return n >> i & 1;
    }
}
