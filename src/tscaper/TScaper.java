
package tscaper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.Collections;
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
        Map<String,String> pagesContent = download(propertiesHolder.getUrls()) ;
        processPages(pagesContent,propertiesHolder) ;
        
        outputVerboseIfNeed(propertiesHolder);
    }
    
    private Map<String,String> download(String[] urls) {
        HashMap<String,String> results = new HashMap<>() ;
        
        for (String urlIter : urls) {
            try {
                ParserTextUtil parser = new ParserTextUtil();
                URLConnection conn = new URL(urlIter).openConnection();
                Reader reader = new InputStreamReader(conn.getInputStream());
                new ParserDelegator().parse(reader, parser, true);
                
                results.put(urlIter, parser.getText()) ;
            } catch (IOException e) {
                System.err.println("url: "+ urlIter);
                e.printStackTrace();
            }
        }
        return results ;
    }
  
    private void processPages(Map<String,String> pages, PropertiesHolder propertiesHolder) {
        for(String urlIter : pages.keySet()){
            String page = pages.get(urlIter) ;
            Map<String,Integer>  wordCount = countNumberOfWordsIfNeed(page,propertiesHolder.isNeedCountCharactersNumber(), propertiesHolder.getWords());
            Map<Character,Integer> charCount = countNumberOfCharactersIfNeed(page,propertiesHolder.isNeedCountCharactersNumber()) ;
            
            System.out.println("URL: "+ urlIter);
            printWordInformationIfNeed(propertiesHolder, wordCount);
            printCharCountMapIfNeed(propertiesHolder,page,charCount);
        }
        

        
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
        System.out.println("\n\nwork: "+ (now.getTime() - startTime.getTime())+ " ms");
    }

    private void printWordInformationIfNeed(PropertiesHolder propertiesHolder, Map<String, Integer> wordCount) {
            for(String word : wordCount.keySet()){
                System.out.println("word: "+ word +"\t time(s): "+ wordCount.get(word));
            }

    }

    private void printCharCountMapIfNeed(PropertiesHolder propertiesHolder, String page, Map<Character, Integer> charCount) {
        System.out.println("\npage lenght: "+ page.length());
        List<Character> sortedList =  new LinkedList<>(charCount.keySet()) ;
        Collections.sort(sortedList) ;
        
        int i = 0 ;
        for(Character chIter  : sortedList ){
            System.out.print(chIter+":"+ charCount.get(chIter)+"\t");        
            if(i++ == 10){
                i = 0 ;
                System.out.println("");
            }
        }
    }
}
