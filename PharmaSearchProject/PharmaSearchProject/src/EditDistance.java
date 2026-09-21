public class EditDistance {

    // Calculate Levenshtein Edit Distance
    public static int editDistance(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        // Create DP table
        int[][] dp = new int[m + 1][n + 1];

        // Convert word1 to empty string
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        // Convert empty string to word2
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Fill the table
        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (word1.charAt(i - 1) ==
                    word2.charAt(j - 1)) {

                    // Characters are same
                    dp[i][j] = dp[i - 1][j - 1];

                } else {

                    // Find minimum of:
                    // 1. Insert
                    // 2. Delete
                    // 3. Replace

                    int insert = dp[i][j - 1];
                    int delete = dp[i - 1][j];
                    int replace = dp[i - 1][j - 1];

                    dp[i][j] =
                        1 + Math.min(
                            insert,
                            Math.min(delete, replace)
                        );
                }
            }
        }

        return dp[m][n];
    }
}