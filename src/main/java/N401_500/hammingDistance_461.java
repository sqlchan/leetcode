package N401_500;

/**
 * 461. 汉明距离
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 *
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 *
 * 方法二：移位
 * 思路
 *
 * 为了计算等于 1 的位数，可以将每个位移动到最左侧或最右侧，然后检查该位是否为 1。
 *
 * 更准确的说，应该进行逻辑移位，移入零替换丢弃的位。
 *
 * 这里采用右移位，每个位置都会被移动到最右边。移位后检查最右位的位是否为 1 即可。检查最右位是否为 1，可以使用取模运算（i % 2）或者 AND 操作（i & 1），这两个操作都会屏蔽最右位以外的其他位。
 *
 */
public class hammingDistance_461 {
    class Solution {
        public int hammingDistance(int x, int y) {
            int xor = x ^ y;
            int distance = 0;
            while (xor != 0) {
                if (xor % 2 == 1)
                    distance += 1;
                xor = xor >> 1;
            }
            return distance;
        }
    }

}
