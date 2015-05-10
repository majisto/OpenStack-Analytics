package assign.etl;

import assign.domain.Meetings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * assignment6 Created by Majisto on 4/26/2015.
 */
public class Transformer
{
    Logger logger;

    public Transformer() {
        logger = Logger.getLogger("Transformer");
    }

    public List<Meetings> setupMeetings(Map<String, List<String>> data, String meetingName){
        List<Meetings> meetingList = new ArrayList<>();
        for (String url: data.keySet()){
            List<String> a = data.get(url);
            for (String log: a) meetingList.add(createMeeting(url, log, meetingName));
        }
        return meetingList;
    }

    public Meetings createMeeting (String url, String log, String meetingName){
        String link = url + log;
        String year = url.substring(url.length() - 5, url.length() - 1);
//        return new Meetings(meetingName, year, log, link);
        return null;
    }

    public List<Meetings> makeMeetings(Map<String, List<String>> data){
        List<Meetings> meetingList = new ArrayList<>();
        for (String url: data.keySet()){
            meetingList.add(new Meetings(url, data.get(url).size()));
        }
        return meetingList;
    }
}
