/**
 * @Hrishikesh Moholkar
 * this file calculates the various aspects of occurences of words
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


/**
 * Created by moholkar.hrishikesh2 on 2/27/2016.
 */
import java.io.File;


public class TextProcessorImpl implements TextProcessor {
    List<String> collection1 = new ArrayList<>();


    public TextProcessorImpl(String filename) {

        TextReader object = new TextReader();
        Scanner input = (TextReader.openFile(filename));
        while (input.hasNext()) {
            String myStr = input.next();
            if (myStr.matches(".*\\d+.*")){
                input.next();
            }else{
                String newStr = myStr.replaceAll("\\p{P}", "");
                if (!newStr.equals("")) {
                    collection1.add(newStr);
                }
            }

        }
        System.out.println(collection1);

    }


    public Collection<Character> mostCommonFirstUnweighted() {
        /**
         * calculates the occurence of  first letter of words
         * without considering frequency ie unique strings
         */
        HashMap<Character, Integer> map1 = new HashMap<>();
        Set<String> map2 = new HashSet<>();
        Collection list1 = new HashSet<>();
       // int var=0;

        for (String each : collection1) {
            map2.add(each);

        }
        for (String each : map2) {
            char key = each.charAt(0);
            if (map1.containsKey(key)) {
                //int var = map1.get(key);
                //var += 1;
                map1.put(key, map1.get(key) + 1);
            } else {
                map1.put(key, 1);
            }
        }
        int largest = 0;
        for (Integer key2 : map1.values()) {
            if (largest < key2) {
                largest = key2;
            }
        }
        for (Character each1 : map1.keySet()) {
            if (map1.get(each1) == largest) {
                list1.add(each1);
            }
        }

        return list1;
}

    public Collection< String > getLongestWords(){
        /**
         * finds the longest word in the text
         */
        //ArrayList<String>longestword=new ArrayList();
        Map<Integer,ArrayList<String>> map=new HashMap<>();
        for ( String each:collection1) {
            int key=each.length();
            if(map.containsKey(key)){
                ArrayList<String> longestword = new ArrayList<>();
                longestword=map.get(key);
                longestword.add(each);
                map.put(key,longestword);
            }
            else{
                ArrayList<String> longestword = new ArrayList<>();
                //map.put(key,longestword);
                //longestword=map.get(key);
                longestword.add(each);
                map.put(key,longestword);
            }
    }
         int largest=0;
         for (Integer key : map.keySet()) {
         if (largest<key){
         largest=key;
         }


    }
        return map.get(largest);
    }

    public Collection< String > getShortestWords() {
        /**
         * find the shortest word in the text
         */

        Map<Integer, Collection<String>> map = new HashMap<>();
        for (String each : collection1) {
            int key = each.length();
            if (map.containsKey(key)) {
                Collection<String> shortestword = new HashSet<>();
                shortestword = map.get(key);
                shortestword.add(each);
                map.put(key, shortestword);
            } else {
                Collection<String> shortestword = new HashSet<>();
                //map.put(key, new ArrayList());
                //shortestword = map.get(key);
                shortestword.add(each);
                map.put(key, shortestword);


            }
        }
        int shortest= Integer.MAX_VALUE;
        for (Integer key : map.keySet()) {
            if (shortest>key){
                shortest=key;

            }

        }
        return map.get(shortest)   ;
    }


    public Collection< String > mostCommon(int length) {
        /**
         * finds the most common word of particular frequency
         */
        /**
        Map<Integer, Collection<String>> map = new HashMap<>();
        Map<ArrayList<String>,Integer> map1=new HashMap<>();
        Collection<String> mostcommonwords = new HashSet<>();
        for (String each : collection1) {
            int key = each.length();
            if (length == key) {

                if (map.containsKey(key)) {
                    mostcommonwords = map.get(key);
                    mostcommonwords.add(each);
                    map.put(key, mostcommonwords);


                } else {
                    map.put(key, new HashSet());
                    mostcommonwords = map.get(key);
                    mostcommonwords.add(each);
                    map.put(key, mostcommonwords);
                }


            }
            if()
*/
        Map<Integer,Integer>map1=new HashMap<>();
        Map<Integer,ArrayList<String>>map2=new HashMap<>();
        for (String each: collection1) {
            int key = each.length();
            if (key == length) {

                if (map1.containsKey(key)) {
                    map1.put(key, map1.get(key) + 1);
                    ArrayList<String> var = new ArrayList<>();
                    var.add(each);
                    map2.put(map1.get(key), var);

                } else {
                    map1.put(key, 1);
                    ArrayList<String> var = new ArrayList<>();
                    var.add(each);
                    map2.put(map1.get(key), var);
                }


            }
        }
        return   map2.get( map1.get(length));

    }
    public Collection< String > mostVowelsWordLength(int length) {
        /**
         * finds the word with most of the vowels
         */


        //Map<Integer, Integer> vowelMap = new HashMap<>();
        Map<Integer, Collection<String>> map = new HashMap<>();
        //Collection<String> name1 = new HashSet<>();
        String vowels = "aeiouAEIOU";

        int max = 0;
        for (String s : collection1) {
            //int key = ();
            if (s.length() == length) {
                int count = 0;
                for (int i = 0; i < s.length(); i++) {
                    for (int j = 0; j < vowels.length(); j++) {
                        if (s.charAt(i) == vowels.charAt(j)) {
                            count++;
                        }
                    }
                }
                if (map.containsKey(count)) {
                    Collection<String> vowelSet = new HashSet<>();
                    vowelSet.add(s);
                    map.put(count, vowelSet);
                } else {
                    Collection<String> vowelSet = new HashSet<>();
                    vowelSet.add(s);
                    map.put(count, vowelSet);
                }
                if (count > max) {
                    max = count;
                }
            }
        }


        if (map.get(max).size() > 0 ){
            return map.get(max);
        }else{
            return new ArrayList<>();
        }

    }


        public Collection<Character> mostCommonFirstWeighted() {
            /**
             * finds the first letter of strings which occured the most considering frequency
             */
            /**

            Map<Integer, Character> secondmap = new HashMap<>();
            Map<Character, Integer> firstmap = new HashMap<>();
            ArrayList<Character>ARRAY=new ArrayList<>();
            int count = 0;
            Character key;
            for (String each : collection1) {
                key = each.charAt(0);
                if (firstmap.containsKey(key)){
                    //&&(secondmap.containsKey(count))) {
                    count=firstmap.get(key);
                    count = count + 1;
                    firstmap.put(key,count);
                    secondmap.put(count,key);
                } else {

                    count= 1;
                    firstmap.put(key, count);
                    //System.out.println(firstmap);
                    Character array1;
                    if (secondmap.containsKey(count)) {
                        ARRAY.add(secondmap.get(count));
                    }
                }


            }
            int largest = 0;
            for (Integer key2 : secondmap.keySet()) {
                if (largest < key2) {
                    largest = key2;


                }

            }
            //return secondmap.get(firstmap.get(largest));
            return ARRAY;
        }

*/       Map<Character,Integer>map1=new HashMap<>();
            Map<Integer,ArrayList<Character>>map2=new HashMap<>();


            for(String each:collection1){
                int count=0;

                Character key=each.charAt(0);
                if (map1.containsKey(key)){

                    map1.put((key),map1.get(key)+1);
                    ArrayList<Character>var=new ArrayList<>();
                    //ArrayList<Character>var=new ArrayList<>();
                    var.add(key);
                    map2.put(map1.get(key),var);

                }
                else{
                    map1.put(key,1);
                    ArrayList<Character>var1=new ArrayList<>();
                    var1.add(key);
                    map2.put(map1.get(key),var1);


                }
            }
            int largest = 0;
            for (Integer key2 : map2.keySet()) {
                if (largest < key2) {
                    largest = key2;




                }


            }
            ArrayList<Character>var2=new ArrayList<>();
            if (map1.containsValue(largest)){
                 var2=map2.get(largest);
            }
           return var2;
        }

}