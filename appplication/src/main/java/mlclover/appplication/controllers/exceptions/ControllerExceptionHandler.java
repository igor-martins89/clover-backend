package mlclover.appplication.controllers.exceptions;

import mlclover.appplication.services.exceptions.*;
import org.springframework.http.ResponseEntity;
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
        StandardError err = new StandardError(Instant.now(), 404, "Email not found", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(404).body(err);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<StandardError> badCredentials(BadCredentialsException e, HttpServletRequest request){
        StandardError err = new StandardError(Instant.now(), 404, "Invalid Email or Password", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(404).body(err);
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<StandardError> authenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException e, HttpServletRequest request){
        StandardError err = new StandardError(Instant.now(), 404, "Authentication Credentials Not Found Or Expired", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(404).body(err);
    }

    @ExceptionHandler(CategoriaSemAssociacaoException.class)
    public ResponseEntity<StandardError> categoriaSemAssociacaoException(CategoriaSemAssociacaoException e, HttpServletRequest request){
        StandardError err = new StandardError(Instant.now(), 404, "Object not found", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(404).body(err);
    }
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<StandardError> entityAlreadyExistsException(EntityAlreadyExistsException e, HttpServletRequest request){
        StandardError err = new StandardError(Instant.now(), 404, "Entity already exists", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(404).body(err);
    }

}
