package tscaper;

import javax.swing.text.html.*;

public class ParserTextUtil extends HTMLEditorKit.ParserCallback
{
    private StringBuffer text = new StringBuffer();
    
    @Override
    public void handleText(char[] data, int pos){
        text.append(new String(data)) ;
    }
    
    public String getText(){
        return text.toString() ;
    }
}