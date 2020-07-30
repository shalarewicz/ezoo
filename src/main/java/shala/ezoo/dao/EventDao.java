package shala.ezoo.dao;

import java.util.List;

import shala.ezoo.model.Event;

public interface EventDao {
    
    /**
     * Returns the event with the specified ID. 
     * @param eventId id of the event
     * @return Event object with the specified id
     */
    public Event getEvent(long eventId);
    
    /**
     * Persists the event to the database. Event ID must be null
     * @param event
     * @return the generated id of the event or 0 if the event already exists
     */
    public long saveEvent(Event event);
    
    /**
     * Removes the event with the specified id from the database
     * @param eventId
     * @return the removed event
     */
    public Event removeEvent(long eventId);
    
    /**
     * Updates the event in the database matching the given events id
     * @param event
     */
    public void updateEvent(Event event);
    
    /**
     * Registers an event attendee
     * @param eventId ID of the event being attended
     * @param username username of the user attending the event
     * @return True if regisration is successfull
     */
    public boolean registerEventUser(long eventId, String username);
    
    /**
     * 
     * @param eventId ID of the event the user is no longer attending
     * @param username username of the user being removed
     * @return True if user is successfully unregistered
     */
    public boolean removeEventUser(long eventId, String username);
    
    /**
     * Get all events for a given user
     * @param username specifies the user
     * @return A list of all events being attended by the current user. 
     */
    public List<Event> getAllEvents(String username);
    
    /**
     * 
     * @return A list of all events
     */
    public List<Event> getAllEvents();

}
