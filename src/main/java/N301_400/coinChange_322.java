package N301_400;

/**
 *  零钱兑换
 *  给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 *当然可以，利用动态规划，我们可以在多项式的时间范围内求解。首先，我们定义：
 *
 * F(S)F(S)：组成金额 SS 所需的最少硬币数量
 *
 * [c_{0}\ldots c_{n-1}][c
 * 0
 * ​
 *  …c
 * n−1
 * ​
 *  ] ：可选的 nn 枚硬币面额值
 *
 * 我们注意到这个问题有一个最优的子结构性质，这是解决动态规划问题的关键。最优解可以从其子问题的最优解构造出来。如何将问题分解成子问题？假设我们知道 F(S)F(S) ，即组成金额 SS 最少的硬币数，最后一枚硬币的面值是 CC。那么由于问题的最优子结构，转移方程应为：
 *
 * F(S) = F(S - C) + 1
 * F(S)=F(S−C)+1
 *
 */
public class coinChange_322 {
    public class Solution {
        public int coinChange(int[] coins, int amount) {
            if (amount < 1) {
                return 0;
            }
            return coinChange(coins, amount, new int[amount]);
        }

        private int coinChange(int[] coins, int rem, int[] count) {
            if (rem < 0) {
                return -1;
            }
            if (rem == 0) {
                return 0;
            }
            if (count[rem - 1] != 0) {
                return count[rem - 1];
            }
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                int res = coinChange(coins, rem - coin, count);
                if (res >= 0 && res < min) {
                    min = 1 + res;
                }
            }
            count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
            return count[rem - 1];
        }
    }

}
