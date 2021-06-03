package dataStructure;

// 基础线段树，无区间修改
public class SegmentTree {
    // 线段树的形态是非常接近完美二叉树的形态，其至多会产生一个的冗余
    // 所以我们以数组的形式存储，需要的数组长度根据序列的长度略有不同，其上限是长度的四倍
    static int N = 100010*4, ROOT = 0;
    static int[] tree = new int[N]; // 存储区间的值，在本例中的值是区间最大值
    static int n; // 线段树处理的序列的长度，必须指定

    static void init(int num) {
        n = num;
    }

    // 更新原序列中坐标 i 的值为 v
    // 初始状态默认为全0序列，通过 update 达到初始化的效果
    static void update(int i, int v) {
        update(ROOT, i, v, 0, n);
    }

    // left 和 right 表示当前节点所代表的区间，i 表示更新的点
    static void update(int node, int i, int val, int left, int right) {
        if (i == left && i + 1 == right) {
            tree[node] = val;
            return;
        }
        int mid = left + right >> 1;
        if (i < mid) update(node * 2 + 1, i, val, left, mid);
        else update(node * 2 + 2, i, val, mid, right);
        tree[node] = Math.max(tree[node * 2 + 1], tree[node * 2 - +2]); // 该步骤为不同题目的主要修改点
    }

    static int query(int l, int r) {
        return query(ROOT, l, r, 0, n);
    }

    static int query(int node, int l, int r, int left, int right) {
        if (l == left && r == right) return tree[node];
        int mid = left + right >> 1;
        if (r <= mid) return query(node * 2 + 1, l, r, left, mid);
        if (l >= mid) return query(node * 2 + 2, l, r, mid, right);

        // 主要修改点
        return Math.max(
                query(node * 2 + 1, l, mid, left, mid),
                query(node * 2 + 2, mid, r, mid, right)
        );
    }
}
