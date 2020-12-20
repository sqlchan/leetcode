package N141_200;

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 *
 * 我们可以根据正负性进行分类讨论。
 *
 * 考虑当前位置如果是一个负数的话，那么我们希望以它前一个位置结尾的某个段的积也是个负数，这样就可以负负得正，并且我们希望这个积尽可能「负得更多」，即尽可能小。如果当前位置是一个正数的话，我们更希望以它前一个位置结尾的某个段的积也是个正数，并且希望它尽可能地大。于是这里我们可以再维护一个 f_{\min}(i)f
 * min
 * ​
 *  (i)，它表示以第 ii 个元素结尾的乘积最小子数组的乘积，那么我们可以得到这样的动态规划转移方程：
 *
 * 它代表第 ii 个元素结尾的乘积最大子数组的乘积 f_{\max}(i)f
 * max
 * ​
 *  (i)，可以考虑把 a_ia
 * i
 * ​
 *   加入第 i - 1i−1 个元素结尾的乘积最大或最小的子数组的乘积中，二者加上 a_ia
 * i
 * ​
 *  ，三者取大，就是第 ii 个元素结尾的乘积最大子数组的乘积。第 ii 个元素结尾的乘积最小子数组的乘积 f_{\min}(i)f
 * min
 * ​
 *  (i) 同理。
 *
 */
public class MaxProduct_152 {
    public int maxProduct(int[] nums) {
        int length = nums.length;
        int[] maxF = new int[length];
        int[] minF = new int[length];
        System.arraycopy(nums, 0, maxF, 0, length);
        System.arraycopy(nums, 0, minF, 0, length);
        for (int i = 1; i < length; ++i) {
            maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(nums[i], minF[i - 1] * nums[i]));
            minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(nums[i], maxF[i - 1] * nums[i]));
        }
        int ans = maxF[0];
        for (int i = 1; i < length; ++i) {
            ans = Math.max(ans, maxF[i]);
        }
        return ans;
    }

    /**
     * 易得这里的渐进时间复杂度和渐进空间复杂度都是 O(n)O(n)。
     *
     * 考虑优化空间。
     *
     * 由于第 ii 个状态只和第 i - 1i−1 个状态相关，根据「滚动数组」思想，我们可以只用两个变量来维护 i - 1i−1 时刻的状态，一个维护 f_{\max}f
     * max
     * ​
     *  ，一个维护 f_{\min}f
     * min
     * ​
     *  。细节参见代码。
     *
     */

    public int maxProduct1(int[] nums) {
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            int mx = maxF, mn = minF;
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(maxF, ans);
        }
        return ans;
    }
}
