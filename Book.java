// Sumay Gupta - implemented Book.java 

//A few assumptions.......

//Words will be separated by spaces. 
//There can be punctuation in a word, we will only add/keep punctuation at the end of a string if it is at the end of a string.
//    for examples: Hello.==> Ellohay.    Good-bye! ==> Ood-byegay!    so... ==> osay...

import java.util.Scanner;

public class Book {

    public String pigLatin(String word) {
        String pigLatin = "";
        int vowelIndex = 0;
        int endIndex = word.length();
        int endP = endPunctuation(word);
        boolean upperCaseStart = false;

        // System.out.println("Word : " + word + " ,has length: " + word.length());
        if (word.length() == 0)
            return pigLatin;

        char c = word.charAt(0);
        if (Character.isUpperCase(c)) {
            upperCaseStart = true;
        }
        for (int i = 0; i < word.length(); i++) {
            if (isVowel(word.charAt(i))) {
                vowelIndex = i;
                break;
            }
        }
        // If there are punctuations at the end, then adjust the endIndex accordingly.
        if (endP != -1)
            endIndex = endP;

        if (vowelIndex != 0) {
            pigLatin = pigLatin + word.substring(vowelIndex, endIndex) + word.substring(0, vowelIndex) + "ay";
        } else {
            pigLatin = pigLatin + word.substring(0, endIndex) + word.substring(0, vowelIndex) + "way";
        }
        //add the punctuation
        pigLatin = pigLatin + word.substring(endIndex, word.length());

        if (upperCaseStart) {
            pigLatin = Character.toUpperCase(pigLatin.charAt(0)) + pigLatin.substring(1, pigLatin.length());
        }
        return pigLatin;
    }

    public boolean isVowel(char c) {
        if ((c == 'a') || (c == 'A') ||
                (c == 'e') || (c == 'E') ||
                (c == 'i') || (c == 'I') ||
                (c == 'o') || (c == 'O') ||
                (c == 'u') || (c == 'U')) {
            return true;
        } else {
            return false;
        }
    }

    public int endPunctuation(String word) // return the index of where the punctuation is at the end of a String. If it
                                           // is all punctuation return 0, if there is no punctuation return -1
    {
        try {
            if ((word.length() != 0) && !Character.isAlphabetic(word.charAt(word.length() - 1))) {
                for (int i = word.length() - 1; i >= 0; i--) {
                    if (Character.isAlphabetic(word.charAt(i))) {
                        return i + 1;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: Word - " + word + " ; Has length - " + word.length());
            e.printStackTrace();
        }
        return -1;
    }

    public String translateWord(String word) // to share with class
    {
        String convertedWord = "";
        convertedWord = pigLatin(word);
        return convertedWord;
    }

    public int getWordCount(String str) {
        int count = 0;
        Scanner sc = new Scanner(str);
        while (sc.hasNext()) {
            sc.next();
            count++;
        }
        sc.close();
        return count;
    }

    public String translateSentence(String sentence) {
        String retSentence = "";
        /* Tokenize each word and translate it */
        String word = "";
        Scanner sc = new Scanner(sentence);
        sc.useDelimiter(" ");
        // System.out.println("Sentence : " + sentence + " ,has length: " +
        // sentence.length());
        while (sc.hasNext()) {
            word = sc.next();
            retSentence = retSentence + translateWord(word) + " ";
        }
        retSentence = retSentence + "\n";
        sc.close();
        return retSentence;
    }
}