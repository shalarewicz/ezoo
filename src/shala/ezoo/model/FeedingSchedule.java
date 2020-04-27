package shala.ezoo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Arrays;

@Entity
@Table(name="FEEDING_SCHEDULES")
public class FeedingSchedule {
	
	@Id
	@NotNull
	private long scheduleId = 0l;
	
	@Column(name="feeding_time")
	@NotNull @Size(min = 0, max = 20, message ="{feedingSchedule.size}")
	private String feedingTime = "";
	
	@Column
	@NotNull @Pattern(regexp = "Every 4 Hours|Every 6 Hours|Every 8 Hours|Every 12 Hours|Daily|Weekly|Monthly", message="{feedingSchedule.recurrence.pattern}")
	@Size(min = 0, max = 80, message ="{feedingSchedule.size}")
	private String recurrence = "";
	
	@Column
	@NotNull @Size(min = 0, max = 80, message ="{feedingSchedule.size}")
	private String food = "";
	
	@Column 
	@Size(min = 0, max = 250, message ="{feedingSchedule.size}")
	private String notes = "";
	
	@OneToMany(targetEntity=Animal.class, mappedBy="feedingSchedule", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Animal> animals = new ArrayList<Animal>();
	
    public static final List<String> RECURRENCES = Arrays.asList(
			"Every 4 Hours", "Every 6 Hours", "Every 8 Hours", "Every 12 Hours", "Daily", "Weekly", "Monthly");
	
	public FeedingSchedule() {}
	
	public FeedingSchedule(long scheduleId, String time, String recurrence, String food, String notes) {
		super();
		this.scheduleId = scheduleId;
		this.feedingTime = time;
		this.recurrence = recurrence;
		this.food = food;
		this.notes = notes;
	}
	
    public List<Animal> getAnimals() {
        return animals;
    }

    
    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }
    
    public void removeAnimal(Animal animal) {
        this.animals.remove(animal);
    }

    public void addAnimals(List<Animal> animals) {
        this.animals.addAll(animals);
	}

	public long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(long scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getFeedingTime() {
		return feedingTime;
	}
	public void setFeedingTime(String feedingTime) {
		this.feedingTime = feedingTime;
	}
	public String getRecurrence() {
		return recurrence;
	}
	public void setRecurrence(String recurrence) {
		this.recurrence = recurrence;
	}

	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "FeedingSchedule [scheduleId=" + scheduleId + ", feedingTime=" + feedingTime + ", recurrence="
				+ recurrence + ", food=" + food + ", Notes=" + notes + "]";
	}

	@Override 
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((animals == null) ? 0 : animals.hashCode());
        result = prime * result + ((feedingTime == null) ? 0 : feedingTime.hashCode());
        result = prime * result + ((food == null) ? 0 : food.hashCode());
        result = prime * result + ((notes == null) ? 0 : notes.hashCode());
        result = prime * result + ((recurrence == null) ? 0 : recurrence.hashCode());
        result = prime * result + (int) (scheduleId ^ (scheduleId >>> 32));
        return result;
    }

	@Override 
	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FeedingSchedule other = (FeedingSchedule) obj;
        if (feedingTime == null) {
            if (other.feedingTime != null)
                return false;
        } else if (!feedingTime.equals(other.feedingTime))
            return false;
        if (food == null) {
            if (other.food != null)
                return false;
        } else if (!food.equals(other.food))
            return false;
        if (notes == null) {
            if (other.notes != null)
                return false;
        } else if (!notes.equals(other.notes))
            return false;
        if (recurrence == null) {
            if (other.recurrence != null)
                return false;
        } else if (!recurrence.equals(other.recurrence))
            return false;
        if (scheduleId != other.scheduleId)
            return false;
        return true;
    }
	
	
}
