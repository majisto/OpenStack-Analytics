package assign.etl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * assignment6 Created by Majisto on 4/26/2015.
 */
public class Reader
{
    private final HashSet<String> badWords;
    String host;
    Logger logger;

    public Reader(String host) {
        this.host = host;
        logger = Logger.getLogger("Reader");
        badWords = new HashSet<>();
        badWords.add("Name"); badWords.add("Last modified"); badWords.add("Size");badWords.add("Description");badWords.add("Parent Directory");
    }

    public Map<String, List<String>> getMeetings() {
        ArrayList<String> allMeetingList = new ArrayList<>();
        try {
            Elements links = getConnection();

            for (Element e : links) {
                String s = e.html();
//                System.out.println(s);
                if (!badWords.contains(s))
                    allMeetingList.add(s);
            }
            if (!allMeetingList.isEmpty()) return getData(allMeetingList);
            else return null;
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return null;
    }

    private Map<String, List<String>> getData (List<String> urlData) throws Exception {
        Map<String, List<String>> mainData = new LinkedHashMap<>();
        ArrayList<String> logs = new ArrayList<>();
        HashSet<String> meet = new HashSet<>();
        for (String s: urlData){
            Elements links = getConnectionHost(s);
            ArrayList<String> years = new ArrayList<>(5);
            for (Element e: links){
                String html = e.html();
                if (!badWords.contains(html)) years.add(html);
                if (html.endsWith(".log.html")) logs.add(html);
            } //Got years now. Iterate through years.
            for (String year: years){
                Elements yearlinks = getConnectionYear(s, year);
                for (Element e: yearlinks){
                    String html = e.html();
                    if (!badWords.contains(html)) {
                        hasMeetingName(meet, html);
                    }
                }
            }
            logs = new ArrayList<>(meet);
            mainData.put(s, logs);
            meet = new HashSet<>();
        }
        return mainData;
    }

    public boolean checkYear (String a) {
        String pattern = "\\d{0,4}[/]";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(a);
        return m.matches();
    }

    private Elements getConnectionYear (String meeting, String year){
        Elements links = null;
        try{
            Document doc2 = Jsoup.connect(host + meeting + "/" + year).get();
            links = doc2.select("body a");
        } catch (IOException e) {
            System.err.println("Something went wrong calling Jsoup.");
            e.printStackTrace();
        }
        return links;
    }

    private Elements getConnectionHost (String meeting){
        Elements links = null;
        try{
            Document doc2 = Jsoup.connect(host + meeting).get();
            links = doc2.select("body a");
        } catch (IOException e) {
            System.err.println("Something went wrong calling Jsoup.");
            e.printStackTrace();
        }
        return links;
    }

    private Elements getConnection (){
        Elements links = null;
        try{
            Document doc2 = Jsoup.connect(host).get();
            links = doc2.select("body a");
        } catch (IOException e) {
            System.err.println("Something went wrong calling Jsoup.");
            e.printStackTrace();
        }
        return links;
    }

    //Added.
    public void hasMeetingName(HashSet<String> projectHash, String name) throws Exception {
        String mainName = name.replace(".log.html", "");
        mainName = mainName.replace(".log.txt", "");
        mainName = mainName.replace(".txt", "");
        mainName = mainName.replace(".html", "");

        if (!projectHash.contains(mainName)) projectHash.add(mainName);
    }
}
