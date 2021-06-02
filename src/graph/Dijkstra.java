package graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

// 堆优化 dijkstra
public class Dijkstra {
    // 准备邻接表
    static final int N = 100010, NULL = 0, INF = 0x3f3f3f3f;
    static final int[] head = new int[N], e = new int[N], ne = new int[N], weight = new int[N];
    static int idx = 1;
    static int n;

    static int[] dijkstra(int start) {
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        Queue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(t -> t[1]));
        heap.add(new int[]{start, 0});
        while (!heap.isEmpty()) {
            int[] poll = heap.poll();
            int from = poll[0];
            int distance = poll[1];
            if (distance >= dist[from]) continue;
            dist[from] = distance;
            for(int p = head[from];p != NULL;p = ne[p]) {
                int to = e[p],newDistance = distance+ weight[p];
                if (newDistance < dist[to]) heap.add(new int[] {to,newDistance});
            }
        }
        return dist;
    }

}
