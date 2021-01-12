package N301_400;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 删除无效的括号
 * 删除最小数量的无效括号，使得输入的字符串有效，返回所有可能的结果。
 *
 * 说明: 输入可能包含了除 ( 和 ) 以外的字符。
 * 输入: "()())()"
 * 输出: ["()()()", "(())()"]
 *
 * 方法一：回溯
 * 对于这个问题，我们得到了一个由括号组成的表达式，并且表达式中可能有一些错误的括号或额外的括号，导致它无效。只有当每个右括号都有对应的左括号时，由括号组成的表达式才被视为有效，反之亦然。
 *
 * 这意味着，如果我们从左到右查看每个括号，一旦遇到右括号，就应该有一个左括号来匹配它。否则表达式将变为无效。如果左括号的数目大于右括号的数目则表达式也无效。
 *
 * 让我们看看一个无效的表达式和所有可能的有效表达式，这些表达式可以通过删除一些方括号从中形成。我们可以删除哪些括号没有限制。我们只需要使表达式有效。
 *
 * 唯一的条件是，我们应该删除最小的括号数，使一个无效的表达式有效。如果不存在这个条件，我们可以潜在地删除大部分括号。
 * 在上图中需要注意的一件重要的事情是，有多种方法可以达到相同的解，也就是说，为了使原始表达式有效，需要删除的括号的最佳数目是 k。我们可以删除多组不同的 k 括号，这些括号最终将给出相同的最终表达式。但是，每个有效表达式只应记录一次。我们必须在解决方案中解决这个问题。请注意，还有其他可能的方法可以达到上面所示的两个有效表达式之一。对于这两个有效表达式，我们只演示了三种方法。
 *
 * 回到我们的问题上来，现在出现的问题是，如何决定要删除哪些括号？
 *
 * 因为我们不知道哪一个括号可能被删除，所以我们尝试了所有的选项！
 *
 * 对于每个括号，我们有两个选择：
 *
 * 它可以被视为最终表达式的一部分
 * 它可以被忽略，也就是说，我们可以从最终表达式中删除它。
 * 这样的问题，我们有多个选择，我们没有战略或指标来贪婪地决定选择哪一个选择，我们尝试了所有的选择，看看哪一个导致了答案。
 *
 * 算法：
 *
 * 初始化最终将存储所有有效表达式的数组。
 * 从给定序列中最左边的括号开始，然后向右递归
 * 递归状态由我们当前在原始表达式中处理的索引定义。让这个索引用字符 i 来表示。另外，我们有两个不同的变量 left_count 和 right_count ，它们表示我们到目前为止添加到表达式中的左括号和右括号的数目。这些是被考虑的括号。
 * 如果当前字符，即 s[i]（考虑 s 是表达式字符串）既不是右括号也不是左括号，那么我们只需将此字符添加到当前递归的最终解决方案字符串中。
 * 但是，如果当前字符是两个方括号中的一个，即 S[i] == '(' or S[i] == ')'，则我们有两个选项。我们可以通过将此字符标记为无效字符来丢弃它，也可以将此括号视为最终表达式的一部分。
 * 当原始表达式中的所有括号都被处理后，我们只需检查 expr 表示的表达式是否有效，即到目前为止形成的表达式是否有效。我们检查最后一个表达式是否有效的方法是通过查看 left_count 和 right_count 的值。表达式必须是有效的 left_count == right_count。如果它确实有效，那么它可能是我们可能的解决方案之一。
 * 即使我们有一个有效的表达式，我们也需要跟踪我们为获得这个表达式所做的删除操作的数量。这是由另一个名为 rem_count 的递归中传递的变量完成的。
 * 一旦递归完成，我们将检查 rem_count 的当前值是否小于我们迄今为止为形成有效表达式所采取的最少步骤数，即全局最小值。如果不是这样的话，我们不会记录新的表达式，否则我们会记录它。
 * 从实现的角度来看，我们可以做的一个小的优化就是在我们的算法中引入某种修剪。现在，我们只需到最后一步，即处理所有的括号，当我们处理完所有的括号后，我们检查我们的表达式是否可以被考虑。
 *
 * 我们必须等到最后才决定递归中形成的表达式是否是有效的表达式。有没有一种方法可以让我们从早期的一些递归路径中切断，因为它们不会导致一个解决方案？答案是肯定的！优化基于以下思想。
 *
 * 对于递归过程中遇到的左括号，如果我们决定考虑它，那么它可能会导致或可能不会导致无效的最终表达式。如果后面没有匹配的右括号，最终可能导致表达式无效。但是，我们不确定这是否会发生。
 *
 * 但是，对于右括号，如果我们决定将其作为最终表达式的一部分保留（请记住，对于每个括号，我们都有两个选项，要么保留它，要么删除它并进一步递归），并且到目前为止表达式中没有对应的左括号来匹配它，那么无论之后做什么，它都肯定会导致无效表达式。
 *
 *
 * ( (  ) ) )
 * 在这种情况下，第三个右括号将使表达式无效。不管之后会发生什么，这都会给我们一个无效的表达式，如果发生这种情况，我们不应该进一步递归，只需要修剪递归树。
 *
 * 这就是为什么，除了在我们当前正在处理的原始字符串/表达式中具有索引之外 ，并且到目前为止已经形成了表达式字符串之外，我们还跟踪左括号和右括号的数量。每当我们在表达式中保留左括号时，我们就增加它的计数器。对于右括号，我们检查 right_count < left_count。如果是这种情况，那么我们只考虑右括号，并进一步递归。否则，我们不知道它会使表达式无效。这个简单的优化节省了很多运行时间。
 *
 */
public class removeInvalidParentheses_301 {
    class Solution {

        private Set<String> validExpressions = new HashSet<String>();
        private int minimumRemoved;

        private void reset() {
            this.validExpressions.clear();
            this.minimumRemoved = Integer.MAX_VALUE;
        }

        private void recurse(
                String s,
                int index,
                int leftCount,
                int rightCount,
                StringBuilder expression,
                int removedCount) {

            // If we have reached the end of string.
            if (index == s.length()) {

                // If the current expression is valid.
                if (leftCount == rightCount) {

                    // If the current count of removed parentheses is <= the current minimum count
                    if (removedCount <= this.minimumRemoved) {

                        // Convert StringBuilder to a String. This is an expensive operation.
                        // So we only perform this when needed.
                        String possibleAnswer = expression.toString();

                        // If the current count beats the overall minimum we have till now
                        if (removedCount < this.minimumRemoved) {
                            this.validExpressions.clear();
                            this.minimumRemoved = removedCount;
                        }
                        this.validExpressions.add(possibleAnswer);
                    }
                }
            } else {

                char currentCharacter = s.charAt(index);
                int length = expression.length();

                // If the current character is neither an opening bracket nor a closing one,
                // simply recurse further by adding it to the expression StringBuilder
                if (currentCharacter != '(' && currentCharacter != ')') {
                    expression.append(currentCharacter);
                    this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount);
                    expression.deleteCharAt(length);
                } else {

                    // Recursion where we delete the current character and move forward
                    this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount + 1);
                    expression.append(currentCharacter);

                    // If it's an opening parenthesis, consider it and recurse
                    if (currentCharacter == '(') {
                        this.recurse(s, index + 1, leftCount + 1, rightCount, expression, removedCount);
                    } else if (rightCount < leftCount) {
                        // For a closing parenthesis, only recurse if right < left
                        this.recurse(s, index + 1, leftCount, rightCount + 1, expression, removedCount);
                    }

                    // Undoing the append operation for other recursions.
                    expression.deleteCharAt(length);
                }
            }
        }

        public List<String> removeInvalidParentheses(String s) {

            this.reset();
            this.recurse(s, 0, 0, 0, new StringBuilder(), 0);
            return new ArrayList(this.validExpressions);
        }
    }


}
