package graph;

import java.util.Arrays;

/**
 * bellman-ford 算法不是用于求最短路的，因为 dijkstra 或 spfa 都远比之高效
 * 其真真的作用是求 ”最多经过 k 条边的最短路径“
 * <p>
 * 基本思路是，遍历 k 次，每次遍历所有的边，从而尝试通过这些边能否更新最短路
 */
public class BellmanFord {
    // bellman-ford 不需要邻接表，只需要一个能够遍历到所有边的手段即可，当然邻接表也可以
    static final int N = 100010, INF = 0x3f3f3f3f;
    static final int[][] edges = new int[N][3];
    static int n, m; // n 是点数 m 是边数

    // 从 start 为起点， k 条边为限 的最短路
    static int[] bellmanFord(int start, int k) {
        int[] dist = new int[n];
        int[] temp = new int[n];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        for (int i = 0; i < k; i++) {
            // 每轮开始都需要建立一个副本，而不能直接使用 dist 数组来更新最小值
            // 事实上，直接使用 dist 数组更新最小值也是可以求出最短路的
            // 但是却不能保证路径边数小于等于 k 的设定了，而这才是 bellman-ford 的意义
            System.arraycopy(dist, 0, temp, 0, n);
            for (int j = 0; j < m; j++) {
                int from = edges[j][0], to = edges[j][1], weight = edges[j][2];
                // 必须加判断，因为图中存在负权边，这会导致正无穷被更新成非正无穷的大数
                if (temp[from] != INF) dist[to] = Math.min(dist[to], temp[from] + weight);
            }
        }
        return dist;
    }
}
