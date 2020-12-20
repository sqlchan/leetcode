package N141_200;

import N120_140.ListNode;

/**
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 * 进阶：
 *
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *
 *
 「147. 对链表进行插入排序」要求使用插入排序的方法对链表进行排序，插入排序的时间复杂度是 O(n^2)O(n
 2
 )，其中 nn 是链表的长度。这道题考虑时间复杂度更低的排序算法。题目的进阶问题要求达到 O(n \log n)O(nlogn) 的时间复杂度和 O(1)O(1) 的空间复杂度，时间复杂度是 O(n \log n)O(nlogn) 的排序算法包括归并排序、堆排序和快速排序（快速排序的最差时间复杂度是 O(n^2)O(n
 2
 )），其中最适合链表的排序算法是归并排序。

 归并排序基于分治算法。最容易想到的实现方式是自顶向下的递归实现，考虑到递归调用的栈空间，自顶向下归并排序的空间复杂度是 O(\log n)O(logn)。如果要达到 O(1)O(1) 的空间复杂度，则需要使用自底向上的实现方式。

 方法二：自底向上归并排序
 使用自底向上的方法实现归并排序，则可以达到 O(1)O(1) 的空间复杂度。

 首先求得链表的长度 \textit{length}length，然后将链表拆分成子链表进行合并。

 具体做法如下。

 用 \textit{subLength}subLength 表示每次需要排序的子链表的长度，初始时 \textit{subLength}=1subLength=1。

 每次将链表拆分成若干个长度为 \textit{subLength}subLength 的子链表（最后一个子链表的长度可以小于 \textit{subLength}subLength），按照每两个子链表一组进行合并，合并后即可得到若干个长度为 \textit{subLength} \times 2subLength×2 的有序子链表（最后一个子链表的长度可以小于 \textit{subLength} \times 2subLength×2）。合并两个子链表仍然使用「21. 合并两个有序链表」的做法。

 将 \textit{subLength}subLength 的值加倍，重复第 2 步，对更长的有序子链表进行合并操作，直到有序子链表的长度大于或等于 \textit{length}length，整个链表排序完毕。

 如何保证每次合并之后得到的子链表都是有序的呢？可以通过数学归纳法证明。

 初始时 \textit{subLength}=1subLength=1，每个长度为 11 的子链表都是有序的。

 如果每个长度为 \textit{subLength}subLength 的子链表已经有序，合并两个长度为 \textit{subLength}subLength 的有序子链表，得到长度为 \textit{subLength} \times 2subLength×2 的子链表，一定也是有序的。

 当最后一个子链表的长度小于 \textit{subLength}subLength 时，该子链表也是有序的，合并两个有序子链表之后得到的子链表一定也是有序的。

 因此可以保证最后得到的链表是有序的。

 */
public class SortList_148 {
    class Solution {
        public ListNode sortList(ListNode head) {
            if (head == null) {
                return head;
            }
            int length = 0;
            ListNode node = head;
            while (node != null) {
                length++;
                node = node.next;
            }
            ListNode dummyHead = new ListNode(0, head);
            for (int subLength = 1; subLength < length; subLength <<= 1) {
                ListNode prev = dummyHead, curr = dummyHead.next;
                while (curr != null) {
                    ListNode head1 = curr;
                    for (int i = 1; i < subLength && curr.next != null; i++) {
                        curr = curr.next;
                    }
                    ListNode head2 = curr.next;
                    curr.next = null;
                    curr = head2;
                    for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                        curr = curr.next;
                    }
                    ListNode next = null;
                    if (curr != null) {
                        next = curr.next;
                        curr.next = null;
                    }
                    ListNode merged = merge(head1, head2);
                    prev.next = merged;
                    while (prev.next != null) {
                        prev = prev.next;
                    }
                    curr = next;
                }
            }
            return dummyHead.next;
        }

        public ListNode merge(ListNode head1, ListNode head2) {
            ListNode dummyHead = new ListNode(0);
            ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
            while (temp1 != null && temp2 != null) {
                if (temp1.val <= temp2.val) {
                    temp.next = temp1;
                    temp1 = temp1.next;
                } else {
                    temp.next = temp2;
                    temp2 = temp2.next;
                }
                temp = temp.next;
            }
            if (temp1 != null) {
                temp.next = temp1;
            } else if (temp2 != null) {
                temp.next = temp2;
            }
            return dummyHead.next;
        }
    }

}
