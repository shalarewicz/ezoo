package shala.ezoo.model;


public enum UserRole {
    USER, EMPLOYEE, ADMIN;
    
    public static String toString(UserRole role) {
        switch (role) {
        case ADMIN:
            return "ROLE_ADMIN";
        case EMPLOYEE:
            return "ROLE_EMPLOYEE";
        case USER:
            return "ROLE_USER";
        default:
            throw new RuntimeException(role + "is not defined");
        }
    }
    
    public static UserRole fromString(String role) {
        switch (role) {
        case "ROLE_ADMIN":
            return ADMIN;
        case "ROLE_EMPLOYEE":
            return EMPLOYEE;
        case "ROLE_USER":
            return USER;
        default:
            throw new RuntimeException(role + "is not defined");
        }
    }
}