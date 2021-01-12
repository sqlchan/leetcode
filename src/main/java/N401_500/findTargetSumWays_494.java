package N401_500;

/**
 * 494. 目标和
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 *方法二：动态规划
 * 这道题也是一个常见的背包问题，我们可以用类似求解背包问题的方法来求出可能的方法数。
 *
 * 我们用 dp[i][j] 表示用数组中的前 i 个元素，组成和为 j 的方案数。考虑第 i 个数 nums[i]，它可以被添加 + 或 -，因此状态转移方程如下：
 *
 *
 * dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j + nums[i]]
 * 也可以写成递推的形式：
 *
 *
 * dp[i][j + nums[i]] += dp[i - 1][j]
 * dp[i][j - nums[i]] += dp[i - 1][j]
 * 由于数组中所有数的和不超过 1000，那么 j 的最小值可以达到 -1000。在很多语言中，是不允许数组的下标为负数的，因此我们需要给 dp[i][j] 的第二维预先增加 1000，即：
 *
 *
 * dp[i][j + nums[i] + 1000] += dp[i - 1][j + 1000]
 * dp[i][j - nums[i] + 1000] += dp[i - 1][j + 1000]
 *
 */
public class findTargetSumWays_494 {
    public class Solution {
        public int findTargetSumWays(int[] nums, int S) {
            int[][] dp = new int[nums.length][2001];
            dp[0][nums[0] + 1000] = 1;
            dp[0][-nums[0] + 1000] += 1;
            for (int i = 1; i < nums.length; i++) {
                for (int sum = -1000; sum <= 1000; sum++) {
                    if (dp[i - 1][sum + 1000] > 0) {
                        dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                        dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                    }
                }
            }
            return S > 1000 ? 0 : dp[nums.length - 1][S + 1000];
        }
    }

}
