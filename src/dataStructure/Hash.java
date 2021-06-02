package dataStructure;

import jdk.jfr.Unsigned;

import java.util.Arrays;

// 线性探测哈希，好写
public class Hash {
    static final int N = 100003; // 请选择大于数据量两倍的质数
    static final int[] hash = new int[N];
    static final int NULL = 0x3f3f3f3f; // 定义一个数据范围外的数字为 null

    static {
        Arrays.fill(hash, NULL);
    }

    static int hash(int x) {
        return (x % N + N) % N;
    }

    // 线性探测核心操作，找到一个值所在的位置，如果不存在该值则返回此时其应该在的位置
    static int find(int x) {
        int k = hash(x);
        while (hash[k] != NULL && hash[k] != x) k = (k + 1) % N;
        return k;
    }
    // 添加操作
    static void add(int x) {
        hash[find(x)] = x;
    }

    // 查找操作
    static boolean contains(int x) {
        return hash[find(x)] != NULL;
    }

    static void remove(int x) {
        hash[find(x)] = NULL;
    }
}
