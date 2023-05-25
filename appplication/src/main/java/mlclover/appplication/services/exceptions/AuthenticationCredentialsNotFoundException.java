package mlclover.appplication.services.exceptions;

public class AuthenticationCredentialsNotFoundException extends RuntimeException{

    public static final long serialVersionUID = 1L;

    public AuthenticationCredentialsNotFoundException(String msg){
        super(msg);
    }
}
