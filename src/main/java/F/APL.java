package F;



/**
 * Created by falco on 6-1-16.
 */
public class APL {
    public static  void main(String[] args){

        RedTrie<Boolean> trie = new RedTrie();

        trie.insert("ok", false);
        trie.insert("o", false);
        trie.insert("okee", true);
        trie.insert("okedokee", true);

        trie.delete("okee");

        System.out.println(trie.toDOTString());

    }

}
