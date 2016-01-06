package generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 18-12-15.
 *
 *
 */
public abstract class Tree<K, V> extends Node<K, V> implements Nested<Tree<K, V>>{


    private final Tree<K, V> parent;
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

    public List<Tree<K, V>> getChildren() {
        return children;
    }


    public Tree<K, V> getParent() {
        return parent;
    }


    public Tree<K, V> getRoot(){

        if(isRoot()){
            return this;
        }
        return parent.getRoot();
    }

    //============================//


    //========= BUSINESS LOGIC ========//


    protected abstract void insert(K key, V value);

    protected abstract void delete(K key);



}
