package other;

import java.util.Scanner;

// 前缀和模板 左闭右开区间
// 前缀和的作用是能够在 O(1) 的复杂度内查询原序列的区间和
public class PrefixSum {
    // 一维前缀和
    public static int[] prefix(int[] arr) {
        int[] res = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            res[i + 1] = res[i] + arr[i];
        }
        return res;
    }

    // 一维区间查找，左闭右开区间
    public static int search(int l, int r, int[] prefix) {
        return prefix[r] - prefix[l];
    }

    // 二维前缀和
    public static int[][] prefix(int[][] arr) {
        int[][] res = new int[arr.length + 1][arr[0].length + 1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                res[i + 1][j + 1] = res[i + 1][j] + res[i][j + 1] - res[i][j] + arr[i][j];
            }
        }
        return res;
    }

    // 二维查找，左闭右开区间
    public static int search(int x1, int y1, int x2, int y2, int[][] prefix) {
        return prefix[x2][y2] - prefix[x1][y2] - prefix[x2][y1] + prefix[x1][y1];
    }

    // 正斜前缀和，即一个二维数组，以从左上到右下的方向，分割成一条条的斜线
    // 结果的 prefix[x][y] 表示从 x，y 到最左上角的坐标的路径的和
    static int[][] slashPrefix(int[][] arr) {
        // 从含义上讲，不适合开区间这样的描述，所以就以精准的坐标描述
        int[][] res = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr[i].length; j++)
                if(i == 0 || j == 0) res[i][j] = arr[i][j];
                else res[i][j] = res[i-1][j-1] + arr[i][j];
        return res;
    }

    // 反斜前缀和，prefix[x][y] 表示其从 x y 到右上角的路径的和
    static int[][] backslashPrefix(int[][] arr) {
        int[][] res = new int[arr.length][arr[0].length];
        for(int i = 0;i < arr.length;i++)
            for(int j = 0;j < arr[i].length;j++)
                if (i == 0 || j == arr[i].length-1) res[i][j] = arr[i][j];
                else res[i][j] = res[i-1][j+1] + arr[i][j];
        return res;
    }

}
