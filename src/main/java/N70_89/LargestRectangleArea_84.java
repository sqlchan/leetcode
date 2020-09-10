package N70_89;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * 我们可以遍历每根柱子，以当前柱子 i 的高度作为矩形的高，那么矩形的宽度边界即为向左找到第一个高度小于当前柱体 i 的柱体，向右找到第一个高度小于当前柱体 i 的柱体。
 *
 * 对于每个柱子我们都如上计算一遍以当前柱子作为高的矩形面积，最终比较出最大的矩形面积即可。
 *
 * 我们每遍历到当前柱体 i 时：
 *
 * 上述写法中，我们需要再嵌套一层 while 循环来向左找到第一个比柱体 i 高度小的柱体，这个过程是 O(N) 的；
 * 那么我们可以 O(1) 的获取柱体 i 左边第一个比它小的柱体吗？答案就是单调增栈，因为对于栈中的柱体来说，栈中下一个柱体就是左边第一个高度小于自身的柱体。
 * 因此做法就很简单了，我们遍历每个柱体，若当前的柱体高度大于等于栈顶柱体的高度，就直接将当前柱体入栈，否则若当前的柱体高度小于栈顶柱体的高度，说明当前栈顶柱体找到了右边的第一个小于自身的柱体，那么就可以将栈顶柱体出栈来计算以其为高的矩形的面积了。
 */
public class LargestRectangleArea_84 {
    public int largestRectangleArea(int[] heights) {
        // 这里为了代码简便，在柱体数组的头和尾加了两个高度为 0 的柱体。
        int[] tmp = new int[heights.length + 2];
        System.arraycopy(heights, 0, tmp, 1, heights.length);

        Deque<Integer> stack = new ArrayDeque<>();
        int area = 0;
        for (int i = 0; i < tmp.length; i++) {
            // 对栈中柱体来说，栈中的下一个柱体就是其「左边第一个小于自身的柱体」；
            // 若当前柱体 i 的高度小于栈顶柱体的高度，说明 i 是栈顶柱体的「右边第一个小于栈顶柱体的柱体」。
            // 因此以栈顶柱体为高的矩形的左右宽度边界就确定了，可以计算面积🌶️ ～
            while (!stack.isEmpty() && tmp[i] < tmp[stack.peek()]) {
                int h = tmp[stack.pop()];
                area = Math.max(area, (i - stack.peek() - 1) * h);
            }
            stack.push(i);
        }

        return area;
    }
}
