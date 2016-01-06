package F;

import java.util.ArrayList;

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
public class RedTrie<D> implements Trie<D>{

    private D data;
    private String value;
    private ArrayList<RedTrie<D>> children = new ArrayList<RedTrie<D>>();

    public RedTrie(String value){
        this.value = value;
    }
    public RedTrie(){
        value = "";
    }

    public void insert(String word, D data) {
        String letter = word.substring(0, 1);

        for(RedTrie<D> child : children) {
            if(child.value.equals(letter)) {
                if(word.length() > 1){
                    child.insert(word.substring(1), data);
                    return;
                } else {
                    child.data = data;
                    return;
                }
            } else if(child.value.substring(0,1).equals(letter)) {
                String tempString = child.value;
                D tempData = child.data;
                children.remove(child);
                RedTrie newChild = new RedTrie<D>(letter);
                children.add(newChild);
                if(tempString.length() > 1){
                    newChild.insert(tempString.substring(1), tempData);
                }
                if (word.length() > 1){
                    newChild.insert(word.substring(1), data);
                } else {
                    newChild.data = data;
                }
                return;
            }
        }

        RedTrie<D> newChild = new RedTrie<>(word);
        newChild.data = data;
        children.add(newChild);

    }

    public D[] search(String word) {
        return null;
    }

    public void delete(String word) {
        String letter = word.substring(0, 1);

        for(RedTrie<D> child : children) {
            if (child.value.equals(letter)) {

                if(word.length() > 1){
                    child.delete(word.substring(1));
                }
                if(child.children.isEmpty()){
                    children.remove(child);
                }
                return;
            } else if (child.value.equals(word)){
                children.remove(child);
                return;
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
            sb.append(String.format("n%d [label=\"%s\"];\n", i, lookup.get(i).value));
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

