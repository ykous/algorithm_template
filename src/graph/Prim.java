package graph;

import java.util.Arrays;

// prim 算法用于求解最小生成树的权值，适用于稠密图，复杂度 O(n*n)
// prim 算法可以使用堆优化成 O(m * log(n)) 但是完全没有必要，因为稠密图的 m 相当大，而稀疏图使用 kruskal 更高效，并且代码简单
// prim 算法不适合求具体的生成树是那些边，其只用于稠密图求最小生成树权值
public class Prim {
    static final int N = 510, INF = 0x3f3f3f3f;
    static final int[][] map = new int[N][N]; // 稠密图，用矩阵
    static int n, m;

    // 基本思路是，每轮找到一个离当前生成树最近的点，加入生成树集合，并且通过相邻的边更新其他点到当前生成树的距离
    // 如果没有最小生成树（图不连通）则返回 INF
    static int prim() {
        int[] dist = new int[n];
        boolean[] in = new boolean[n];
        Arrays.fill(dist, INF);
        dist[0] = 0;
        int res = 0;
        // 每轮找一个点加入生成树
        for (int i = 0; i < n; i++) {
            // 每次找出不在树中的最近的点
            int from = -1;
            for (int j = 0; j < n; j++)
                if (!in[j] && (from == -1 || dist[j] < dist[from]))
                    from = j;
            if (dist[from] == INF) return INF; // 如果最近的点不可达，则无生成树
            // 否则加入生成树，并且把边的长度记录到结果
            in[from] = true;
            res += dist[from];
            // 更新其他点到树的距离
            for (int to = 0; to < n; to++)
                if (map[from][to] != INF) dist[to] = Math.min(dist[to], map[from][to]);
        }
        return res;
    }
}
