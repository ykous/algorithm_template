package graph;

/**
 * 邻接表模板
 */
public class Adjacency {
    static final int N = 100010;
    static final int NULL = 0; // 0 作为 NULL 不需要初始化
    static int idx = 1;

    static final int[] head = new int[N],e = new int[N],ne = new int[N],weight = new int[N];
    static int n; // 点数量

    // from 到 to 加一条有向边
    static void add(int from,int to,int w) {
        e[idx] = to;
        weight[idx] = w;
        ne[idx] = head[from];
        head[from] = idx++;
    }

    // 示例如何遍历一个点的邻接点
    static void example(int from) {
        for (int p = head[from];p != NULL ;p = ne[p]) {
            int to = e[p]; // 当前遍历到的边的终点
            System.out.println(to);
        }
    }
}
