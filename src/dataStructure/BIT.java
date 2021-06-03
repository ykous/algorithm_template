package dataStructure;

// 树状数组
public class BIT {
    static final int N = 100010;
    static final int[] tree = new int[N];

    // 查询原数组 [0,i) 区间的运算和
    static int query(int i) {
        int res = 0;
        while (i > 0) {
            res += tree[i];
            i -= lowbit(i);
        }
        return res;
    }

    // 更新原数组下标为 i 的元素，使其加上 delta
    static void update(int i,int delta) {
        i++;
        while (i < N) {
            tree[i] += delta;
            i += lowbit(i);
        }
    }

    static int lowbit(int n) {
        return n & -n;
    }
}
