package shala.ezoo.model;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class LocalDateTimeEditor extends PropertyEditorSupport {
    
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            setValue(LocalDateTime.parse(text, formatter));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Please use the date format YYYY-MM-DD HH:MM");
        }
    }
    
    @Override
    public String getAsText() {
        LocalDateTime time = ((LocalDateTime) getValue());
        return time != null ? time.format(formatter) : "";
    }
}
