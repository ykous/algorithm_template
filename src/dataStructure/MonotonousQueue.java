package dataStructure;

// 单调队列
// 可以看作升级版的单调栈，单调栈只能维护某个前缀内的信息
// 而单调队列就是在此基础上添加了 pop 的功能，使得其维护的是一个滑动窗口内的信息
// 单调队列的模型只有一个，序列中的滑动窗口的最值问题
// 由于单调队列需要pop元素，因此存储的必须是下标，否则无法确定元素何时可以被 pop
public class MonotonousQueue {
    static final int N = 100010;
    static int head = 0;
    static int tail = 0;
    static int[] queue = new int[N];

    static void push(int x) {
        while (head < tail && !check(queue[tail - 1], x)) tail--;
        queue[tail++] = x;
    }

    // 自己定义队列从头到尾的单调性
    private static boolean check(int x, int y) {
        return false;
    }

    // 单调队列维护一个区间，需要提供区间的左端点来判断是否应该弹出队头元素
    static void pop(int l) {
        // 使用 while ，有时区间的跳跃跨度很大，需要弹出很多
        while (queue[head] < l) head++;
    }

    // peek 得到的是区间内的最小值
    static int peek() {
        return queue[head];
    }
}
