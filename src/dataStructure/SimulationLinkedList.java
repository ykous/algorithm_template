package dataStructure;

/**
 * 自己管理内存来实现单向链表
 *
 * 维护一个 val 数组，一个 next 数组，分别对应链表节点结构体中的 val 和 next 变量
 * 数组相当于内存，下标相当于地址
 *
 * 算法题中的绝大多数数据结构都可以采用这种方式，好处是效率高，代码少，不需要自定义类
 *
 * 这并不是针对链表的某一具体模拟，自己管理内存在算法题中是非常常用的手段，因为算法题不需要管理内存（获取和释放）
 */
public class SimulationLinkedList {

    static final int N = 100010; // 内存大小，根据具体题目变动
    static final int NULL = 0; // 定义 0 为 null
    static int index = 1; // 下一个 new 出来的结构体的内存地址，依次递增

    static int[] val = new int[N]; // val 变量
    static int[] next = new int[N]; // next 变量

    static final int H = 0; // 哨兵头节点的位置，同时也是 null，这么做的好处是模板更短，但是写错了不会报错

    // 在 node 后插入一个节点
    static void insert(int node,int v){
        val[index] = v;
        next[index] = next[node];
        next[node] = index;
        index++;
    }

    // 删除指定节点的下一个节点
    static void delete(int node){
        next[node] = next[next[node]];
    }

    // 查找序列下标为 i 的节点
    static int search(int idx){
        int t = H;
        for(int i = 0;i < index;i++) t = next[t];
        return next[t];
    }
}
