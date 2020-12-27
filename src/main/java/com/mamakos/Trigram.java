package com.mamakos;

import java.util.*;

public class Trigram {
    HashMap<String, List<String>> trigrams = new HashMap<>();

    public void add(String lines) {
        String[] wordList = lines.split("\\s+");
        for (int i = 0; i < wordList.length-2; i++) {
            insert(wordList[i], wordList[i+1], wordList[i+2]);
        }
    }
    private void insert(String s1, String s2, String s3){
        List<String> temp;
        if(trigrams.containsKey(s1 + " " + s2) ){
            temp = trigrams.get(s1 + " " + s2);
        }else{
            temp = new ArrayList<>();
        }
        temp.add(s3);
        trigrams.put(s1 + " " + s2, temp);
    }
    public void writeStory(){
        Queue<String> output = new LinkedList<>();

        String key = getStartingWordPair();
        String value = getRandomValue(key);
        String word1 = key.split(" ")[0];
        String word2 = key.split(" ")[1];
        output.offer(word1);
        output.offer(word2);
        output.offer(value);

        for (int i = 0; i < 100; i++) {
            key = word2 + " " + value;
            if(!trigrams.containsKey(key)){
                break;
            }
            value = getRandomValue(key);
            output.offer(value);
            try {
                word2 = key.split(" ")[1];
            }catch (ArrayIndexOutOfBoundsException e){
                System.err.println(word2);
                break;
            }
        }
        System.out.print("...");
        for (String s : output) {
            System.out.print(s + " ");
        }
        System.out.print("...");
    }
    private String getRandomValue(String key){
        List<String> values = trigrams.get(key);
        Random rand = new Random();
        return values.get(rand.nextInt(values.size()));
    }

    private String getStartingWordPair(){
        Set<String> keys = trigrams.keySet();
        Random rand = new Random();
        int target = rand.nextInt(keys.size());
        int i = 0;
        for (Object key : keys) {
            if(i == target){
                return key.toString();
            }
            i++;
        }
        return null;
    }

}
