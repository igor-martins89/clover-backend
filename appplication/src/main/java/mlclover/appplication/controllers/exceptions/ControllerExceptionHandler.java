package mlclover.appplication.controllers.exceptions;

import mlclover.appplication.services.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;


@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request){
        StandardError err = new StandardError(Instant.now(), 404, "Resource not found", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(404).body(err);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<StandardError> usernameNotFound(UsernameNotFoundException e, HttpServletRequest request){
        StandardError err = new StandardError(Instant.now(), 401, "Email not found", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(401).body(err);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<StandardError> badCredentials(BadCredentialsException e, HttpServletRequest request){
        StandardError err = new StandardError(Instant.now(), 401, "Invalid Email or Password", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(401).body(err);
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<StandardError> authenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException e, HttpServletRequest request){
        StandardError err = new StandardError(Instant.now(), 401, "Authentication Credentials Not Found Or Expired", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(401).body(err);
    }

    @ExceptionHandler(CategoriaSemAssociacaoException.class)
    public ResponseEntity<StandardError> categoriaSemAssociacaoException(CategoriaSemAssociacaoException e, HttpServletRequest request){
        StandardError err = new StandardError(Instant.now(), 400, "Object not found", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(400).body(err);
    }
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<StandardError> entityAlreadyExistsException(EntityAlreadyExistsException e, HttpServletRequest request){
        StandardError err = new StandardError(Instant.now(), 409, "Entity already exists", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(409).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .findFirst()
                .orElse("Invalid Argument Not Valid Exception");

        StandardError err = new StandardError(Instant.now(), 400, "Invalid Argument Not Valid Exception", errorMessage, request.getRequestURI());

        return ResponseEntity.status(400).body(err);
    }

}
