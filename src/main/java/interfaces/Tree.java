package interfaces;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 18-12-15.
 *
 * red tree node kan een letter bevatten OF een string (wanneer deze een leaf is).
 * ook heeft een red tree node extra data
 */
public class Tree<K, V> extends Node<K, V> implements Nested<Tree<K, V>>{


    private final Tree parent;
    private List<Tree<K, V>> children = new ArrayList<>();


    //========= CONSTRUCTORS ===============//
    /**Constructor for subnode
     *
     * @param key
     * @param value
     * @param parent
     */
    public Tree(K key, V value, Tree parent) {
        super(key, value);
        this.parent = parent;
    }

    /**
     * Constructor for root
     * @param key
     * @param value
     */
    public Tree(K key, V value) {
        super(key, value);
        this.parent = null;
    }
    //============================//



    //=========== GETTERS ==========//
    @Override
    public List<Tree<K, V>> getChildren() {
        return children;
    }

    @Override
    public Tree<K, V> getParent() {
        return parent;
    }

    //============================//


    //========= BUSINESS LOGIC ========//




}
