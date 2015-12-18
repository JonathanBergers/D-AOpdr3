package implementation;

import generic.Tree;
import model.Data;

/**
 *
 * /**
 * Created by jonathan on 18-12-15.
 * Een red-trie (reduced trie) is een boomstructuur, waarin elke interne knoop één letter bevat en
 nul of meer subknopen heeft, en elke externe knoop (blad) een string bevat. Daarnaast kan een
 knoop willekeurige data bevatten, die iets te maken heeft met het door die knoop
 gerepresenteerde woord.
 *
 *
 * red tree node kan een letter bevatten OF een string (wanneer deze een leaf is).
 * ook heeft een red tree node extra data
 *
 * Created by jonathan on 18-12-15.
 *NOW IMPLEMENTED WITH DATA CLASS..
 *
 */
public class RedTrie extends Tree<String, Data>{


    public RedTrie(String key, Data value, Tree parent) {
        super(key, value, parent);
    }


    public RedTrie(String key, Data value) {
        super(key, value);
    }


    /**Constructor for leaf
     *
     * @param key
     * @param value
     * @param parent
     */
    public RedTrie(char key, Data value, Tree parent) {
        super(""+key, value, parent);
    }



    public Data[] search(String s){
        //TODO
        return null;
    }

    @Override
    protected void insert(String key, Data value) {

        //TODO

    }

    @Override
    protected void delete(String key) {

        //TODO

    }
}
