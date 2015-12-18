package interfaces;

/**
 * Created by jonathan on 18-12-15.
 */
public abstract class Node<K, V> {

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    private final K key;
    protected final V value;


    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
