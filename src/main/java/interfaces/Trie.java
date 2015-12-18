package interfaces;

/**
 * Created by jonathan on 18-12-15.
 */
public interface Trie<T> {


    void insert(final String s, T data);


    T[] search(String s);

    void delete(String s);
}
