package N141_200;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 */
public class MajorityElement_169 {
    /**
     * 方法五：Boyer-Moore 投票算法
     * 思路
     *
     * 如果我们把众数记为 +1+1，把其他数记为 -1−1，将它们全部加起来，显然和大于 0，从结果本身我们可以看出众数比其他数多。
     *
     * 算法
     *
     * Boyer-Moore 算法的本质和方法四中的分治十分类似。我们首先给出 Boyer-Moore 算法的详细步骤：
     *
     * 我们维护一个候选众数 candidate 和它出现的次数 count。初始时 candidate 可以为任意值，count 为 0；
     *
     * 我们遍历数组 nums 中的所有元素，对于每个元素 x，在判断 x 之前，如果 count 的值为 0，我们先将 x 的值赋予 candidate，随后我们判断 x：
     *
     * 如果 x 与 candidate 相等，那么计数器 count 的值增加 1；
     *
     * 如果 x 与 candidate 不等，那么计数器 count 的值减少 1。
     *
     * 在遍历完成后，candidate 即为整个数组的众数。
     *
     */
    class Solution {
        public int majorityElement(int[] nums) {
            int count = 0;
            Integer candidate = null;

            for (int num : nums) {
                if (count == 0) {
                    candidate = num;
                }
                count += (num == candidate) ? 1 : -1;
            }

            return candidate;
        }
    }
    /**
     * 投票法正确思路：不妨假设整个数组的众数记做a，则最初的数组中a的数量大于其余所有数。当采用count计数的时候有两种情况：
     *
     * 1）假设candidate等于a，则当count从1变为0的过程，此区间内a的数量等于其余数的数量，因此以count=0为分界线，数组右端部分的众数仍然为a
     *
     * 2）假设candidate不等于a，则当count从1变为0的过程， 此区间内a的数量小于等于其余数的数量，因此以count=0为分界线，数组右端部分的众数仍然为a
     *
     * 因此，以count=0可以将整个原始数组分为若干部分，count=0右端部分的数组中的众数永远是a，最终必然会筛选出a
     */

    //解法2：排序
    //由于众数出现的频率大于n/2,所以在排序之后众数必存在于下标[n/2]处(本题默认数组中是一定存在众数的，所以返回下标[n/2]可行)
//    int majorityElement_2(vector<int>& nums) {
//        sort(nums.begin(),nums.end());
//        return nums[nums.size()/2];
//    }
}
