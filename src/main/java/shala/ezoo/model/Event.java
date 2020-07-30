package shala.ezoo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name = "events")
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eventId;
    
    @Column
    @Size(max = 100, message = "{event.size}")
    private String name;
    
    @Column
    private LocalDateTime time;
    
    @Column
    private String location;
    
    @Column 
    private String description;
    
//    @ManyToMany(mappedBy = "events", fetch = FetchType.EAGER)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "event_attendees", joinColumns = @JoinColumn(name="eventId"), inverseJoinColumns = @JoinColumn(name = "username"))
    private List<User> attendees = new ArrayList<User>();
    
    public Event() {}
    
    public Event(String name, LocalDateTime time, String location, String description, List<User> attendees) {
        this.name = name;
        this.time = time;
        this.location = location;
        this.description = description;
        this.attendees = attendees;
    }

    public long getEventId() {
        return eventId;
    }  
    
    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public LocalDateTime getTime() {
        return time;
    }

    
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    
    public String getLocation() {
        return location;
    }

    
    public void setLocation(String location) {
        this.location = location;
    }
    
    
    public String getDescription() {
        return description;
    }

    
    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getAttendees() {
        return attendees;
    }

    
    public void setAttendees(List<User> attendees) {
        this.attendees = attendees;
    }
    
    public void addAttendee(User user) {
        this.attendees.add(user);
    }
    
    public void removeAttendee(User user) {
        this.attendees.remove(user);
    }

    
    @Override 
    public String toString() {
        return "Event [eventId=" + eventId + ", name=" + name + ", time=" + time + ", location=" + location + 
                ", description=" + description + ", attendees=" + attendees + "]";
    }

    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
//        result = prime * result + ((attendees == null) ? 0 : attendees.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + (int) (eventId ^ (eventId >>> 32));
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        /* Does not use attendees due to type being org.hibernate.collection.internal.PersistentBag. 
         * Hibernate documentation states:
         * Bag does not respect the collection API and does a JVM instance comparison to do the equals.
         * The semantic is broken not to have to initialize a collection for a simple equals() operation.
         * 
         * Fix with https://stackoverflow.com/questions/55621145/how-to-work-with-hibernates-persistentbag-not-obeying-list-equals-contract
     */

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Event other = (Event) obj;
        if (eventId != other.eventId)
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        return true;
    }


    
}
