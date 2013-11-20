package blog;

/**
 * 编辑距离
 * 					V(i-1,j-1) + (vStrRow[i]==vStrColumn[j]?MATCH:MISS_MATCH)
 * V(i,j) = MAX		V(i-1,j) + INSERT
 * 					V(i,j-1) + DELETE
 * @author Administrator
 *
 */
public class StringEditDistance {
    private static final int MATCH = 2;
    private static final int MISS_MATCH = -1;
    private static final int INSERT = -1;
    private static final int DELETE = -1;

    public static void main(String[] args) {
    	process("ACAATCC".toCharArray(), "AGCATGC".toCharArray());
    }

    /**
     * @param vStrRow
     * @param vStrColumn
     */
    public static void process(char[] vStrRow, char[] vStrColumn) {
        int editDis = 0; // 编辑距离
        int row = vStrColumn.length;
        int column = vStrRow.length;
        int sizeR = row + 1;
        int sizeC = column + 1;

        int[][] pScore = new int[sizeR][sizeC];

        // 初始化第一行和第一列
        for (int c = 0; c <= column; c++)
            pScore[0][c] = 0 - c;
        for (int r = 0; r <= row; r++)
            pScore[r][0] = 0 - r;

        // 从v(1,1)开始每列计算
        for (int c = 1; c <= column; c++) {
            for (int r = 1; r <= row; r++) {
                // 计算v(i,j)
                int valueMatch;
                if (vStrColumn[r - 1] == vStrRow[c - 1])
                    valueMatch = MATCH;
                else
                    valueMatch = MISS_MATCH;
                int A = pScore[r - 1][c] + INSERT;
                int B = pScore[r][c - 1] + DELETE;
                int C = pScore[r - 1][c - 1] + valueMatch;
                pScore[r][c] = getMaxValue(A, B, C);
            }
        }

        // 计算编辑距离
        int r = row, c = column;
        while (r > 0 && c > 0) {
            if (pScore[r][c] + 1 == pScore[r - 1][c]) {
                editDis++;
                r--;
                continue;
            } else if (pScore[r][c] + 1 == pScore[r][c - 1]) {
                editDis++;
                c--;
                continue;
            } else if (pScore[r][c] + 1 == pScore[r - 1][c - 1]) {
                editDis++;
                r--;
                c--;
                continue;
            } else {
                r--;
                c--;
            }
        }
        if (r > 0 && c == 0)
            editDis += r;
        else if (c > 0 && r == 0)
            editDis += c;

        // ----------------DEBUG-------------------//
        // 打印数据
        System.out.println("edit distance is " + editDis);
        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= column; j++)
                System.out.print(pScore[i][j] + "  ");
            System.out.println();
        }

    }

    private static int getMaxValue(int a, int b, int c) {
        if (a < b) {
            if (b < c)
                return c;
            return b;
        } else {
            if (b > c)
                return a;
            return a < c ? c : a;
        }
    }
}
