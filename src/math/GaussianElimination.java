package math;

import static java.lang.Math.abs;

/**
 * 高斯消元用于求解多元线性微分方程组
 * 其大致思路是
 * <p>
 * 1. 找出第一列元素最大的那一行
 * 2. 交换到第一行
 * 3. 将第一行整体除以第一个数，使得第一个数变成 1
 * 4. 将之后的行的第一个元素使用该行的倍数相减使得下面的行的第一个元素都是 0
 * 5. 同样的操作对 2 到 n 行执行
 */
public class GaussianElimination {

    static final int N = 110;
    static final double eps = 10e-6;
    static final double[][] matrix = new double[N][N];
    static int n;

    // 代码非常难写
    // 减去一行的代码，先明确那两行，然后遍历所有列，同列不同行，最后加上一个系数 k 倍
    static int gauss() {
        int r = 0; // 矩阵的秩
        for (int col = 0; col < n; col++) { // 操作每一列
            int row = r;
            // 找到当前这列绝对值最大的一行，之所以找最大值是为了判断是否全0方便
            for (int i = r; i < n; i++) if (abs(matrix[row][col]) < abs(matrix[i][col])) row = i;
            // 如果全部行都是 0 那么无可操作，同时有效多项式势必 -1 矩阵的秩也 -1
            if (abs(matrix[row][col]) < eps) continue;
            // 否则把这一行换到 r
            for (int i = col; i <= n; i++) {
                double t = matrix[row][i];
                matrix[row][i] = matrix[r][i];
                matrix[r][i] = t;
            }
            // 把这一行的第 col 列变成 1
            for (int i = n; i >= col; i--) matrix[r][i] /= matrix[r][col];
            // 让 r 之后的行的 col 列都变成 0，即其他列都减去这行的 k 倍
            for (int i = r + 1; i < n; i++)
                for (int j = n; j >= col; j--)
                    matrix[i][j] -= matrix[i][col] * matrix[r][j];
            r++; // 矩阵的秩增加
        }
        // 如果不满秩，不可能唯一解
        if (r < n) {
            // 如果出现了 0 等于 非0 的情况，则说明无解
            for (int i = n - 1; i >= r; i--) if (abs(matrix[i][n]) > eps) return -1;
            return 1; // 否则无穷解
        }
        // 在有解的情况下，求出解
        for (int i = n - 1; i >= 0; i--) // 操作每一行
            for (int j = i + 1; j < n; j++) // 把 i 后面的每一列都变成 0
                for (int k = n; k >= j; k--) // 要想把第 j 列变成 0 ，则需要减去第 j 行的倍数
                    matrix[i][k] -= matrix[i][j] * matrix[j][k]; // 倍数关系就是 matrix[i][j]
        return 0;
    }
}
