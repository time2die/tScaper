package tscaper;


import junit.framework.Assert;
import org.junit.Test;
import tscaper.PropertiesHolder;


public class PropertiesHolderTest {
    
    PropertiesHolder holder ;
    
    @Test
    public void testVflag(){
        String [] args = {"http://ya.ru","-v"} ;
        holder = new PropertiesHolder(args);
        Assert.assertTrue(holder.isIsVerbosity());
    }
    
    @Test
    public void testWFlag(){
        String [] args = {"http://ya.ru","-w"} ;
        holder = new PropertiesHolder(args);
        Assert.assertTrue(holder.isShowWordsOccurrence());
    }
            
    @Test
    public void testCFlag(){
        String [] args = {"http://ya.ru","-c"} ;
        holder = new PropertiesHolder(args);
        Assert.assertTrue(holder.isNeedCountCharactersNumber());
    }
            
    @Test
    public void testEFlag(){
        String [] args = {"http://ya.ru","-e"} ;
        holder = new PropertiesHolder(args);
        Assert.assertTrue(holder.isNeedExtracrSentences());
    }
        
    @Test
    public void testMixFlags(){
        String [] args = {"http://ya.ru","-v","-w","-c","-e"} ;
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
        String [] args = {"http://ya.ru/"} ;
        holder = new PropertiesHolder(args);
        Assert.assertEquals(holder.getUrl(),"http://ya.ru/") ;
         
    }

}
