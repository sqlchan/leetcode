package N301_400;

/**
 * 比特位计数
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: [0,1,1]
 *直觉
 *
 * 对一个数字解决问题，并应用到全部。
 *
 * 算法
 *
 * 本问题可以看做 位 1 的个数 的后续。它计数一个无符号整数的位。结果称为 pop count，或 汉明权重。可以参看 位 1 的个数 的题解以获得更详细介绍。
 *
 * 现在，我们先默认这个概念。假设我们有函数int popcount(int x) ，可以返回一个给定非负整数的位计数。我们只需要在 [0, num] 范围内循环并将结果存到一个列表中。
 *
 */
public class countBits_338 {
    public class Solution {
        public int[] countBits(int num) {
            int[] ans = new int[num + 1];
            for (int i = 0; i <= num; ++i)
                ans[i] = popcount(i);
            return ans;
        }
        private int popcount(int x) {
            int count;
            for (count = 0; x != 0; ++count)
                x &= x - 1; //zeroing out the least significant nonzero bit
            return count;
        }
    }

}
