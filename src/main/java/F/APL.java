package F;


import java.util.ArrayList;

/**
 * Created by falco on 6-1-16.
 */
public class APL {
    public static  void main(String[] args){

        RedTrie<String, ArrayList<String>> trie = new RedTrie();

        trie.insert("ok", "i");
        trie.insert("o", "i");
        trie.insert("okee", "i");
        trie.insert("okedokee", "i");
        trie.insert("okedo", "i");

        trie.delete("okee");
        trie.delete("okedo");
        trie.delete(null);

        System.out.println(trie.toDOTString());

    }

}
