package com.examples.ezoo.model;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class FeedingSchedule {
	
	private long scheduleId = 0l;
	
	private String feedingTime = ""; // TODO use a date time object
	private String recurrence = "";
	private String food = "";
	private String notes = "";
	private Map<Long, String> animals = new TreeMap<Long, String>(); // (k, v) = (id, "name (genus, species)")
	
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
	
	public List<String> getAnimals() {
		return new ArrayList<String>(this.animals.values());
	}

	public void setAnimals(Map<Long, String> animals) {
		this.animals = animals;
	}
	
	public void addAnimal(Animal animal) {
		String name = animal.getName() + " (" + animal.getTaxGenus() + " " + animal.getTaxSpecies() + ")";
		this.animals.put(animal.getAnimalID(), name);
	}
	
	public void addAnimals(List<Animal> animals) {
		for (Animal animal: animals) {
			this.addAnimal(animal);
		}
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
