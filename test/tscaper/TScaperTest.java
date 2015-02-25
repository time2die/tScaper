package tscaper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class TScaperTest {

    @Test
    public void testCountWordForNullInputArgs() {
        String words = "";
        assertNull(TScaper.countNumberOfWordsIfNeed(null, false, null));
        assertNull(TScaper.countNumberOfWordsIfNeed(words, false, null));
        assertNull(TScaper.countNumberOfWordsIfNeed(words, true, null));
        assertNotNull(TScaper.countNumberOfWordsIfNeed(words, true, Collections.EMPTY_LIST));
    }

    @Test
    public void testCountNumberOfWordsIfNeedNull() {
        ArrayList<String> wordsToCount = new ArrayList<>();
        Map<String, Integer> result = TScaper.countNumberOfWordsIfNeed("", false, wordsToCount);
        assertNull(result);

        result = TScaper.countNumberOfWordsIfNeed("", true, null);
        assertNull(result);

        result = TScaper.countNumberOfWordsIfNeed(null, true, null);
        assertNull(result);
    }

    @Test
    public void testCountNumberOfWordsIfNeedSimple() {
        ArrayList<String> wordsToCount = new ArrayList<>();
        wordsToCount.add("one");
        wordsToCount.add("two");
        wordsToCount.add("three");
        wordsToCount.add("fex");

//        Map<String,Integer> result =  TScaper.countNumberOfWordsIfNeed(" :three; (one) |three\\ [two- .three, *two1234567890 fex] fex[ @fex% #fex$ &fex^", true, wordsToCount ) ;
        Map<String, Integer> result = TScaper.countNumberOfWordsIfNeed("three one three two three two fex fex fex fex fex", true, wordsToCount);
        assertEquals(result.get("one"), Integer.valueOf(1));
        assertEquals(result.get("two"), Integer.valueOf(2));
        assertEquals(result.get("three"), Integer.valueOf(3));
        assertEquals(result.get("fex"), Integer.valueOf(5));
        assertEquals(result.keySet().size(), 4);

    }

    @Test
    public void testCountCharNull() {
        String testString = " ";
        assertNull(TScaper.countNumberOfCharactersIfNeed(testString, false));

        //simple one char test
        testString = "a";
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString, true).size(), 1);
        assertTrue(TScaper.countNumberOfCharactersIfNeed(testString, true).containsKey('a'));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString, true).get('a'), Integer.valueOf(1));

        //simple double char test
        testString = "aa";
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString, true).size(), 1);
        assertTrue(TScaper.countNumberOfCharactersIfNeed(testString, true).containsKey('a'));
        assertFalse(TScaper.countNumberOfCharactersIfNeed(testString, true).containsKey('b'));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString, true).get('a'), Integer.valueOf(2));

        //simple double char test 
        testString = "aba";
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString, true).size(), 2);
        assertTrue(TScaper.countNumberOfCharactersIfNeed(testString, true).containsKey('a'));
        assertTrue(TScaper.countNumberOfCharactersIfNeed(testString, true).containsKey('b'));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString, true).get('a'), Integer.valueOf(2));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString, true).get('b'), Integer.valueOf(1));

        //use not leters
        testString = "1a@b#a$";
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString, true).size(), 2);
        assertTrue(TScaper.countNumberOfCharactersIfNeed(testString, true).containsKey('a'));
        assertTrue(TScaper.countNumberOfCharactersIfNeed(testString, true).containsKey('b'));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString, true).get('a'), Integer.valueOf(2));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString, true).get('b'), Integer.valueOf(1));

        //lower and upper case
        testString = "aBa";
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString, true).size(), 2);
        assertTrue(TScaper.countNumberOfCharactersIfNeed(testString, true).containsKey('a'));
        assertTrue(TScaper.countNumberOfCharactersIfNeed(testString, true).containsKey('b'));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString, true).get('a'), Integer.valueOf(2));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString, true).get('b'), Integer.valueOf(1));

        //multilangugage
        testString = "ф 一 Z";
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString, true).size(), 3);
        assertTrue(TScaper.countNumberOfCharactersIfNeed(testString, true).containsKey('ф'));
        assertTrue(TScaper.countNumberOfCharactersIfNeed(testString, true).containsKey('一'));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString, true).get('ф'), Integer.valueOf(1));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString, true).get('一'), Integer.valueOf(1));
        assertEquals(TScaper.countNumberOfCharactersIfNeed(testString, true).get('z'), Integer.valueOf(1));
    }

    
    @Test
    public void testMain() {
        TScaper.main("http://lenta.ru новости,погода -w -c -v ".split(" "));
    }

}
