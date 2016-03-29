package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private ArrayList<String> wordList = new ArrayList<String>();
    private HashMap<String, ArrayList> letterstoWord = new HashMap<String, ArrayList>();
    private HashSet<String> wordSet = new HashSet<String>();

    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();

            if(letterstoWord.containsKey(alphabeticalOrder(word))){
                ArrayList<String> temp = letterstoWord.get(alphabeticalOrder(word));
                temp.add(word);
                letterstoWord.put(alphabeticalOrder(word), temp);
                wordList.add(word);
                wordSet.add(word);

            }
            else{
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(word);
                letterstoWord.put(alphabeticalOrder(word), temp);
                wordList.add(word);
                wordSet.add(word);
            }
        }
    }

    public boolean isGoodWord(String word, String base) {

        boolean result = true;

        if(!wordSet.contains(word))
            result = false;
        if(word.contains(base))
            result = false;

        return result;
    }

    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();

        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        for(int i = 0; i < 26; i++)
        {
            String newWord = alphabeticalOrder(word+alphabet[i]);

            if(letterstoWord.containsKey(newWord)){
                ArrayList temp = letterstoWord.get(newWord);
                result.addAll(temp);
            }

        }

        return result;
    }

    public String pickGoodStarterWord() {

        int rand = random.nextInt(wordList.size());
        int size = wordList.size();
        int wrapCounter = 0;

        while (rand <= size) {
            int num = letterstoWord.get(wordList.get(rand)).size();

            if (num >= MIN_NUM_ANAGRAMS)
                return wordList.get(rand);

            if (rand == size) {
                if (wrapCounter > 1)
                    return "error";
                else {
                    rand = 0;
                    wrapCounter++;
                }
            } else
                rand++;
        }
        return "Word Not Found";
    }

    private String alphabeticalOrder(String myWord){

        char[] chars = myWord.toCharArray();

        Arrays.sort(chars);

        return new String(chars);
    }
}
