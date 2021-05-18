package math;

// 组合数的模板
public class Combination {
    public static void main(String[] args) {
        System.out.println(combination(10, 920));
    }
    /**
     * m 为上面的数字
     *
     * 如果必要还可以加一步判断，如果 m > n/2 那么 n = m - n
     */
    static int combination(int m,int n){
        int result = 1;
        for (int i = 1,j = n; i <= m; i++,j--) {
            result = result*j/i;
        }
        return result;
    }
}
