package tscaper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class TScaperTest {
    
    @Test
    public void testCountWordForNullInputArgs(){
        String [] words = {""}  ;
        assertNull(TScaper.countNumberOfWordsIfNeed(null, false, null));
        assertNull(TScaper.countNumberOfWordsIfNeed(words, false, null));
        assertNull(TScaper.countNumberOfWordsIfNeed(words, true, null));
        assertNotNull(TScaper.countNumberOfWordsIfNeed(words, true, Collections.EMPTY_LIST));
    }
 @Test
    public void testSTDelimeter(){
        StringTokenizer st = new StringTokenizer(getTestBigTextFromDoc()) ;
//        while(st.hasMoreTokens()){
//            System.out.print('>'+ st.nextToken(TScaper.stringTokinaizerDelimeter).toLowerCase()+" ") ;
//        }
    }

    public String getTestBigTextFromDoc(){
          String text = "(Content:) Simple HTML generated on the fly (as string), embedded CSS 2.1, no JS.\n"
            + "\n"
            + "Short story: Flying Saucer is simplest to use and render is most correct, but you better have full control over content. Otherwise look for a native solution.\n"
            + "\n"
            + "Long story:\n"
            + "\n"
            + "CSSBox seems to be more active, however it seems to depends on some 3rd party libraries. For example the demo depends on nekohtml which use apache xerces which changed the way the default Java 1.7 sax parser works and broke my program, but when I force it to use java's built in xerces I get ClassCastException (InlineBox to BlockBox). Can't get it to work at the end. Plus still haven't found a way to replace the document in an existing  BrowserCanvas.\n"
            + "\n"
            + "Cobra is no longer maintained, have to manually fix an incompatibility issue to make it works in 1.7. Also need to grab mozilla Rhino (not using any JS) but that is all. After that it is fairly smooth, just need to ask Logger to hide paint messages. Render is correct and speed is fair - as long as the document is simple. When you start to use less common tags or more complicated layout, Cobra falls apart pretty quickly.\n"
            + "\n"
            + "Flying Saucer is last updated Feb 2011 as of writing, and although its css support is not perfect it is the best of the three. Setup is very easy (e.g. no need to setup document like cobo or domparser like cssbox) has few dependency - which also means no javascript. But Flying Saucer is very strict about what you feed it. The source must be a well-formed XML, for example style and script may have to be wrapped in CDATA and if you use html entities you must declare DTD (so no html5 doctype). However if you are embedding content that you can control then it may be your best 111 222 choice.";
    return text ;
    }
    
    
    
    @Test
    public void testCountNumberOfWordsIfNeedNull(){
        ArrayList<String> wordsToCount = new ArrayList<>() ;
         HashMap<String,Integer> result =  TScaper.countNumberOfWordsIfNeed("".split(""), false , wordsToCount);
         assertNull(result);
         
         result = TScaper.countNumberOfWordsIfNeed("".split(""), true , null);
         assertNull(result);
         
        result = TScaper.countNumberOfWordsIfNeed(null, true , null);
        assertNull(result);
    }
    
    
    @Ignore
    @Test
    public void testCountNumberOfWordsIfNeedSimple(){
        ArrayList<String> wordsToCount = new ArrayList<>() ;
        wordsToCount.add("one") ;
        wordsToCount.add("two") ;
        wordsToCount.add("three") ;
        wordsToCount.add("fex") ;
        
        HashMap<String,Integer> result =  TScaper.countNumberOfWordsIfNeed(" :three; (one) |three\\ [two- .three, *two1234567890 fex] fex[ @fex% #fex$ &fex^".split(" "), true, wordsToCount ) ;
//        assertEquals(result.get("one"), Integer.valueOf(1));
//        assertEquals(result.get("two"), Integer.valueOf(2));
//        assertEquals(result.get("three"), Integer.valueOf(3));
//        assertEquals(result.get("fex"), Integer.valueOf(5));
//        assertEquals(result.keySet().size(), 4);
        
    }
    
    @Test
    public void testCountCharNull(){
        String testString = " " ;
        assertNull(TScaper.countNumberOfCharactersIfNeed(testString.split(""),false)) ;
        
        //simple one char test
        testString = "a" ;
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString.split(""),true).size(), 1);
        assertTrue(TScaper.countNumberOfCharactersIfNeed(testString.split(""), true).containsKey('a'));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString.split(""),true).get('a') , Integer.valueOf(1));
        
        //simple double char test
        testString = "aa" ;
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString.split(""),true).size(), 1);
        assertTrue(TScaper.countNumberOfCharactersIfNeed(testString.split(""), true).containsKey('a'));
        assertFalse(TScaper.countNumberOfCharactersIfNeed(testString.split(""), true).containsKey('b'));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString.split(""),true).get('a') , Integer.valueOf(2));
        
        //simple double char test 
        testString = "aba" ;
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString.split(""),true).size(), 2);
        assertTrue(TScaper.countNumberOfCharactersIfNeed(testString.split(""), true).containsKey('a'));
        assertTrue(TScaper.countNumberOfCharactersIfNeed(testString.split(""), true).containsKey('b'));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString.split(""),true).get('a') , Integer.valueOf(2));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString.split(""),true).get('b') , Integer.valueOf(1));
        
        //use not leters
        testString = "1a@b#a$" ;
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString.split(""),true).size(), 2);
        assertTrue(TScaper.countNumberOfCharactersIfNeed(testString.split(""), true).containsKey('a'));
        assertTrue(TScaper.countNumberOfCharactersIfNeed(testString.split(""), true).containsKey('b'));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString.split(""),true).get('a') , Integer.valueOf(2));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString.split(""),true).get('b') , Integer.valueOf(1));
        
        //lower and upper case
        testString = "aBa" ;
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString.split(""),true).size(), 2);
        assertTrue(TScaper.countNumberOfCharactersIfNeed(testString.split(""), true).containsKey('a'));
        assertTrue(TScaper.countNumberOfCharactersIfNeed(testString.split(""), true).containsKey('b'));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString.split(""),true).get('a') , Integer.valueOf(2));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString.split(""),true).get('b') , Integer.valueOf(1));
        
        //multilangugage
        testString = "ф 一 Z" ;
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString.split(""),true).size(), 3);
        assertTrue(TScaper.countNumberOfCharactersIfNeed(testString.split(""), true).containsKey('ф'));
        assertTrue(TScaper.countNumberOfCharactersIfNeed(testString.split(""), true).containsKey('一'));
        assertTrue(TScaper.countNumberOfCharactersIfNeed(testString.split(""), true).containsKey('z'));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString.split(""),true).get('ф') , Integer.valueOf(1));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString.split(""),true).get('一') , Integer.valueOf(1));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString.split(""),true).get('z') , Integer.valueOf(1));
    }
}
