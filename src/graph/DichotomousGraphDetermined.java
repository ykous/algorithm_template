package graph;

// 染色法判定二分图
// 二分图的等价条件是，图中不存在奇数长度的回路
// 根据这条性质，我们可以定义任意路径中的节点是黑白交替的颜色，并进行 dfs
// 在 dfs 的过程中，每一次我们都要定义当前的节点颜色，如果当前节点已经被染色
// 则看看其颜色与我们给其定义的颜色是否一致，如果不一致则必定出现了奇数长度的回路
// 也可以使用 bfs ，原理都是预判子节点的颜色是否满足预期
public class DichotomousGraphDetermined {
    static final int N = 100010, NULL = 0;
    static final int[] head = new int[N], e = new int[N], ne = new int[N];
    static int idx = 1;
    static int n, m;
    static int[] color = new int[N]; // 定义 0 为还未染色， 1 为白色， -1 为黑色

    static boolean determine() {
        boolean res = true;
        // 二分图不一定是连通图，因此我们需要对每一个连通分支判断其中回路是否满足条件
        for (int i = 0; i < n; i++)
            if (color[i] == 0) res &= stain(i, 1);
        return res;
    }


    static boolean stain(int node, int c) {
        if (color[node] != 0) return c == color[node]; // 如果已经染色，看看颜色是不是和预期的一样
        color[node] = c;
        // 递归看邻接点，如果任何一次行为发现了奇数环，则返回 false
        for (int p = head[node]; p != NULL; p = ne[p])
            if (!stain(e[p], -c))
                return false;
        return true;
    }
}
