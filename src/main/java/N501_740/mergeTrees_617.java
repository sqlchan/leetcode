package N501_740;

import N94_120.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 合并二叉树
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 *
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 *
 * 输入:
 * 	Tree 1                     Tree 2
 *           1                         2
 *          / \                       / \
 *         3   2                     1   3
 *        /                           \   \
 *       5                             4   7
 * 输出:
 * 合并后的树:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \
 * 	 5   4   7
 *
 * 方法二：广度优先搜索
 * 也可以使用广度优先搜索合并两个二叉树。首先判断两个二叉树是否为空，如果两个二叉树都为空，则合并后的二叉树也为空，如果只有一个二叉树为空，则合并后的二叉树为另一个非空的二叉树。
 *
 * 如果两个二叉树都不为空，则首先计算合并后的根节点的值，然后从合并后的二叉树与两个原始二叉树的根节点开始广度优先搜索，从根节点开始同时遍历每个二叉树，并将对应的节点进行合并。
 *
 * 使用三个队列分别存储合并后的二叉树的节点以及两个原始二叉树的节点。初始时将每个二叉树的根节点分别加入相应的队列。每次从每个队列中取出一个节点，判断两个原始二叉树的节点的左右子节点是否为空。如果两个原始二叉树的当前节点中至少有一个节点的左子节点不为空，则合并后的二叉树的对应节点的左子节点也不为空。对于右子节点同理。
 *
 * 如果合并后的二叉树的左子节点不为空，则需要根据两个原始二叉树的左子节点计算合并后的二叉树的左子节点以及整个左子树。考虑以下两种情况：
 *
 * 如果两个原始二叉树的左子节点都不为空，则合并后的二叉树的左子节点的值为两个原始二叉树的左子节点的值之和，在创建合并后的二叉树的左子节点之后，将每个二叉树中的左子节点都加入相应的队列；
 *
 * 如果两个原始二叉树的左子节点有一个为空，即有一个原始二叉树的左子树为空，则合并后的二叉树的左子树即为另一个原始二叉树的左子树，此时也不需要对非空左子树继续遍历，因此不需要将左子节点加入队列。
 *
 * 对于右子节点和右子树，处理方法与左子节点和左子树相同。
 *
 */
public class mergeTrees_617 {
    class Solution {
        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            if (t1 == null) {
                return t2;
            }
            if (t2 == null) {
                return t1;
            }
            TreeNode merged = new TreeNode(t1.val + t2.val);
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
            Queue<TreeNode> queue2 = new LinkedList<TreeNode>();
            queue.offer(merged);
            queue1.offer(t1);
            queue2.offer(t2);
            while (!queue1.isEmpty() && !queue2.isEmpty()) {
                TreeNode node = queue.poll(), node1 = queue1.poll(), node2 = queue2.poll();
                TreeNode left1 = node1.left, left2 = node2.left, right1 = node1.right, right2 = node2.right;
                if (left1 != null || left2 != null) {
                    if (left1 != null && left2 != null) {
                        TreeNode left = new TreeNode(left1.val + left2.val);
                        node.left = left;
                        queue.offer(left);
                        queue1.offer(left1);
                        queue2.offer(left2);
                    } else if (left1 != null) {
                        node.left = left1;
                    } else if (left2 != null) {
                        node.left = left2;
                    }
                }
                if (right1 != null || right2 != null) {
                    if (right1 != null && right2 != null) {
                        TreeNode right = new TreeNode(right1.val + right2.val);
                        node.right = right;
                        queue.offer(right);
                        queue1.offer(right1);
                        queue2.offer(right2);
                    } else if (right1 != null) {
                        node.right = right1;
                    } else {
                        node.right = right2;
                    }
                }
            }
            return merged;
        }
    }

}
