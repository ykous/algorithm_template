package graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

// 排序边集，遍历，不断地维护联通分支直到全联通
public class Kruskal {
    // 先准备好并查集
    static int[] p;

    static void init(int n) {
        p = new int[n];
        Arrays.fill(p, -1);
    }

    static int find(int x) {
        if (p[x] == -1) return x;
        p[x] = find(p[x]);
        return p[x];
    }

    static boolean connect(int x, int y) {
        return find(x) == find(y);
    }


    static void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px != py) {
            p[px] = py;
        }
    }

    static void kruskal(int[][] edges, int n) {
        init(n);
        Arrays.sort(edges, Comparator.comparingInt(t -> t[2]));
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            if (!connect(from, to)) {
                exec(from, to, weight);
                union(from, to);
            }
        }
    }

    private static void exec(int from, int to, int weight) {
        System.out.println(from + " " + to + " " + weight);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] edges = new int[m][3];
        for (int i = 0; i < m; i++) {
            edges[i][0] = scanner.nextInt() - 1;
            edges[i][1] = scanner.nextInt() - 1;
            edges[i][2] = scanner.nextInt();
        }
        kruskal(edges, n);
    }
}
