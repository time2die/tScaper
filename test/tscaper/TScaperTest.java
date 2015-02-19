package tscaper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class TScaperTest {
    
    @Test
    public void testCountWordForNullInputArgs(){
        List<String> words = new LinkedList<>() ;
        assertNull(TScaper.countNumberOfWordsIfNeed(null, false, null));
        assertNull(TScaper.countNumberOfWordsIfNeed(words, false, null));
        assertNull(TScaper.countNumberOfWordsIfNeed(words, true, null));
        assertNull(TScaper.countNumberOfWordsIfNeed(words, true, Collections.EMPTY_LIST));
    }

    @Test
    public void testCountNumberOfWordsIfNeedNull(){
        List<String> words = new LinkedList<>() ;
        ArrayList<String> wordsToCount = new ArrayList<>() ;
         Map<String,Integer> result =  TScaper.countNumberOfWordsIfNeed(words, false , wordsToCount);
         assertNull(result);
         
         result = TScaper.countNumberOfWordsIfNeed(words, true , null);
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
        List<String> words = Arrays.asList(" :three; (one) |three\\ [two- .three, *two1234567890 fex] fex[ @fex% #fex$ &fex^".split("")) ;
        Map<String,Integer> result =  TScaper.countNumberOfWordsIfNeed(words, true, wordsToCount ) ;
        assertEquals(result.get("one"), Integer.valueOf(1));
        assertEquals(result.get("two"), Integer.valueOf(2));
        assertEquals(result.get("three"), Integer.valueOf(3));
        assertEquals(result.get("fex"), Integer.valueOf(5));
        assertEquals(result.keySet().size(), 4);
    }
    
    @Test
    public void testCountCharNull(){
        List<String> words = Arrays.asList(" ".split("")) ;
        assertNull(TScaper.countNumberOfCharactersIfNeed(words,false)) ;
        
        //simple one char test
        words = Arrays.asList("a".split("")) ;
        assertEquals(TScaper.countNumberOfCharactersIfNeed(words,true).size(), 1);
        assertTrue(TScaper.countNumberOfCharactersIfNeed(words, true).containsKey('a'));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(words,true).get('a') , Integer.valueOf(1));
        
        //simple double char test
        words = Arrays.asList("aa".split("")) ;
        assertEquals(TScaper.countNumberOfCharactersIfNeed(words,true).size(), 1);
        assertTrue(TScaper.countNumberOfCharactersIfNeed(words, true).containsKey('a'));
        assertFalse(TScaper.countNumberOfCharactersIfNeed(words, true).containsKey('b'));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(words,true).get('a') , Integer.valueOf(2));
        
        //simple double char test 
        words = Arrays.asList("aba".split("")) ;
        assertEquals(TScaper.countNumberOfCharactersIfNeed(words,true).size(), 2);
        assertTrue(TScaper.countNumberOfCharactersIfNeed(words, true).containsKey('a'));
        assertTrue(TScaper.countNumberOfCharactersIfNeed(words, true).containsKey('b'));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(words,true).get('a') , Integer.valueOf(2));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(words,true).get('b') , Integer.valueOf(1));
        
        //use not leters
        words = Arrays.asList("1a@b#a$".split(""));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(words, true).size(), 2);
        assertTrue(TScaper.countNumberOfCharactersIfNeed(words, true).containsKey('a'));
        assertTrue(TScaper.countNumberOfCharactersIfNeed(words, true).containsKey('b'));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(words, true).get('a'), Integer.valueOf(2));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(words, true).get('b'), Integer.valueOf(1));
        
        //lower and upper case
        words = Arrays.asList("aBa".split(""));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(words,true).size(), 2);
        assertTrue(TScaper.countNumberOfCharactersIfNeed(words, true).containsKey('a'));
        assertTrue(TScaper.countNumberOfCharactersIfNeed(words, true).containsKey('b'));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(words,true).get('a') , Integer.valueOf(2));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(words,true).get('b') , Integer.valueOf(1));
        
        //multilangugage
        
        words = Arrays.asList("ф 一 Z".split(""));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(words,true).size(), 3);
        assertTrue(TScaper.countNumberOfCharactersIfNeed(words, true).containsKey('ф'));
        assertTrue(TScaper.countNumberOfCharactersIfNeed(words, true).containsKey('一'));
        assertTrue(TScaper.countNumberOfCharactersIfNeed(words, true).containsKey('z'));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(words,true).get('ф') , Integer.valueOf(1));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(words,true).get('一') , Integer.valueOf(1));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(words,true).get('z') , Integer.valueOf(1));
    }
    
    @Test
    public void testMain(){
        TScaper.main("http://ya.ru long,stroy -v -w -c -e".split(" "));
    }

}
