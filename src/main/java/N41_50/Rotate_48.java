package N41_50;

/**
 * 48. 旋转图像
 * 将给定的矩阵分成四个矩形并且将原问题划归为旋转这些矩形的问题
 * 可以在第一个矩形中移动元素并且在 长度为 4 个元素的临时列表中移动它们
 *
 */
public class Rotate_48 {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2 + n % 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int[] tmp = new int[4];
                int row = i;
                int col = j;
                for (int k = 0; k < 4; k++) {
                    tmp[k] = matrix[row][col];
                    int x = row;
                    row = col;
                    col = n - 1 - x;
                }
                for (int k = 0; k < 4; k++) {
                    matrix[row][col] = tmp[(k + 3) % 4];
                    int x = row;
                    row = col;
                    col = n - 1 - x;
                }
            }
        }
    }
}
