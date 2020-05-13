package shala.ezoo.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class DatabaseConstraintViolationException extends DataIntegrityViolationException {

    public DatabaseConstraintViolationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DatabaseConstraintViolationException(String msg) {
        super(msg);
    }

 

}
