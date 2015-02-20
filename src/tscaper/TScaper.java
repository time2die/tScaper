
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
    
    public static void main(String[] args) {
        Date startTime = new Date() ;
        PropertiesHolder propertiesHolder = new PropertiesHolder(args) ;
        
        TScaper scaper = new TScaper(startTime) ;
        scaper.work(propertiesHolder) ;
    }

    private TScaper(Date startTime) {
        this.startTime = startTime ;
    }
    
    private void work(PropertiesHolder propertiesHolder) {
        List<String> pages = download(propertiesHolder.getUrls()) ;
        processPages(pages,propertiesHolder) ;
        
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
    
    public static String savePage(final String URL) throws IOException {
    String line = "" ;
    StringBuilder all = new StringBuilder();
    URL myUrl = null;
    BufferedReader in = null;
    try {
        myUrl = new URL(URL);
        in = new BufferedReader(new InputStreamReader(myUrl.openStream()));

        while ((line = in.readLine()) != null) {
            all.append(line);
        }
    } finally {
        if (in != null) {
            in.close();
        }
    }

    return all.toString();
}

    private void processPages(List<String> pages, PropertiesHolder propertiesHolder) {
        Map<String,Integer>  wordCount = countNumberOfWordsIfNeed(pages,propertiesHolder.isNeedCountCharactersNumber(), propertiesHolder.getWords());
        Map<Character,Integer> charCount = countNumberOfCharactersIfNeed(pages,propertiesHolder.isNeedCountCharactersNumber()) ;
//        extractSentencesIfNeed(pages,propertiesHolder.isNeedExtracrSentences()) ;
    }

    protected static  Map<String, Integer> countNumberOfWordsIfNeed(List<String>  pages, boolean needCountCharactersNumber, List<String> words) {
        if(!needCountCharactersNumber || pages == null || words == null || pages.isEmpty()){
            return null;
        }
        
        HashMap<String,Integer> wordsMap = new HashMap<>();
        for(String iter : words ){
            wordsMap.put(iter, 0) ;
        }
        
        for(String page : pages){
            StringTokenizer st = new StringTokenizer(page) ;
            while(st.hasMoreTokens()){
                incrementWordCountIfNeed(st.nextToken(stringTokinaizerDelimeter).toLowerCase(), wordsMap);
            }
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
    protected static Map<Character,Integer> countNumberOfCharactersIfNeed(List<String> pages, boolean needCountCharactersNumber) {
        if(!needCountCharactersNumber || pages == null || pages.isEmpty() ){
            return null;
        }
        
        HashMap<Character,Integer> result = new HashMap<>() ;
        
        for(String stringIter : pages){
            for(char charIter : stringIter.toCharArray()){
                if(!Character.isLetter(charIter)){
                    continue; 
                }
                
                charIter = Character.toLowerCase(charIter) ;
                
                if(result.containsKey(charIter)){
                    result.put(charIter, result.get(charIter).intValue() +1) ;
                }else{
                    result.put(charIter, Integer.valueOf(1)) ;
                }
            }
        }

        return result ;
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


}
