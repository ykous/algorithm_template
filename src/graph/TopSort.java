package graph;

import java.util.LinkedList;
import java.util.Queue;

// 拓扑排序
public class TopSort {
    // 准备邻接表
    static final int N = 100010;
    static final int NULL = 0;
    static int idx = 1;

    static int[] head=  new int[N], e = new int[N], ne = new int[N];
    static int n,m;

    static int[] topSort() {
        int[] result = new int[head.length];
        int size = 0;
        int[] inDegree = new int[head.length];
        // 遍历所有的边计算入度
        for (int i = 0 ;i < n;i++)
            for (int p = head[i]; p != NULL; p = ne[p])
                inDegree[e[p]]++;
        // 把所有入度为 0 的点加入对队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++)
            if (inDegree[i] == 0) queue.add(i);

        while (!queue.isEmpty()) {
            int from = queue.poll();
            result[size++] = from;
            // 删除邻接的边
            for (int p = head[from]; p != NULL; p = ne[p])
                if (--inDegree[e[p]] == 0) queue.add(e[p]);
        }
        // 存在环则返回 null
        return size == head.length ? result : null;
    }
}
