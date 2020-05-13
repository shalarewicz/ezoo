package shala.ezoo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import shala.ezoo.dao.FeedingScheduleDAO;
import shala.ezoo.model.FeedingSchedule;

@Component
public class CheckValidScheduleIdValidator implements ConstraintValidator<CheckValidScheduleID, FeedingSchedule>{
    
    @Autowired
    private FeedingScheduleDAO dao;
    
    public void initialize(CheckValidScheduleID constraintAnnotation) {
        // This prevents a null pointer on second call to validator
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    
    @Override
    public boolean isValid(FeedingSchedule schedule, ConstraintValidatorContext context) {
        return schedule == null || dao.getScheduleByID(schedule.getScheduleId()) != null;
    }

}
