package shala.ezoo.model;


public class AnimalSchedule {
    
    private Animal animal;
    
    private FeedingSchedule feedingSchedule;

    public AnimalSchedule() {}
    
    public AnimalSchedule(Animal animal, FeedingSchedule feedingSchedule) {
        super();
        this.animal = animal;
        this.feedingSchedule = feedingSchedule;
    }

    


    public Animal getAnimal() {
        return animal;
    }

    
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    
    public FeedingSchedule getFeedingSchedule() {
        return feedingSchedule;
    }

    
    public void setFeedingSchedule(FeedingSchedule feedingSchedule) {
        this.feedingSchedule = feedingSchedule;
    }

    @Override public String toString() {
        return "AnimalSchedule [animal=" + animal + ", feedingSchedule=" + feedingSchedule + "]";
    }
    
    
    
    
}
