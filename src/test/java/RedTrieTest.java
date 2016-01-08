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

        testInsert();
        try {
            ArrayList<String> dict = (ArrayList<String>) Files.readAllLines(Paths.get("dictionary.txt"));

            long begin = System.currentTimeMillis();
            dict.indexOf("sibilance");
            dict.indexOf("sibilance");
            long end = System.currentTimeMillis();
            System.out.println(end - begin);


            RedTrie<String, List<String>> trie = new RedTrie<>(new ArrayList<>());
            ArrayList<String> a = new ArrayList<>();
            a.add("Jo");
            dict.forEach(new Consumer<String>() {
                @Override
                public void accept(String s) {
                    ArrayList<String> a = new ArrayList<String>();
                    a.add(s);
                    trie.insert(s, a);

                }
            });

                    begin = System.currentTimeMillis();
//            System.out.println(Arrays.toString(trie.search("siblilance")));
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

        RedTrie<String, List<String>> trie = new RedTrie<>(new ArrayList<>());
        dict.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                ArrayList<String> a = new ArrayList<String>();
                a.add(s);
                trie.insert(s, a);

            }
        });

        System.out.println(trie.toDOTString());


    }





}
