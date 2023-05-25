package mlclover.appplication.services.exceptions;

public class EntityAlreadyExistsException extends RuntimeException{

    public static final long serialVersionUID = 1L;

    public EntityAlreadyExistsException(String msg){
        super(msg);
    }
}
