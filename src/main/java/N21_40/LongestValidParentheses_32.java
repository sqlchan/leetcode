package N21_40;

/**
 * 32. 最长有效括号
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 * <p>
 * 在此方法中，我们利用两个计数器 \textit{left}left 和 \textit{right}right 。首先，我们从左到右遍历字符串，对于遇到的每个 \text{‘(’}‘(’，我们增加 \textit{left}left 计数器，对于遇到的每个 \text{‘)’}‘)’ ，我们增加 \textit{right}right 计数器。每当 \textit{left}left 计数器与 \textit{right}right 计数器相等时，我们计算当前有效字符串的长度，并且记录目前为止找到的最长子字符串。当 \textit{right}right 计数器比 \textit{left}left 计数器大时，我们将 \textit{left}left 和 \textit{right}right 计数器同时变回 00。
 * <p>
 * 这样的做法贪心地考虑了以当前字符下标结尾的有效括号长度，每次当右括号数量多于左括号数量的时候之前的字符我们都扔掉不再考虑，重新从下一个字符开始计算，但这样会漏掉一种情况，就是遍历的时候左括号的数量始终大于右括号的数量，即 (() ，这种时候最长有效括号是求不出来的。
 * <p>
 * 解决的方法也很简单，我们只需要从右往左遍历用类似的方法计算即可，只是这个时候判断条件反了过来：
 * <p>
 * 当 \textit{left}left 计数器比 \textit{right}right 计数器大时，我们将 \textit{left}left 和 \textit{right}right 计数器同时变回 00
 * 当 \textit{left}left 计数器与 \textit{right}right 计数器相等时，我们计算当前有效字符串的长度，并且记录目前为止找到的最长子字符串
 * 这样我们就能涵盖所有情况从而求解出答案。
 */
public class LongestValidParentheses_32 {
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }
}
