package N201_N230;

import N120_140.ListNode;

//反转一个单链表。
public class ReverseList_206 {
    /**
     * 方法二：递归
     * 递归版本稍微复杂一些，其关键在于反向工作。假设列表的其余部分已经被反转，现在我们应该如何反转它前面的部分？
     *
     * 假设列表为：
     *
     * n_{1}\rightarrow \ldots \rightarrow n_{k-1} \rightarrow n_{k} \rightarrow n_{k+1} \rightarrow \ldots \rightarrow n_{m} \rightarrow \varnothing
     * n
     * 1
     * ​
     *  →…→n
     * k−1
     * ​
     *  →n
     * k
     * ​
     *  →n
     * k+1
     * ​
     *  →…→n
     * m
     * ​
     *  →∅
     *
     * 若从节点 n_{k+1}n
     * k+1
     * ​
     *   到 n_{m}n
     * m
     * ​
     *   已经被反转，而我们正处于 n_{k}n
     * k
     * ​
     *  。
     *
     * n_{1}\rightarrow \ldots \rightarrow n_{k-1} \rightarrow n_{k} \rightarrow n_{k+1} \leftarrow \ldots \leftarrow n_{m}
     * n
     * 1
     * ​
     *  →…→n
     * k−1
     * ​
     *  →n
     * k
     * ​
     *  →n
     * k+1
     * ​
     *  ←…←n
     * m
     * ​
     *
     *
     * 我们希望 n_{k+1}n
     * k+1
     * ​
     *   的下一个节点指向 n_{k}n
     * k
     * ​
     *  。
     *
     * 所以，n_k.\textit{next}.\textit{next} = n_{k}n
     * k
     * ​
     *  .next.next=n
     * k
     * ​
     *  。
     *
     * 要小心的是 n_{1}n
     * 1
     * ​
     *   的下一个必须指向 \varnothing∅ 。如果你忽略了这一点，你的链表中可能会产生循环。如果使用大小为 22 的链表测试代码，则可能会捕获此错误。
     *
     */

    class Solution {
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode p = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return p;
        }
    }

}
