package assign.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * assignment6 Created by Majisto on 4/27/2015.
 */

@XmlRootElement(name = "meetings")
@XmlAccessorType(XmlAccessType.FIELD)
public class MeetingList
{
    @XmlElement(name = "meeting")
    private List<Meetings> meetingsList;

    public List<Meetings> getMeetingsList() {
        return meetingsList;
    }

    public void setMeetingsList(List<Meetings> meetingsList) {
        this.meetingsList = meetingsList;
    }

    public MeetingList() {
    }
}
