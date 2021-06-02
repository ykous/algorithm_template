package dataStructure;

// 单调栈
// 单调栈的模型只有一个，就是找序列中某一位置前最近的比其小的元素，这个前和最近可以自己定义
public class MonotonousStack {
    static final int N = 100010;
    static int top = 0;
    static int[] stack = new int[N];

    static void push(int x){
        while(top > 0 && !check(stack[top-1],x)) top --; // 注意此处的 check 有个 ！
        stack[top++] = x;
    }

    // 定义从前到后的单调性
    private static boolean check(int x, int y) {
        return x < y;
    }
}
