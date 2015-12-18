package implementation;

import interfaces.Nested;
import interfaces.Tree;
import interfaces.Trie;
import model.Data;

import java.util.List;

/**
 * Created by jonathan on 18-12-15.
 *NOW IMPLEMENTED WITH DATA CLASS..
 *
 */
public class RedTrie extends Tree<String, Data> implements Trie<Data> {


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


    @Override
    public void insert(String s, Data data) {

    }

    @Override
    public Data[] search(String s) {
        return new Data[0];
    }

    @Override
    public void delete(String s) {

    }
}
