
package tscaper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @author a.karpov
 */

public class PropertiesHolder {

    private String url = null ; 
    private List<String> words = null ;
    
    private boolean verbosity = false  ;
    private boolean showWordsOccurrence = false  ;
    private boolean needCountCharactersNumber = false ;
    private boolean needExtracrSentences = false ;
    
    public  PropertiesHolder(String[] args) {
        if(args.length < 1 ){
            printUsageHelpAndThrowE(); 
        }

        setUrl(args[0]) ;
        //add parsing words
        
        for(String iter : args){
            if(iter.startsWith("-")){
                fillFlags(iter);
            }
        }
    }
    
    private void setUrl(String arg) {
        try {
            URL siteURL = new URL(arg) ;
            url = arg ;
        } catch (MalformedURLException ex) {
            printUsageHelpAndThrowE(); 
        }
    }
    
    private void fillFlags(String iter) {
        switch (iter) {
            case "-v":
                verbosity = true;
                break;
            case "-w":
                showWordsOccurrence = true;
                break;
            case "-c":
                needCountCharactersNumber = true;
                break;
            case "-e":
                needExtracrSentences = true;
                break;
            default:
                printUsageHelpAndThrowE();

        }
    }
    
    private void printUsageHelpAndThrowE(){
        throw new IllegalArgumentException();
    }
    
    
      public String getUrl() {
        return url;
    }

    public List<String> getWords() {
        return words;
    }

    public boolean isIsVerbosity() {
        return verbosity;
    }

    public boolean isShowWordsOccurrence() {
        return showWordsOccurrence;
    }

    public boolean isNeedCountCharactersNumber() {
        return needCountCharactersNumber;
    }

    public boolean isNeedExtracrSentences() {
        return needExtracrSentences;
    }



}
