package mlclover.appplication.services.exceptions;

public class UsernameNotFoundException extends RuntimeException{

    public static final long serialVersionUID = 1L;

    public UsernameNotFoundException(String msg){
        super(msg);
    }
}
