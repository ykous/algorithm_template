package graph;

import java.util.Arrays;

// floyd 算法的意义是求出任意两点之间的最短路，一次求值多次查询，此外代码很短
// 且多用于稠密图，复杂度 O(n*n*n)
public class Floyd {
    static final int N = 510, INF = 0x3f3f3f3f;
    static final int[][] map = new int[N][N];
    static int n, m;

    static {
        for (int[] arr : map) Arrays.fill(arr, INF);
        for (int i = 0; i < N; i++) map[i][i] = 0; // 注意不要用小写 n
    }

    // 如果需要保存邻接矩阵则对副本操作
    // floyd 是一个动态规划算法 dp[k,i,j] 代表只使用前 k 个点能够构成的从 i 到 j 的最短路径
    // dp[k,i,j] = dp[k-1,i,k] + dp[k - 1,k,j]
    // k 是可以去掉的，这里的可以去掉的原因不同于滚动数组，而是同一轮中已经被更新的值不会影响更新
    // 即 如果 dp[k-1,i,k] 中的路径集合被加入第 k 个点，即被 k 更新成 dp[k,i,k]
    // 那么由于抽屉原理 dp[k,i,j] 中一定不会再经过 k 节点

    // 理解算法的关键就在于理解 dp[i,j] 代表的是从 i 到 j 的路径集合
    // 每一次我们尝试通过节点 k 连接两个集合之间的路径，就是把两条路接起来
    // 在最开始，集合中只有两点的路径，即各个有向边
    // 每一次，我们遍历所有边，并尝试用 k 节点连接两条边，即每个节点都能够加入到每一条路径
    static void floyd() {
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (map[i][k] != INF && map[k][j] != INF) // 对于负权图必须检查，因为 INF 会被负边更新
                        map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
    }

}
