package N120_140;

/**
 * 141. 环形链表
 * 给定一个链表，判断链表中是否有环。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 *
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 *
 * 一.解题思路：
 *
 * 判空
 * 设置慢指针l=head;快指针r=head,
 * 相当于两人从同一起跑线开始跑步，l每次跑一步，r每次跑2步，如果是环形跑道，l，r终究会相遇
 * 如果没有环，则r一定先跑到终点,所以无需判断l是否到达终点
 * 只要r!=null(r没到终点)且r.next != null(r跑一步也不会到终点),则两人一直循环跑下去
 * 每循环一次，l跑一步l = l.next，r跑两步r = r.next.next
 * 跑完后判断下，l == r（l跟r是否相遇了），相遇表示有环返回true
 * 如果循环结束，标示r跑到了终点还没跟l相遇，则没有环，返回false
 *
 */
public class HasCycle_141 {
    class Solution {
        public boolean hasCycle(ListNode head) {
            if (head == null) {
                return false;
            }
            ListNode l = head;
            ListNode r = head;
            while (r != null && r.next != null) {
                l = l.next;
                r = r.next.next;
                if (l == r) {
                    return true;
                }
            }
            return false;
        }
    }
}
