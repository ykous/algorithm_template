package graph;

import java.util.Arrays;

// 并查集，既可以是某一种数据结构，也可以看作是一种维护连通性的算法
public class UnionSet {
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

    static void union(int x,int y){
        int px = find(x);
        int py = find(y);
        if (px != py) p[px] = py;
    }
}
