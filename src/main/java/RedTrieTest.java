import F.RedTrie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by jonathan on 8-1-16.
 */
public class RedTrieTest {


    public static void main(String[] args) {
//
//        testInsert();
        try {
            ArrayList<String> dict = (ArrayList<String>) Files.readAllLines(Paths.get("dictionary.txt"));

            long begin = System.currentTimeMillis();
            dict.indexOf("sibilance");
            dict.indexOf("sibilance");
            long end = System.currentTimeMillis();
            System.out.println(end - begin);


            RedTrie<String> trie = new RedTrie<>();
            ArrayList<String> a = new ArrayList<>();
            a.add("Jo");
            dict.forEach(new Consumer<String>() {
                @Override
                public void accept(String s) {
                    trie.insert(s, "hi");

                }
            });

                    begin = System.currentTimeMillis();
      System.out.println(trie.search("siblilance"));
            System.out.println(trie.search("siblilance"));
            end = System.currentTimeMillis();
            System.out.println(end - begin);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void testInsert(){

        ArrayList<String> dict = new ArrayList<>();

        dict.add("a");
        dict.add("e");
        dict.add("o");
        dict.add("ee");
        dict.add("eea");


        RedTrie<String> trie = new RedTrie<>();


        dict.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {

                trie.insert(s, s);

            }
        });

        System.out.println(trie.search("eea"));
        System.out.println(trie.toDOTString());

    }





}
