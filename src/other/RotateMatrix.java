package other;

import java.util.Arrays;

// 旋转二维数组,目的在于记住坐标的变换公式
public class RotateMatrix {
    // 正向旋转 90 度
    static int[][] rotate90(int[][] map){
        int n = map.length;
        int m = map[0].length;
        int[][] result = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[j][n - i - 1] = map[i][j]; // 变换公式
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] ints = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        ints = rotate90(ints);
        for (int[] arr : ints) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
