package mlclover.appplication.services.exceptions;

public class BadCredentialsException extends RuntimeException{

    public static final long serialVersionUID = 1L;

    public BadCredentialsException(String msg){
        super(msg);
    }
}
