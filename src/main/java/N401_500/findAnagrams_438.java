package N401_500;

import java.util.ArrayList;
import java.util.List;

/**
 * 找到字符串中所有字母异位词
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 *
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 输入:
 * s: "cbaebabacd" p: "abc"
 *
 * 输出:
 * [0, 6]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *
 * 该题目用滑动窗口思想解决的基本思路是：
 *
 * 首先，计算字符串p中各个元素出现的次数，由于字符串只包含小写英文字母，所以可以用数组来记录每个元素出的次数。
 *
 * 接着，用变量start表示窗口的起始位置，变量end表示窗口的结束位置，区间[start,end]用于记录当前窗口中的元素。在这里扩大窗口右侧边界的条件是——字符串s还有剩余元素为考察且窗口[start,end]内的字符长度小于字符串p的长度；缩小窗口左侧边界的条件是——窗口[start,end]内字符的长度等于字符串p的长度。
 *
 * 当，窗口[start,end]内字符的长度等于字符串p的长度时，接着要做的就是判断窗口内的字符串是不是字符串p的字母异位词(每个字母出现次数相同，只是顺序不同)。
 *
 */
public class findAnagrams_438 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> resultList = new ArrayList<>();

        // 计算字符串p中各元素的出现次数
        int[] pFreq = new int[26];
        for(int i = 0; i < p.length(); i++) {
            pFreq[p.charAt(i)-'a']++;
        }

        // 窗口区间为[start,end]
        int start = 0, end = -1;
        while (start <s.length()) {
            // 还有剩余元素未考察，且窗口内字符串长度小于字符串p的长度
            // 则扩大窗口右侧边界
            if (end+1 < s.length() && end-start+1 <p.length()) {
                end++;
            }else {
                // 右侧边界不能继续扩大或窗口内字符串长度等于字符串p的长度
                // 则缩小左侧边界
                start++;
            }

            // 当窗口内字符串长度等于字符串p的长度时，则判断其是不是字符串p的字母异位词子串
            if (end-start+1 == p.length() && isAnagrams(s.substring(start,end+1), pFreq)) {
                resultList.add(start);
            }
        }
        return resultList;
    }

    // 判断当前子串是不是字符串p的字母异位词
    private boolean isAnagrams(String window, int[] pFreq) {
        // 计算窗口内字符串各元素的出现次数
        int[] windowFreq = new int[26];
        for(int i = 0; i < window.length(); i++) {
            windowFreq[window.charAt(i)-'a']++;
        }

        // 比较窗口内各元素的出现次数和字符串p中各元素的出现次数是否一样
        // 如果都一样，则说明窗口内的字符串是字符串p的字母异位词子串
        // 如果不一样，则说明不是其子串
        for(int j = 0; j < 26; j++) {
            if (windowFreq[j] != pFreq[j]) {
                return false;
            }
        }
        return true;
    }

}
