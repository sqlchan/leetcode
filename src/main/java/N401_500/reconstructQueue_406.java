package N401_500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 根据身高重建队列
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 *
 * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 *
 * 方法二：从高到低考虑
 * 思路与算法
 *
 * 同样地，我们也可以将每个人按照身高从大到小进行排序，处理身高相同的人使用的方法类似，即：按照 h_ih
 * i
 * ​
 *   为第一关键字降序，k_ik
 * i
 * ​
 *   为第二关键字升序进行排序。如果我们按照排完序后的顺序，依次将每个人放入队列中，那么当我们放入第 ii 个人时：
 *
 * 第 0, \cdots, i-10,⋯,i−1 个人已经在队列中被安排了位置，他们只要站在第 ii 个人的前面，就会对第 ii 个人产生影响，因为他们都比第 ii 个人高；
 *
 * 而第 i+1, \cdots, n-1i+1,⋯,n−1 个人还没有被放入队列中，并且他们无论站在哪里，对第 ii 个人都没有任何影响，因为他们都比第 ii 个人矮。
 *
 * 在这种情况下，我们无从得知应该给后面的人安排多少个「空」位置，因此就不能沿用方法一。但我们可以发现，后面的人既然不会对第 ii 个人造成影响，我们可以采用「插空」的方法，依次给每一个人在当前的队列中选择一个插入的位置。也就是说，当我们放入第 ii 个人时，只需要将其插入队列中，使得他的前面恰好有 k_ik
 * i
 * ​
 *   个人即可。
 *
 */
public class reconstructQueue_406 {
    class Solution {
        public int[][] reconstructQueue(int[][] people) {
            Arrays.sort(people, new Comparator<int[]>() {
                public int compare(int[] person1, int[] person2) {
                    if (person1[0] != person2[0]) {
                        return person2[0] - person1[0];
                    } else {
                        return person1[1] - person2[1];
                    }
                }
            });
            List<int[]> ans = new ArrayList<int[]>();
            for (int[] person : people) {
                ans.add(person[1], person);
            }
            return ans.toArray(new int[ans.size()][]);
        }
    }

}
