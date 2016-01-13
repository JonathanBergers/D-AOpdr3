package F;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
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
public class RedTrie<D> implements Trie<D>{

    private ArrayList<D> data = new ArrayList<D>();
    private String key;
    private ArrayList<RedTrie<D>> children = new ArrayList<RedTrie<D>>();

    public RedTrie(String key){
        this.key = key;
    }

    public RedTrie(String key, D data){
        this.key = key;
        this.data.add(data);
    }
    public RedTrie(){
        key = "";
    }

    public void insert(String word, D data) {
        String letter = word.substring(0, 1);

        for(RedTrie<D> child : children) {
            if(child.key.equals(letter)) {
                if(word.length() > 1){
                    child.insert(word.substring(1), data);

                } else {
                    child.data.add(data);

                }
                return;
            } else if(child.key.substring(0,1).equals(letter)) {
                String tempString = child.key;
                List<D> tempData = child.data;
                children.remove(child);
                RedTrie<D> newChild = new RedTrie<D>(letter);
                children.add(newChild);
                if(tempString.length() > 1){
                    for(D item : tempData){
                        newChild.insert(tempString.substring(1), item);
                    }


                    //newChild.data.addAll(tempData);
                }
                if (word.length() > 1){
                    newChild.insert(word.substring(1), data);
                } else {
                    newChild.data.add(data);
                }
                return;
            }
        }

        RedTrie<D> newChild = new RedTrie<>(word);
        newChild.data.add(data);
        children.add(newChild);

    }

    public List<D> search(String word) {


        String letter = word.substring(0, 1);
        for(RedTrie<D> child : children) {
            if (child.key.equals(letter)) {
                if(word.length() > 1){
                    //System.out.println("return child search from parent \t"+ key);
                    return child.search(word.substring(1));
                } else {

                    return child.data;

                }
            } else if(child.key.equals(word)){

                return child.data;
            }
        }
        return null;

    }

    public void delete(String word) {

        //System.out.print("delete(" + word + ") on " + key);
        //System.out.println("\t \tkey = "+key+ "\t has data = "+!data.isEmpty());

        if(word == null){
            if(children.size() == 1){
                //System.out.println("childrensize == 1");
                //System.out.print("this = " + key + "\t");
                //System.out.println("has data: " + !data.isEmpty());
                RedTrie child = children.get(0);
                if(child.children.isEmpty()){
                    if(data.isEmpty()){
                        //System.out.println("child = " + child.key);
                        System.out.println();
                        key += child.key;
                        data.addAll(child.data);
                        children.remove(child);
                    }
                } else {
                    //System.out.println("delete child");
                    child.delete(null);
                }
            }
            return;
        }

        String letter = word.substring(0, 1);
        for(int i = 0; i<children.size(); i++) {
            RedTrie<D> child = children.get(i);
            if (child.key.equals(letter)) {
                if(word.length() > 1){
                    //recursive
                    child.delete(word.substring(1));
                }
                if(child.children.isEmpty() && child.key.equals(word)){
                    children.remove(child);
                } else {
                    if(word.length() == 1){
                        child.data.clear();
                    }
                }
                child.delete(null);
                break;

            } else if (child.key.equals(word)){
                //the child is a string node and thus a leaf.
                children.remove(child);
            }

        }



    }










    public String toDOTString() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph mijngraaf {\n");

        // Collect all the nodes
        ArrayList<RedTrie<D>> lookup = new ArrayList<RedTrie<D>>();
        lookup.add(this);
        for (int i = 0; i < lookup.size(); i++) {
            RedTrie<D> currentNode = lookup.get(i);
            for (RedTrie<D> child : currentNode.children) {
                if (!lookup.contains(child)) {
                    lookup.add(child);
                }
            }
        }

        // Create nodes
        for (int i = 0; i < lookup.size(); i++) {
            sb.append(String.format("n%d [label=\"%s\"];\n", i, lookup.get(i).key+" "+lookup.get(i).data));
        }

        // Create edges
        for (int i = 0; i < lookup.size(); i++) {
            RedTrie<D> node = lookup.get(i);
            for (RedTrie<D> child : node.children) {
                sb.append(String.format("n%d -> n%d;\n", i, lookup.indexOf(child)));
            }
        }

        sb.append("}");
        return sb.toString();
    }
}

