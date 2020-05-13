package shala.ezoo.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import shala.ezoo.model.Animal;
import shala.ezoo.model.FeedingSchedule;


public class LoggerDefault implements DatabaseLogger {
    
    Logger log = LogManager.getLogger(LoggerDefault.class.getName());
    
    public LoggerDefault() {}
    
    
    public LoggerDefault(Logger log) {
        this.log = log;
    }

    
    public Logger getLog() {
        return log;
    }

    
    public void setLog(Logger log) {
        this.log = log;
    }
    
    public void logSave(Object o) {
        this.log.debug("SAVING: " + o);
    }

    public void logFailedSave(Object o) {
       this.log.error("FAILED SAVE: " + o);
        
    }

    public void logUpdate(Object o) {
        this.log.debug("UPDATE: " + o);
        
    }

    public void logRemoveAnimal(long id) {
        this.log.debug("DELETING: Animal " + id);
    }


    public void logRemoveAnimalSchedule(long id) {
        this.log.debug("DELETING: schedule from animal: " + id);
    }


    public void logRemoveSchedule(long id) {
        this.log.debug("DELETING: Schedule " + id);
    }


    public void logGetByID(long id) {
        this.log.debug("GETTING by id: " + id);
    }


    public void logGetAllBySchedule(long id) {
        this.log.debug("GETTING: all animals with schedule " + id);
    }


    public void logGetAll() {
        this.log.debug("GETTING: all objects");
    }


    public void logSetFeedingSchedule(FeedingSchedule schedule, Animal animal) {
        this.log.debug("SETTING schedule: " + schedule.getScheduleId() + " for animal: " + animal.getAnimalID());
    }


}
