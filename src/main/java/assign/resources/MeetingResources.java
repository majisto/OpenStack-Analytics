package assign.resources;

import assign.domain.MeetingList;
import assign.domain.Meetings;
import assign.etl.DBLoader;
import org.jsoup.nodes.Element;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * assignment7 Created by Majisto on 5/7/2015.
 */
@Path("/")
public class MeetingResources
{
    private DBLoader loader;

    public MeetingResources() {
        loader = new DBLoader(true);
    }

    @GET
    @Path("/getMeetings")
    @Produces("application/xml")
    public StreamingOutput getMeetings () throws Exception {
        List<Meetings> meetings = loader.getMeetings();
        MeetingList m = new MeetingList();
        m.setMeetingsList(meetings);
        return outputStream -> outputMeetings(outputStream, m);
    }

    @GET
    @Path("/helloworld")
    @Produces("text/html")
    public String helloWorld() {
        System.out.println("Inside helloworld");
        return "Hello world ";
    }

    protected void outputMeetings(OutputStream os, MeetingList meetings) throws IOException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(MeetingList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(meetings, os);
        } catch (JAXBException jaxb) {
            jaxb.printStackTrace();
            throw new WebApplicationException();
        }
    }
}
