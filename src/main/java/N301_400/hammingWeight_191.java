package N301_400;

/**
 * 位1的个数
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 * 方法 2：位操作的小技巧
 * 算法
 *
 * 我们可以把前面的算法进行优化。我们不再检查数字的每一个位，而是不断把数字最后一个 11 反转，并把答案加一。当数字变成 00 的时候偶，我们就知道它没有 11 的位了，此时返回答案。
 *
 * 这里关键的想法是对于任意数字 nn ，将 nn 和 n - 1n−1 做与运算，会把最后一个 11 的位变成 00 。为什么？考虑 nn 和 n - 1n−1 的二进制表示。
 *在二进制表示中，数字 nn 中最低位的 11 总是对应 n - 1n−1 中的 00 。因此，将 nn 和 n - 1n−1 与运算总是能把 nn 中最低位的 11 变成 00 ，并保持其他位不变。
 *
 */
public class hammingWeight_191 {
    public int hammingWeight(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n &= (n - 1);
        }
        return sum;
    }

}
