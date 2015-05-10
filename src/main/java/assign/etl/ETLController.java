package assign.etl;

import assign.domain.Meetings;

import java.util.List;
import java.util.Map;

/**
 * assignment6 Created by Majisto on 4/23/2015.
 */
public class ETLController
{
    DBLoader loader;
    Reader reader;
    Transformer optimus_prime;
    String host = "http://eavesdrop.openstack.org/meetings/";

    public ETLController() {
        loader = new DBLoader(false);
        optimus_prime = new Transformer();
    }

    public static void main(String[] args) {
        ETLController etlController = new ETLController();
        if (args == null || args.length == 0){
            etlController.performETLActions("");
        }
        else
            for (String arg : args) etlController.performETLActions(arg);
    }

    private void performETLActions(String arg) {
        try {
            String source = host + arg;
            reader = new Reader(source);

            Map<String,List<String>> data = reader.getMeetings();
            List<Meetings> meetingList = optimus_prime.makeMeetings(data);
            System.out.println(meetingList);
            loader.addMeetings(meetingList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DBLoader getLoader() {
        return loader;
    }
}
