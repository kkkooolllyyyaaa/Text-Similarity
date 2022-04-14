/**
 * @author tsypk on 13.04.2022 22:58
 * @project testTask
 */
public class LewensteinLength {
    private int[][] D;
    private int n, m;

    public int calculateLength(String str1, String str2) {
        init(str1, str2);
        for (int j = 0; j < m; ++j) {
            D[0][j] = j;
        }
        for (int i = 0; i < n; ++i) {
            D[i][0] = i;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < m; ++j) {
                int c = diff(str1.charAt(i - 1), str2.charAt(j - 1));
                D[i][j] = Math.min(D[i - 1][j] + 1, Math.min(D[i][j - 1] + 1, D[i - 1][j - 1] + c));
            }
        }
        return D[n - 1][m - 1];
    }

    private void init(String str1, String str2) {
        n = str1.length() + 1;
        m = str2.length() + 1;
        D = new int[n][m];
    }

    private int diff(char c1, char c2) {
        return c1 == c2 ? 0 : 2;
    }
}
