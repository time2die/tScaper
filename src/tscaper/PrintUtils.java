package tscaper;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author a.karpov
 */
public class PrintUtils {

    static public void printWordInformationIfNeed(PropertiesHolder propertiesHolder, Map<String, Integer> wordCount) {
        if (!propertiesHolder.isShowWordsOccurrence() || wordCount == null) {
            return;
        }
        printWordMap(wordCount);
    }

    static private void printWordMap(Map<String, Integer> wordCount) {
        for (String word : wordCount.keySet()) {
            System.out.println("word: " + word + "\t time(s): " + wordCount.get(word));
        }
    }

    static public void printCharCountMapIfNeed(PropertiesHolder propertiesHolder, String page, Map<Character, Integer> charCount) {
        if (!propertiesHolder.isNeedCountCharactersNumber() || charCount == null) {
            return;
        }

        printCharMap(charCount);
        System.out.println("\ntotal page lenght: " + page.length());
    }

    static private void printCharMap(Map<Character, Integer> charCount) {
        List<Character> sortedList = new LinkedList<>(charCount.keySet());
        Collections.sort(sortedList);
        System.out.println("");
        int i = 0;
        for (Character chIter : sortedList) {
            System.out.print(chIter + ":" + charCount.get(chIter) + "\t");
            if (i++ == 10) {
                i = 0;
                System.out.println("");
            }
        }
    }

    static public void printTotalResultIfNeed(PropertiesHolder propertiesHolder, Map<String, Integer> totalWordCount, Map<Character, Integer> totalChar) {
        System.out.println("Total result:");
        if (propertiesHolder.isShowWordsOccurrence()) {
            printWordMap(totalWordCount);
        }

        if (propertiesHolder.isNeedCountCharactersNumber()) {
            printCharMap(totalChar);
        }
    }
}
