package assign.resources;

import org.jsoup.nodes.Element;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * assignment7 Created by Majisto on 5/7/2015.
 */
@Path("/")
public class MeetingResources
{
    public MeetingResources() {

    }

    @GET
    @Path("/helloworld")
    @Produces("text/html")
    public String helloWorld() {
        System.out.println("Inside helloworld");
        return "Hello world ";
    }
}
