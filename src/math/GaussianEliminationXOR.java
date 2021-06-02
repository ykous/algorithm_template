package math;

// 高斯消元解异或线性方程组，原理类似，只是运算变成了异或
public class GaussianEliminationXOR {
    static int N = 110;
    static int[][] matrix = new int[N][N];
    static int n ;

    static int gauss(){
        int r = 0;
        for(int col = 0;col < n;col++) {
            int row = r;
            for(int i = r;i < n;i++) if(matrix[i][col] == 1) {
                row = i;
                break;
            }
            if(matrix[row][col] == 0) continue;
            for(int i = col;i <= n;i++) {
                int t = matrix[row][i];
                matrix[row][i] = matrix[r][i];
                matrix[r][i] = t;
            }
            for(int i = r + 1;i < n;i++) for(int j = n;j >= col;j--) matrix[i][j] ^= matrix[i][col]*matrix[r][j];
            r++;
        }
        if(r < n) {
            for(int i = n - 1;i >= r;i--) if(matrix[i][n]!=0) return -1;
            return 1;
        }
        for(int i = n-1;i >= 0;i--)
            for(int j = i+1;j < n;j++)
                for(int k = n;k >= j;k--) matrix[i][k] ^= matrix[j][j]*matrix[j][k];
        return 0;
    }
}
