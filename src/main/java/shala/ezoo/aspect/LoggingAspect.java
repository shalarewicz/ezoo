package shala.ezoo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;

import shala.ezoo.logging.DatabaseLogger;
import shala.ezoo.logging.LoggerDefault;
import shala.ezoo.model.Animal;
import shala.ezoo.model.FeedingSchedule;

@Aspect
public class LoggingAspect {
    
    @DeclareParents(value="shala.ezoo.*.*", defaultImpl=LoggerDefault.class)
    private DatabaseLogger dbLogger;
    
    @Pointcut("execution( * shala.ezoo.dao.*.save*(shala.ezoo.model.*) ) && this(logger) && args(object)")
    public void logSave(DatabaseLogger logger, Object object) {
    }
    
    @Pointcut("execution( * shala.ezoo.dao.*.update*(shala.ezoo.model.*) ) && this(logger) && args(object)")
    public void logUpdate(DatabaseLogger logger, Object object) {
    }
    
    
    @Pointcut("execution( * shala.ezoo.dao.AnimalDAO.removeAnimal(long) ) && this(logger) && args(id)")
    public void logRemoveAnimal(DatabaseLogger logger, long id) {
    }
    @Pointcut("execution( * shala.ezoo.dao.AnimalDAO.removeSchedule(long) ) && this(logger) && args(id)")
    public void logRemoveAnimalSchedule(DatabaseLogger logger, long id) {
    }
    @Pointcut("execution( * shala.ezoo.dao.FeedingScheduleDAO.removeSchedule(long) ) && this(logger) && args(id)")
    public void logRemoveSchedule(DatabaseLogger logger, long id) {
    }
    
    
    // TODO Two pointcuts to specify animal or schedule
    @Pointcut("execution( * shala.ezoo.dao.*.get*ByID(long) ) && this(logger) && args(id)")
    public void logGetByID(DatabaseLogger logger, long id) {
    }
    
    @Pointcut("execution( * shala.ezoo.dao.AnimalDAO.getAllAnimals(long) ) && this(logger) && args(id)")
    public void logGetAllBySchedule(DatabaseLogger logger, long id) {
    }
    
    @Pointcut("execution( * shala.ezoo.dao.*.getAll*() ) && this(logger)")
    public void logGetAll(DatabaseLogger logger) {
    }
    
    
    @Pointcut("execution( * shala.ezoo.dao.*.setFeedingSchedule*(shala.ezoo.model.FeedingSchedule, shala.ezoo.model.Animal) ) && this(logger) && args(schedule, animal)")
    public void logSetFeedingSchedule(DatabaseLogger logger, FeedingSchedule schedule, Animal animal) {
    }
    
    
    
    @Before("logSave(logger, object)")
    public void loggingSave(DatabaseLogger logger, Object object) {
            logger.logSave(object);
    }
      
    @Before("logUpdate(logger, object)")
    public void loggingUpdate(DatabaseLogger logger, Object object) {
            logger.logUpdate(object);
    }
    
    
    @Before("logRemoveAnimal(logger, id)")
    public void loggingRemoveAnimal(DatabaseLogger logger, long id) {
            logger.logRemoveAnimal(id);
    }
    
    @Before("logRemoveAnimalSchedule(logger, id)")
    public void loggingRemoveAnimalsSchedule(DatabaseLogger logger, long id) {
            logger.logRemoveAnimalSchedule(id);
    }
    
    @Before("logRemoveSchedule(logger, id)")
    public void loggingRemoveSchedule(DatabaseLogger logger, long id) {
            logger.logRemoveSchedule(id);
    }
    
    @Before("logGetByID(logger, id)")
    public void loggingGetByID(DatabaseLogger logger, long id) {
            logger.logGetByID(id);
    }
    @Before("logGetAllBySchedule(logger, id)")
    public void loggingGetAll(DatabaseLogger logger, long id) {
            logger.logGetAllBySchedule(id);
    }
    @Before("logGetAll(logger)")
    public void loggingGetAll(DatabaseLogger logger) {
            logger.logGetAll();
    }
    
    @Before("logSetFeedingSchedule(logger, schedule, animal)")
    public void loggingSetSchedule(DatabaseLogger logger, FeedingSchedule schedule, Animal animal) {
            logger.logSetFeedingSchedule(schedule, animal);
    }
}
