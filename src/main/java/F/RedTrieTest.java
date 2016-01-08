package F;

import F.RedTrie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jonathan on 8-1-16.
 */
public class RedTrieTest {


    public static void main(String[] args) {
//        try {
//            ArrayList<String> dict = (ArrayList<String>) Files.readAllLines(Paths.get("dictionary.txt"));
//
//            long begin = System.currentTimeMillis();
//            dict.indexOf("sibilance");
//            dict.indexOf("sibilance");
//            long end = System.currentTimeMillis();
//            System.out.println(end - begin);
//
//
//            RedTrie<String, List<String>> trie = new RedTrie<>();
//            dict.forEach(s -> trie.insert(s, s));
//
//            begin = System.currentTimeMillis();
//            System.out.println(Arrays.toString(trie.search("siblilance")));
//            System.out.println(trie.search("siblilance"));
//            end = System.currentTimeMillis();
//            System.out.println(end - begin);
//            System.out.println(trie.toDOTString());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        RedTrie<String, ArrayList<String>> trie = new RedTrie<>();

        trie.insert("o", "ok");
        trie.insert("ok", "ok");
        trie.insert("okeeeeeeee", "ok");
        trie.insert("okeek", "ok");

        trie.delete("okeeeeeeee");
        trie.delete("okeek");


        System.out.println(trie.toDOTString());


    }






}
