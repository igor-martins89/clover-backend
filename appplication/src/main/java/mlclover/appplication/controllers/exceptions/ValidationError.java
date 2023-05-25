package mlclover.appplication.controllers.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

    private static final long serialVersionUID = 1L;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError() {
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public ValidationError(Integer status, String msg, Instant timeStamp) {
        super(timeStamp, status, msg);
    }

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}