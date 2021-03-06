package shala.ezoo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ROLES")
public class Role {
    
    public static final Role ROLE_USER = new Role(1L, UserRole.USER);
    public static final Role ROLE_ADMIN = new Role(2L, UserRole.ADMIN);
    public static final Role ROLE_EMPLOYEE = new Role(3L, UserRole.EMPLOYEE);
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL) // Name of field mapping the relationship
    private Set<User> users;
    
    
    public Role() {}
    
    public Role(Long id, UserRole name) {
        this.name = UserRole.toString(name);
        this.id = id;
    }
    
    public Role(UserRole name) {
        this.name = UserRole.toString(name);
    }
    
    public Long getId() {
        return id;
    }  
   
    public UserRole getName() {
        return UserRole.fromString(this.name);
    }

    
    public void setName(UserRole name) {
        this.name = name.toString();
    }
    
    
    public Set<User> getUsers() {
        return users;
    }

    
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    
    @Override 
    public String toString() {
        return "Role [id=" + id + ", name=" + name + "]";
    }

    @Override 
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Role other = (Role) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
    
    
    
}
