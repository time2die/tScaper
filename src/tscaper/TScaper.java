
package tscaper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.swing.text.html.parser.ParserDelegator;

public class TScaper {
    private Date startTime = null ;
    
      private TScaper(Date startTime) {
        this.startTime = startTime ;
    }
    
    public static void main(String[] args) {
        Date startTime = new Date() ;
        
        PropertiesHolder propertiesHolder = new PropertiesHolder(args) ;
        
        TScaper scaper = new TScaper(startTime) ;
        scaper.work(propertiesHolder) ;
    }
    
    private void work(PropertiesHolder propertiesHolder) {
        List<String> pagesContent = download(propertiesHolder.getUrls()) ;
        processPages(pagesContent,propertiesHolder) ;
        
        outputVerboseIfNeed(propertiesHolder);
    }
    
    private List<String> download(String[] urls) {
        LinkedList<String> results = new LinkedList<>() ;
        
        for (String urlIter : urls) {
            try {
                ParserTextUtil parser = new ParserTextUtil();
                URLConnection conn = new URL(urlIter).openConnection();
                Reader reader = new InputStreamReader(conn.getInputStream());
                new ParserDelegator().parse(reader, parser, true);
                
                results.add(parser.getText()) ;
            } catch (IOException e) {
                System.err.println("url: "+ urlIter);
                e.printStackTrace();
            }
        }
        return results ;
    }
  
    private void processPages(List<String> pages, PropertiesHolder propertiesHolder) {
        Map<String,Map<String,Integer>> pageWordCountMap = new HashMap<> () ;
        Map<String,Map<Character,Integer>> pageCharCountMap = new HashMap<>() ;
        for(String page : pages){
            Map<String,Integer>  wordCount = countNumberOfWordsIfNeed(page,propertiesHolder.isNeedCountCharactersNumber(), propertiesHolder.getWords());
            Map<Character,Integer> charCount = countNumberOfCharactersIfNeed(page,propertiesHolder.isNeedCountCharactersNumber()) ;
            
            pageWordCountMap.put(page, wordCount) ;
            pageCharCountMap.put(page, charCount) ;
        }
        
        printWordInformationIfNeed(propertiesHolder, pageWordCountMap);
        printCharCountMapIfNeed(propertiesHolder,pageCharCountMap);
        
    }

    protected static  Map<String, Integer> countNumberOfWordsIfNeed(String page, boolean needCountCharactersNumber, List<String> words) {
        if(!needCountCharactersNumber || page == null || words == null ){
            return null;
        }
        
        HashMap<String,Integer> wordsMap = new HashMap<>();
        for(String iter : words ){
            wordsMap.put(iter, 0) ;
        }
        
        StringTokenizer st = new StringTokenizer(page);
        while (st.hasMoreTokens()) {
            incrementWordCountIfNeed(st.nextToken(stringTokinaizerDelimeter).toLowerCase(), wordsMap);
        }
        return wordsMap ;
    }
    
    protected static String stringTokinaizerDelimeter = ":;()-*[]!@#$%^& 1234567890.,\n\t\\|" ;
    
    private static void incrementWordCountIfNeed(String wordFromPage, HashMap<String, Integer> wordsMap) {
         if (wordsMap.containsKey(wordFromPage)) {
            wordsMap.put(wordFromPage, wordsMap.get(wordFromPage) + 1);
        }
    }
    
    //считает количество повторений определенных символов на странице.
    protected static Map<Character, Integer> countNumberOfCharactersIfNeed(String page, boolean needCountCharactersNumber) {
        if (!needCountCharactersNumber) {
            return null;
        }

        HashMap<Character, Integer> result = new HashMap<>();

        for (char charIter : page.toCharArray()) {
            if (!Character.isLetter(charIter)) {
                continue;
            }

            charIter = Character.toLowerCase(charIter);

            if (result.containsKey(charIter)) {
                result.put(charIter, result.get(charIter).intValue() + 1);
            } else {
                result.put(charIter, Integer.valueOf(1));
            }
        }
        return result;
    }

    private void setStartTime(Date startTime) {
        this.startTime = startTime ;
    }

    private void outputVerboseIfNeed(PropertiesHolder propertiesHolder) {
        if(!propertiesHolder.isIsVerbosity()){
            return  ;
        }
        
        Date now = new Date() ;
        System.out.println("work: "+ (now.getTime() - startTime.getTime())+ " ms");
    }

    private void printWordInformationIfNeed(PropertiesHolder propertiesHolder, Map<String, Map<String, Integer>> pageWordCountMap) {
        System.out.println("Word count information:");
        for(String page : pageWordCountMap.keySet()){
            Map<String,Integer> wordCounts = pageWordCountMap.get(page) ;
            System.out.println("page: "+  page);
            for(String word : wordCounts.keySet()){
                System.out.println("word: "+ word +"\t time(s): "+ wordCounts.get(word));
            }
        }
    }

    private void printCharCountMapIfNeed(PropertiesHolder propertiesHolder, Map<String, Map<Character, Integer>> pageCharCountMap) {

    }


}
