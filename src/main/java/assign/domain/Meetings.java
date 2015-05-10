package assign.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;

/**
 * assignment7 Created by Majisto on 5/9/2015.
 */

@Entity
@Table(name = "meetings")
@XmlRootElement(name = "meetings")
@XmlAccessorType(XmlAccessType.FIELD)
public class Meetings
{
    @XmlTransient
    private Long id;
    @XmlElement
    private String Project;
    @XmlElement
    private int count;

    public Meetings() {
    }

    public Meetings(String project, int count) {
        Project = project;
        this.count = count;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProject() {
        return Project;
    }

    public void setProject(String project) {
        Project = project;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
