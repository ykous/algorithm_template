package dataStructure;

// 模拟队列
public class SimulationQueue {
    static final int N = 100010;
    static int[] queue= new int[N];
    static int head = 0;
    static int tail = 0;

    static void push(int x){
        queue[tail++] = x;
    }

    static void pop(){
        head++;
    }

    static int peek(){
        return queue[head];
    }

    static boolean empty(){
        return tail - head == 0;
    }
}
