package other;

// 差分
// 差分是前缀和的逆运算，对差分进行前缀和就可以得到原数组
// 差分的作用是能够在 O(1) 的复杂度内对原数组进行区间修改的操作，应用于大量的区间修改，少量的区间查找问题
// 最终可以利用前缀和模板获得修改后的原数组，因此不附加查询操作
public class Difference {

    public static int[] difference(int[] arr) {
        // 边界+1，该边界正常情况下无用
        int[] res = new int[arr.length + 1];
        // 初始化操作，对于每一个位置，看作 [i,i+1) 的更新操作
        for (int i = 0; i < arr.length; i++) update(i, i + 1, arr[i], res);
        return res;
    }

    // 区间修改，左闭右开区间
    public static void update(int l, int r, int delta, int[] diff) {
        diff[l] += delta;
        diff[r] -= delta;
    }

    // 二维差分
    public static int[][] difference(int[][] arr) {
        int[][] res = new int[arr.length + 1][arr[0].length + 1];
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr[i].length; j++)
                update(i, j, i + 1, j + 1, arr[i][j], res);
        return res;
    }

    // 区间修改，左闭右开
    private static void update(int x1, int y1, int x2, int y2, int delta, int[][] diff) {
        diff[x1][y1] += delta;
        diff[x2][y1] -= delta;
        diff[y1][x2] -= delta;
        diff[x2][y2] += delta;
    }

    public static void main(String[] args) {


    }
}
