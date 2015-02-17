
package tscaper;

import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class TScaper {
    
    public static void main(String[] args) {
        PropertiesHolder propertiesHolder = new PropertiesHolder(args) ;
        TScaper scaper = new TScaper(propertiesHolder) ;
		
    }
    
    static void countNumberOfCharactersIfNeed(String test, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    private TScaper(PropertiesHolder propertiesHolder) {
        String [] pages = download(propertiesHolder.getUrl()) ;
        processPages(pages,propertiesHolder) ;
    }

    
    private String[] download(String url) {
        String[] result = {"Content: Simple HTML generated on the fly (as string), embedded CSS 2.1, no JS.\n"
            + "\n"
            + "Short story: Flying Saucer is simplest to use and render is most correct, but you better have full control over content. Otherwise look for a native solution.\n"
            + "\n"
            + "Long story:\n"
            + "\n"
            + "CSSBox seems to be more active, however it seems to depends on some 3rd party libraries. For example the demo depends on nekohtml which use apache xerces which changed the way the default Java 1.7 sax parser works and broke my program, but when I force it to use java's built in xerces I get ClassCastException (InlineBox to BlockBox). Can't get it to work at the end. Plus still haven't found a way to replace the document in an existing  BrowserCanvas.\n"
            + "\n"
            + "Cobra is no longer maintained, have to manually fix an incompatibility issue to make it works in 1.7. Also need to grab mozilla Rhino (not using any JS) but that is all. After that it is fairly smooth, just need to ask Logger to hide paint messages. Render is correct and speed is fair - as long as the document is simple. When you start to use less common tags or more complicated layout, Cobra falls apart pretty quickly.\n"
            + "\n"
            + "Flying Saucer is last updated Feb 2011 as of writing, and although its css support is not perfect it is the best of the three. Setup is very easy (e.g. no need to setup document like cobo or domparser like cssbox) has few dependency - which also means no javascript. But Flying Saucer is very strict about what you feed it. The source must be a well-formed XML, for example style and script may have to be wrapped in CDATA and if you use html entities you must declare DTD (so no html5 doctype). However if you are embedding content that you can control then it may be your best choice."};
        return result;
    }

    private void processPages(String[] pages, PropertiesHolder propertiesHolder) {
        countNumberOfWordsIfNeed(pages,propertiesHolder.isNeedCountCharactersNumber(), propertiesHolder.getWords());
        countNumberOfCharactersIfNeed(pages,propertiesHolder.isNeedCountCharactersNumber()) ;
//        extractSentencesIfNeed(pages,propertiesHolder.isNeedExtracrSentences()) ;
    }

    protected static  HashMap<String, Integer> countNumberOfWordsIfNeed(String[] pages, boolean needCountCharactersNumber, List<String> words) {
        if(!needCountCharactersNumber || pages == null || words == null){
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
       
    }
    
    //считает количество повторений определенных символов на странице.
    protected static HashMap<Character,Integer> countNumberOfCharactersIfNeed(String[] pages, boolean needCountCharactersNumber) {
        if(!needCountCharactersNumber || pages == null ){
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
}
