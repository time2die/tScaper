
package tscaper;

public class TScaper {
   
    private TScaper(PropertiesHolder propertiesHolder) {

    }

    public static void main(String[] args) {
        PropertiesHolder propertiesHolder = new PropertiesHolder(args) ;
        TScaper scaper = new TScaper(propertiesHolder) ;
    }

}
