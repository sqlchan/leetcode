package N501_740;

import java.util.HashMap;
import java.util.Map;

/**
 * 任务调度器
 * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
 *
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 *
 * 你需要计算完成所有任务所需要的 最短时间 。
 *
 * ：利用一个计数Map记录剩余未调度每个字母的个数，利用一个时间Map记录每个字母的冷却时间，每次调度选择冷却时间为0中计数最大的字母，若不存在这种字母则进入一轮待命状态。若存在这种字母，则进行调度，该字母计数减一，冷却时间变为n。其中过一轮，每个冷却时间不为0的字母冷却时间减一。
 */
public class leastInterval_621 {
    class Solution {
        public int leastInterval(char[] tasks, int n) {
            //计数Map
            Map<Character,Integer> countMap = new HashMap<>();
            //冷却时间Map
            Map<Character,Integer> timeMap = new HashMap<>();
            //初始化两个Map
            for(char c:tasks){
                countMap.put(c,countMap.getOrDefault(c,0)+1);
                timeMap.put(c,0);
            }
            //记录调度轮数
            int resCount = 0;
            //记录已调度字母的个数
            int count = 0;
            while(count < tasks.length){
                //寻找数组种第一个冷却时间为0，且计数大于零的字母
                char tmp = 'a';
                for(char c:timeMap.keySet()){
                    if(timeMap.get(c)==0 && countMap.get(c)>0){
                        tmp=c;
                        break;
                    }
                }
                //若不存在则进入一轮待命状态
                if(tmp=='a'){
                    resCount++;
                    for(char c:timeMap.keySet()){
                        if(timeMap.get(c)>0) timeMap.put(c,timeMap.get(c)-1);
                    }
                    continue;
                }
                //寻找冷却时间为0中计数最大的字母
                for(char c:countMap.keySet()){
                    if(timeMap.get(c)==0 && countMap.get(tmp)<countMap.get(c)) tmp = c;
                }
                countMap.put(tmp,countMap.get(tmp)-1);
                for(char c:timeMap.keySet()){
                    if(timeMap.get(c)>0) timeMap.put(c,timeMap.get(c)-1);
                }
                timeMap.put(tmp,n);
                count++;
                resCount++;
            }
            return resCount;
        }
    }
}
