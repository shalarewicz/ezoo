package shala.ezoo.model;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import shala.ezoo.dao.FeedingScheduleDAO;


public class FeedingScheduleEditor extends PropertyEditorSupport {
   
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        FeedingSchedule s = new FeedingSchedule();
        try {
            long id = Long.parseLong(text);
            s.setScheduleId(id);
        } catch (NumberFormatException e) {
           s = null;
        } 
        setValue(s);
        
    }
      
    @Override
    public String getAsText() {
        FeedingSchedule schedule = (FeedingSchedule) getValue();
        return schedule != null ? String.valueOf(schedule.getScheduleId()) : "";
    }
  
}
