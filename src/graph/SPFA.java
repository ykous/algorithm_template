package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// spfa 在期望意义下的复杂度是 O(m) 但在极端情况下的复杂度是 O(nm)
// 因此 spfa 是一种实际中很有用但是竞赛中容易被卡复杂度的算法，所以正权图还是 dijkstra 吧
// 此外另一个比较重要的作用就是负环检测
public class SPFA {

    static final int N = 100010, NULL = 0,INF = 0x3f3f3f3f;
    static final int[] head = new int[N], e = new int[N],ne = new int[N] ,weight = new int[N];
    static int idx = 1;
    static int n;

    static int[] spfa(int start) {
        int[] dist = new int[n];
        boolean[] in = new boolean[n]; // 记录节点在不在队列中
        Arrays.fill(dist,INF);
        dist[start] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int from = queue.poll();
            in[from] = false;
            for (int p = head[from]; p != NULL;p = ne[p]){
                int to = e[p], w = weight[p];
                if (dist[to] > dist[from] + w) {
                    dist[to] = dist[from] + w;
                    if(!in[to]) queue.add(to); // 已经在队列中的点不需要再加了，否则增加复杂度
                    in[to] = true;
                }
            }
        }
        return dist;
    }

    // 使用 spfa 算法判断负环
    // spfa 判负环 与 计算最短路其实是两种算法，更具象的说是两种动态规划
    // 但为什么都叫 spfa ？ 因为 spfa 代表的不是某一种算法
    // 而是”节点代表的状态发生改变，因而需要重新更新“
    // 这种重新更新如果以递归的方式体现，就是 dfs ，如果以队列的方式体现，就是 bfs
    static boolean spfa() {
        // 此时的 dist 的含义不再是从起点到 i 的最短路径长度了
        // 而是以 i 为终点的路径的最短距离
        int[] dist = new int[n];
        int[] cnt = new int[n]; // 这条最短路径的边数
        boolean[] in = new boolean[n];
        Arrays.fill(in,true); // 不加会多一个 n 的线性复杂度，可以考虑
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) queue.add(i);
        while (!queue.isEmpty()) {
            int from = queue.poll();
            if (cnt[from] >= n) return true;
            in[from] = false;
            for(int p = head[from]; p != NULL; p = ne[p]) {
                int to = e[p] , w = weight[p];
                if (dist[to] > dist[from] + w) {
                    dist[to] = dist[from] + w;
                    cnt[to] = cnt[from] + 1;
                    if (!in[to]) queue.add(to);
                    in[to] = true;
                }
            }
        }
        return false;
    }
}
