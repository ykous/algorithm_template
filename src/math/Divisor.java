package math;

// 求约数
public class Divisor {

    static void divisor(int n) {
        for(int i = 1;i < n/i;i++)
            if(n % i == 0)
                exec(i,n/i);
    }


    // 重写来处理每一个约束对
    static void exec(int x, int y) {
        System.out.println(x + " " + y);
    }

}
