package N201_N230;

/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 */
public class Trie_208 {
    public class Trie {
        private boolean is_string=false;
        private Trie next[]=new Trie[26];

        public Trie(){}

        public void insert(String word){//插入单词
            Trie root=this;
            char w[]=word.toCharArray();
            for(int i=0;i<w.length;++i){
                if(root.next[w[i]-'a']==null)root.next[w[i]-'a']=new Trie();
                root=root.next[w[i]-'a'];
            }
            root.is_string=true;
        }

        public boolean search(String word){//查找单词
            Trie root=this;
            char w[]=word.toCharArray();
            for(int i=0;i<w.length;++i){
                if(root.next[w[i]-'a']==null)return false;
                root=root.next[w[i]-'a'];
            }
            return root.is_string;
        }

        public boolean startsWith(String prefix){//查找前缀
            Trie root=this;
            char p[]=prefix.toCharArray();
            for(int i=0;i<p.length;++i){
                if(root.next[p[i]-'a']==null)return false;
                root=root.next[p[i]-'a'];
            }
            return true;
        }
    }
}
