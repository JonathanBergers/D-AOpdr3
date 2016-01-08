package F;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

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
public class RedTrie<D, T extends Collection<D>> implements Trie<T>{

    protected RedTrie<D, T> parent;

    private T data;
    private String key;
    private ArrayList<RedTrie<D, T>> children = new ArrayList<RedTrie<D, T>>();

    public RedTrie(String key, T data){
        this.key = key;
        this.data = data;
    }
    public RedTrie(T data){
        key = "";
        this.data = data;
    }





    private boolean insertRec(String word, T data){
        // word already in trie, just add data
        if(this.hasKey(word)){
            this.data.addAll(data);
        }
        //prefix same?
        if(key.startsWith(word.substring(0,1))) {

            //add remaining
            if (key.length() == 1) {
                children.add(new RedTrie<D, T>(word.substring(1), data));
                return true;
            } else {

                // DIT IS HET GEVAL DAT HET WOORD ZELFDE BEGIN HEEFT ALS KEY
                // BIJV INSERT ABC, IN EEN TRIE MET A, en AB
                //REGEL 123

                RedTrie<D, T> newChild = new RedTrie<D, T>(key.substring(1), this.data);
                children.add(newChild);
                this.key = key.substring(0, 1);

                this.insert(word.substring(1), data);

            }

        }
        return false;

    }
    public void insert(String word, T data) {


        for(RedTrie<D, T> child : children){

            if(child.insertRec(word, data)){
                return;
            }
        }


//
//
//
//            final String wordPrefix = word.substring(0,1);
//        Optional<RedTrie<D,T>> childWithPrefix = children.stream().filter(dtRedTrie -> dtRedTrie.hasKey(wordPrefix)).findFirst();
//
//        if(childWithPrefix.isPresent()){
//            childWithPrefix.get().insert(word.substring(1), data);
//            return;
//        }else {
//
//        }
//
//        }
//
//        String letter = word.substring(0, 1);
//
//
//        for(RedTrie<D, T> child : children) {
//
//            if(child.hasKey(word)){
//                child.data.addAll(data);
//                return;
//            }
//            if(child.key.startsWith(letter)){
//
//            }
//
////            if(child.hasKey(word)) {
////                if(word.length() > 1){
////                    child.insert(word.substring(1), data);
////                    return;
////                } else {
////                    child.data.addAll(data);
////                    return;
////                }
////            } else if(child.key.substring(0,1).equals(letter)) {
////                String tempString = child.key;
////                T tempData = child.data;
////                children.remove(child);
////                RedTrie<D, T> newChild = new RedTrie<D, T>(letter, data);
////                children.add(newChild);
////                if(tempString.length() > 1){
////                    newChild.insert(tempString.substring(1), tempData);
////                }
////                if (word.length() > 1){
////                    newChild.insert(word.substring(1), data);
////                } else {
////                    newChild.data.addAll(data);
////                }
////                return;
////            }
////        }
//
//        RedTrie<D, T> newChild = new RedTrie<D, T>(word, data);
//        newChild.data.addAll(data);
//        children.add(newChild);

    }


    public boolean hasKey(final String key){

        return this.key == key;
    }


    public T search(String word) {

        if(hasKey(word)){
            return data;
        }

       for(RedTrie<D, T> child : children) {
           if(child.hasKey(word)) {
               return child.data;
           }
            if(child.key.startsWith(word.substring(0,1))){
                return child.search(word.substring(1));
            }
        }

        return null;


    }

    public void delete(String word) {
        if(word == null){
            if(children.size() == 1){
                System.out.print("this = " + key + "\t");
                System.out.println("has data: " + !data.isEmpty());
                RedTrie<D, T> child = children.get(0);
                if(child.children.isEmpty()){
                    if(data.isEmpty()){
                        System.out.println("child = " + child.key);
                        System.out.println();
                        key += child.key;
                        data.addAll(child.data);
                        children.remove(child);
                    }
                } else {
                    child.delete(null);
                }
            }
            return;
        }

        String letter = word.substring(0, 1);
        for(RedTrie<D, T> child : children) {
            if (child.key.equals(letter)) {
                if(word.length() > 1){
                    //recursive
                    child.delete(word.substring(1));
                }
                if(child.children.isEmpty()){
                    children.remove(child);
                } else {
                    if(word.length() == 1){
                        child.data.clear();
                    }
                }

            } else if (child.key.equals(word)){
                //the child is a string node and thus a leaf.
                children.remove(child);
            }
            this.delete(null);

        }


    }










    public String toDOTString() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph mijngraaf {\n");

        // Collect all the nodes
        ArrayList<RedTrie<D, T>> lookup = new ArrayList<RedTrie<D, T>>();
        lookup.add(this);
        for (int i = 0; i < lookup.size(); i++) {
            RedTrie<D, T> currentNode = lookup.get(i);
            for (RedTrie<D, T> child : currentNode.children) {
                if (!lookup.contains(child)) {
                    lookup.add(child);
                }
            }
        }

        // Create nodes
        for (int i = 0; i < lookup.size(); i++) {
            sb.append(String.format("n%d [label=\"%s\"];\n", i, lookup.get(i).key));
        }

        // Create edges
        for (int i = 0; i < lookup.size(); i++) {
            RedTrie<D, T> node = lookup.get(i);
            for (RedTrie<D, T> child : node.children) {
                sb.append(String.format("n%d -> n%d;\n", i, lookup.indexOf(child)));
            }
        }

        sb.append("}");
        return sb.toString();
    }
}

