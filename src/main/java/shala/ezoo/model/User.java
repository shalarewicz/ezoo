package shala.ezoo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @NotNull @Size(min=8, message="{user.username.size.min}")
    @Size(max=80, message="{user.username.size.max}")
    private String username;
    
    @Column
    private boolean enabled = true;
    
    @Column
    @NotNull @Size(max=80, message="{user.name.size}")
    private String firstName;
    
    @Column
    @NotNull @Size(max=80, message="{user.name.size}")
    private String lastName;
    
    @Column
    @NotNull @Size(max=80, message="{user.email.size}")
    private String email;
    
    @Column
    @NotNull @Size(max=30, message="{user.phone.size}")
    private String phone;
    
    @Column
    @NotNull
    @Pattern(regexp = "^(?=.*\\d).{8,}$", message = "{user.password}") // Must contain 8 characters and and 2 numbers
    private String password;
    
    @Transient
    @NotNull
    private String confirmPassword;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "authorities", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = @JoinColumn(name = "authority"))
    private Set<Role> roles = new HashSet<Role>();
    
    
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "event_attendees", joinColumns = @JoinColumn(name="username"), inverseJoinColumns = @JoinColumn(name="eventid"))
    @ManyToMany(mappedBy = "attendees", fetch = FetchType.EAGER)
    private Set<Event> events = new HashSet<Event>();
    
    public User(String username, String firstName, String lastName, String email, String phone) {
        super();
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public User() {}

    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }

    
    public String getConfirmPassword() {
        return confirmPassword;
    }

    
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
   
    public Set<Role> getRoles() {
        return roles;
    }
    
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
    public void addRole(Role role) {
        this.roles.add(role);
    }
    
    public void removeRole(Role role) {
        this.roles.remove(role);
    }   
    
    public Set<Event> getEvents() {
        return events;
    }
    
    public void setEvents(Set<Event> events) {
        this.events = events;
    }
    
    public void removeEvent(Event e) {
        this.events.remove(e);
    }
    
    public void addEvent(Event e) {
        this.events.add(e);
    }
    

    @Override public String toString() {
        return "User [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", phone=" + phone + "]";
    }

    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }


    
    
}
