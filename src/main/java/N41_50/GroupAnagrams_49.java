package N41_50;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 49. 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 将单词转化成char[]，排序后作为HashMap的key值，value值就是一个List<String>用于存放字母相同的单词
 */
public class GroupAnagrams_49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> hashMap = new HashMap<>();//根据String把元素放入对应的ListString
        List<String> anagramGroup;//组，存放字母一样的单词
        for (String curStr : strs) {
            String sortedStr = sortLetters(curStr);//每个单词按字母排序
            if (hashMap.containsKey(sortedStr)) {//存在该排序后单词的key，则取出value，将curStr插入
                anagramGroup = hashMap.get(sortedStr);
                anagramGroup.add(curStr);
            } else {//不存在该key 则新建一个链表，将curStr add进去，在将该value即list 以sortedStr为key put进去
                anagramGroup = new ArrayList<>();
                anagramGroup.add(curStr);
                hashMap.put(sortedStr, anagramGroup);
            }
        }
        return new ArrayList<>(hashMap.values());
    }

    //用于给单词 按字典顺序排序
    private String sortLetters(String word) {
        char[] letters = word.toCharArray();
        Arrays.sort(letters);
        return new String(letters);
    }

}
