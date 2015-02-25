package tscaper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author a.karpov
 */
public class PropertiesHolder {

    private String[] urls = null;
    private List<String> words = null;

    private boolean verbosity = false;
    private boolean showWordsOccurrence = false;
    private boolean needCountCharactersNumber = false;

    public PropertiesHolder(String[] args) {
        if (args.length < 1) {
            printUsageHelpAndThrowE();
        }

        setUrl(args[0]);

        for (int i = 1; i < args.length; i++) {
            String iter = args[i];
            if (iter.startsWith("-")) {
                fillFlags(iter);
            } else {
                if (words == null) {
                    parseWords(iter);
                } else {
                    printUsageHelpAndThrowE();
                }
            }
        }
    }

    private void setUrl(String arg) {
        if (isUrl(arg)) {
            urls = new String[1];
            urls[0] = arg;
        } else {
            try {
                List<String> t = readUrlsFromFile(arg);
                urls = t.toArray(new String[t.size() > 1 ? t.size() - 1 : 1]);
            } catch (IOException ex1) {
                printUsageHelpAndThrowE();
            }
        }
    }

    private boolean isUrl(String url) {
        try {
            URL testURl = new URL(url);
            return true;
        } catch (MalformedURLException ex) {
            return false;
        }
    }

    public List<String> readUrlsFromFile(String filePath) throws FileNotFoundException, IOException {
        List<String> urls = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String sCurrentLine = null;
            urls = new LinkedList<>();

            while ((sCurrentLine = br.readLine()) != null) {
                if (isUrl(sCurrentLine)) {
                    urls.add(sCurrentLine);
                }
            }

            return urls;
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
            default:
                printUsageHelpAndThrowE();
        }
    }

    private void parseWords(String param) {
        if (param.indexOf(',') != -1) {
            StringTokenizer st = new StringTokenizer(param, ",");
            while (st.hasMoreElements()) {
                processWord(st.nextToken().toLowerCase());
            }
        } else {
            processWord(param);
        }
    }

    private void addWords(String iter) {
        if (words == null) {
            words = new ArrayList<>();
        }
        words.add(iter);
    }

    private void processWord(String param) {
        if (isWord(param)) {
            addWords(param);
        } else {
            printUsageHelpAndThrowE();
        }
    }

    /**
     * Is word alphabetic ?
     *
     * @param word
     * @return iter.matches("[a-z]+") && iter.length() > 1
     */
    protected static boolean isWord(String iter) {
        for (char charIter : iter.toCharArray()) {
            if (!Character.isLetter(charIter)) {
                return false;
            }
        }
        return true;
    }

    private void printUsageHelpAndThrowE() {
        System.out.println("How to use rScaper ?");
        System.out.println("java –jar tScraper.jar xxxx yyy,yyy –v –w –c –e");
        System.out.println("where:");
        System.out.println("xxxx - web site");
        System.out.println("yyy,yyy - word or list of words");
        System.out.println("-v for verbosity");
        System.out.println("-w count number of provided word");
        System.out.println("-c count number of character of each web page");
        System.out.println("-e extract sentences’ which contain given words");
        System.out.flush();

        throw new IllegalArgumentException("");
    }

    public String[] getUrls() {
        return urls;
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

}
