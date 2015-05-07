package assign.resources;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * assignment7 Created by Majisto on 5/7/2015.
 */
@ApplicationPath("/assignment6")
public class MainApplication extends Application {

    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> classes = new HashSet<Class<?>>();

    public MainApplication() {
    }

    @Override
    public Set<Class<?>> getClasses() {
        classes.add(MeetingResources.class);
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
