package tscaper;

import java.util.Collections;
import java.util.StringTokenizer;
import org.junit.Test;
import static org.junit.Assert.*;

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
        StringTokenizer st = new StringTokenizer(text) ;
//        while(st.hasMoreTokens()){
//            System.out.print('>'+ st.nextToken(TScaper.stringTokinaizerDelimeter).toLowerCase()+" ") ;
//        }
    }    
}
