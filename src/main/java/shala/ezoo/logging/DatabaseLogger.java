package shala.ezoo.logging;

import shala.ezoo.model.Animal;
import shala.ezoo.model.FeedingSchedule;

public interface DatabaseLogger {
    
    public void logSave(Object o);
    
    public void logFailedSave(Object o);
    
    public void logUpdate(Object o);
    
    public void logRemoveAnimal(long id);
    
    public void logRemoveAnimalSchedule(long id);
    
    public void logRemoveSchedule(long id);
    
    public void logGetByID(long id);
    
    public void logGetAllBySchedule(long id);
    
    public void logGetAll();
    
    public void logSetFeedingSchedule(FeedingSchedule schedule, Animal animal);
    
    

}
