package tscaper;

import java.util.List;
import junit.framework.Assert;
import org.junit.Test;

public class PropertiesHolderTest {
    
    PropertiesHolder holder ;
    String yaru = "http://ya.ru" ;
    
    @Test
    public void testVflag(){
        String [] args = {yaru,"-v"} ;
        holder = new PropertiesHolder(args);
        
        Assert.assertTrue(holder.isIsVerbosity());
    }
    
    @Test
    public void testWFlag(){
        String [] args = {yaru,"-w"} ;
        holder = new PropertiesHolder(args);
        
        Assert.assertTrue(holder.isShowWordsOccurrence());
    }
            
    @Test
    public void testCFlag(){
        String [] args = {yaru,"-c"} ;
        holder = new PropertiesHolder(args);
        
        Assert.assertTrue(holder.isNeedCountCharactersNumber());
    }
            
    @Test
    public void testEFlag(){
        String [] args = {yaru,"-e"} ;
        holder = new PropertiesHolder(args);
       
        Assert.assertTrue(holder.isNeedExtracrSentences());
    }
        
    @Test
    public void testMixFlags(){
        String [] args = {yaru,"-v","-w","-c","-e"} ;
        holder = new PropertiesHolder(args) ;
        
        Assert.assertTrue(holder.isIsVerbosity());
        Assert.assertTrue(holder.isShowWordsOccurrence());
        Assert.assertTrue(holder.isNeedCountCharactersNumber());
        Assert.assertTrue(holder.isNeedExtracrSentences());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullFlag(){
        String [] args = {""}  ;
        holder = new PropertiesHolder(args) ;
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgument(){
        String [] args = {"-glaf,-test,-dddd"}  ;
        holder = new PropertiesHolder(args) ;
        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testIEmptyArgument(){
        String [] args = {} ;
        holder = new PropertiesHolder(args);
    }
    
    @Test
    public void testWWWParam(){
        String [] args = {yaru} ;
        holder = new PropertiesHolder(args);
        
        Assert.assertEquals(holder.getUrls()[0],yaru) ;
    }
    
    @Test
        public void testSingleWord(){
        Assert.assertEquals( true ,PropertiesHolder.isWord("word"));
        Assert.assertEquals( true ,  PropertiesHolder.isWord("wweeeeeeeeeeeeeeeeeeeeeeeeeeryyyyyyyyyyyyyyyyyyyyyyyyyyyyyyloooooooooooooooooonnnngword"));
        Assert.assertEquals( true,  PropertiesHolder.isWord("aa"));
    
        Assert.assertEquals( false ,  PropertiesHolder.isWord("a"));
        Assert.assertEquals( false ,PropertiesHolder.isWord(yaru)) ;
        Assert.assertEquals( false ,PropertiesHolder.isWord("1")) ;
        Assert.assertEquals( false ,PropertiesHolder.isWord("1a")) ;
        Assert.assertEquals( false ,PropertiesHolder.isWord("a2a")) ;
        Assert.assertEquals( false , PropertiesHolder.isWord("CalmNottationWord"));
    }
    
    @Test
    public void testSimplyWord(){
        String [] args = {yaru, "news"} ;
        holder = new PropertiesHolder(args) ;
        List<String> words = holder.getWords() ;
        
        Assert.assertTrue(words.size() == 1);
        Assert.assertEquals("news", words.get(0));
    }
    
    @Test
    public void testDoubleWord() {
        String [] args = {yaru, "news,breakennews"} ;
        holder = new PropertiesHolder(args) ;
        List<String> words = holder.getWords() ;
        
        Assert.assertTrue(words.size() == 2);
        Assert.assertEquals("news", words.get(0));
        Assert.assertEquals("breakennews", words.get(1));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testWrongWords(){
        String [] args = {yaru, "n1e1w1s"} ;
        holder = new PropertiesHolder(args) ;
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDoubledWords(){
        String [] args = {yaru, "news,breakennews", "okGoogleLetsCatchException"} ;
        holder = new PropertiesHolder(args) ;
    }

    @Test(expected = IllegalArgumentException.class)
    public void test4Words(){
        String [] args = {yaru, "news,breakennews", "okGoogleLetsCatchException", "maybeNow" , "orNow"} ;
        holder = new PropertiesHolder(args) ;
    }
    
    @Test
    public void testTaskArgs(){
        String [] args = {yaru, "Greece,default", "-v", "-w" , "-c" , "-e"} ;
        holder = new PropertiesHolder(args) ;
        List<String> words = holder.getWords() ;
        
        Assert.assertTrue(words.size() == 2);
        Assert.assertEquals("Greece".toLowerCase(), words.get(0));
        Assert.assertEquals("default", words.get(1));
        Assert.assertEquals(yaru,holder.getUrls()[0]);
        Assert.assertTrue(holder.isIsVerbosity());
        Assert.assertTrue(holder.isShowWordsOccurrence());
        Assert.assertTrue(holder.isNeedCountCharactersNumber());
        Assert.assertTrue(holder.isNeedExtracrSentences());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testChangeSiteDataArgs(){
        String [] args = {"Greece,default" , yaru} ;
        holder = new PropertiesHolder(args) ;
    }
}
