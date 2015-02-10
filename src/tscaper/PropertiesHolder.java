
package tscaper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
        
        for( int i = 1 ; i < args.length  ; i++){
            String iter = args[i] ;
            if(iter.startsWith("-")){
                fillFlags(iter);
            }else{
                if(words == null){
                    parseWords(iter) ;
                }else{
                    printUsageHelpAndThrowE(); 
                }
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
    
    private void parseWords(String param) {
        if(param.indexOf(',') != -1){
            StringTokenizer st = new StringTokenizer(param, ",") ;
            while(st.hasMoreElements()){
                processWord(st.nextToken().toLowerCase());
            }
        }else{
            processWord(param);
        }
    }

    
    private void addWords(String iter) {
        if(words == null){
            words = new ArrayList<>() ;
        }
        words.add(iter) ;
    }
    
    private void processWord(String param) {
         if(isWord(param)){
                    addWords(param) ;
                }else{
                    printUsageHelpAndThrowE(); 
                }
    }
    /**
     *  Is word alphabetic ?
     * @param word 
     * @return iter.matches("[a-z]+") && iter.length() > 1
     */
    protected static boolean isWord(String iter) {
        return iter.matches("[a-z]+") && iter.length() > 1;
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
