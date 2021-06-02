package dataStructure;

/**
 * 堆模板
 * 记忆堆模板的好处有两个，也就是标准库中优先队列有两个不存在的性质
 * <p>
 * 1. 序列建堆只需要 O(n)
 * 2. 具有修改指定位置元素，删除指定位置元素的能力
 * <p>
 * 堆最关键的代码就是 down 和 up ，这两个是记忆的关键，其他的都通过二者组合可得
 */
public class Heap {
    static final int N = 100010;
    static int[] heap = new int[N];
    static int size = 0;

    // 一种快速建堆的方式，提供一个序列并把其变成堆，线性复杂度
    static void init(int[] arr) {
        size = arr.length;
        heap = arr;
        for (int i = (size - 1) / 2; i >= 0; i--) down(i);
    }

    // 堆元素的交换操作，把下标 i 和下标 j 交换
    // 多数情况下只需要修改这个函数即可适配需要
    // 这类需要通常是维护节点元素与其他数据的映射关系，只需要把映射关系也对换一下即可。例如记录该元素是第几次插入的
    static void swap(int i, int j) {
        int t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
    }

    // 上滤操作
    static void up(int k) {
        if (k == 0) return;
        int p = (k - 1) >> 1;
        if (heap[k] < heap[p]) {
            swap(p, k);
            up(p);
        }
    }

    // 下滤操作
    static void down(int k) {
        int t = k, l = k * 2 + 1, r = k * 2 + 2;
        if (l < size && heap[l] < heap[t]) t = l;
        if (r < size && heap[r] < heap[t]) t = r;
        if (t != k) {
            swap(t, k);
            down(t);
        }
    }

    static void push(int x) {
        heap[size] = x;
        up(size++);
    }

    static void pop() {
        swap(0,--size);
        down(0);
    }

    static int peek() {
        return heap[0];
    }

    // 删除下标为 i 的元素
    // 通常要配合某种外部索引来转换成下标而不是直接使用下标
    static void delete(int i) {
        swap(i,--size);
        // 为什么删除元素也需要 up 一下？
        // 因为换上来的不一定是当前位置所代表子树的子节点，可能来自其他子树
        // 因此不能保证大小一定小于当前节点的父节点
        up(i);
        down(i);
    }

    // 修改指定下标为 i 的元素
    static void update(int i, int v) {
        heap[i] = v;
        up(i);
        down(i);
    }


}
