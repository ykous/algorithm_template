package dataStructure;

import java.util.Map;

/**
 * 自定义内存模拟双向链表
 */
public class SimulationDoublyLinkedList {
    static final int N = 100010;
    // 该模板采用头尾哨兵都为 0 号节点的方式，形成双向环路
    // 好处是代码整体更自洽，模板更少
    static final int head = 0;
    static final int tail = 0;
    static int index = 1;

    static int[] val = new int[N];
    static int[] next = new int[N];
    static int[] pre = new int[N];

    // 在指定节点后插入一个节点
    // insert(head) 即表示在头部插入
    // insert(pre[tail]) 表示在尾部插入
    static void insert(int node, int v) {
        int after = next[node];
        val[index] = v;
        next[node] = index;
        pre[after] = index;
        next[index] = after;
        pre[index] = node;
        index++;
    }

    // 删除指定节点
    static void delete(int node) {
        next[pre[node]] = next[node];
        pre[next[node]] = pre[node];
    }


}
