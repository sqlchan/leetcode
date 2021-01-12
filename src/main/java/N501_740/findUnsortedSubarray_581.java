package N501_740;

/**
 * 最短无序连续子数组
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 *
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 *
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 *
 * 算法
 *
 * 这个算法背后的思想是无序子数组中最小元素的正确位置可以决定左边界，最大元素的正确位置可以决定右边界。
 *
 * 因此，首先我们需要找到原数组在哪个位置开始不是升序的。我们从头开始遍历数组，一旦遇到降序的元素，我们记录最小元素为 minmin 。
 *
 * 类似的，我们逆序扫描数组 numsnums，当数组出现升序的时候，我们记录最大元素为 maxmax。
 *
 * 然后，我们再次遍历 numsnums 数组并通过与其他元素进行比较，来找到 minmin 和 maxmax 在原数组中的正确位置。我们只需要从头开始找到第一个大于 minmin 的元素，从尾开始找到第一个小于 maxmax 的元素，它们之间就是最短无序子数组。
 *我们观察到指针 bb 在下标 0 以后，标记着无序子数组的左边界，指针 aa 在下标 7 以前，标记着无序子数组的右边界。
 */
public class findUnsortedSubarray_581 {
    public class Solution {
        public int findUnsortedSubarray(int[] nums) {
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            boolean flag = false;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] < nums[i - 1])
                    flag = true;
                if (flag)
                    min = Math.min(min, nums[i]);
            }
            flag = false;
            for (int i = nums.length - 2; i >= 0; i--) {
                if (nums[i] > nums[i + 1])
                    flag = true;
                if (flag)
                    max = Math.max(max, nums[i]);
            }
            int l, r;
            for (l = 0; l < nums.length; l++) {
                if (min < nums[l])
                    break;
            }
            for (r = nums.length - 1; r >= 0; r--) {
                if (max > nums[r])
                    break;
            }
            return r - l < 0 ? 0 : r - l + 1;
        }
    }


}
