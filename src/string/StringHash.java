package string;


// 字符串哈希
// 哈希作为一种概率算法，有时候可以轻松解决一些奇葩问题
// 例如其本身解决 kmp 问题可以达到 O(n) 级别，但是常数较大而且对字符串长度有限
// 过长的字符串会导致哈希映射出错的概率增加

// 字符串哈希本身的基本思路就是把字符串定义成 P 进制的数，同时对大数取余

// 该篇还提供了对一个字符串的任意子串计算哈希值的方式
public class StringHash {

    // 计算hash全部用long
    static final long P = 131;
    static final long Q = (int) (1e9 + 7);

    static long getHash(char[] chars) {
        long res = 0;
        for (char c : chars) {
            res = (res * P + c) % Q;
        }
        return res;
    }

    // 一下是获取字符串任意子串的哈希值模板

    static long[] hash;
    static long[] power; // P 的每一幂次取模数值

    static void init(char[] chars) {
        hash = new long[chars.length + 1];
        power = new long[chars.length + 1];
        power[0] = 1;
        for (int i = 0; i < chars.length; i++) {
            hash[i+1] = (hash[i] * P + chars[i]) % Q;
            power[i+1] = power[i] * P % Q;
        }
    }

    // 查询该字符串在 [l,r) 区间内子串的哈希值
    static long query(int l,int r) {
        return ((hash[r] - hash[l] * power[r - l]) % Q + Q) % Q;
    }

}
