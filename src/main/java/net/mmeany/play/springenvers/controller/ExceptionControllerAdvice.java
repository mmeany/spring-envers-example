package net.mmeany.play.springenvers.controller;

import net.mmeany.play.springenvers.controller.model.ValidationFailedResponse;
import net.mmeany.play.springenvers.controller.model.ValidationMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationFailedResponse> handleMissingArguments(MethodArgumentNotValidException exception) {
        return handleBindExceptions(exception);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationFailedResponse> handleBindExceptions(BindException exception) {
        List<ValidationMessage> validationErrors = exception.getBindingResult().getAllErrors()
                .stream()
                .map(e -> new ValidationMessage(parameterName(e), e.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ValidationFailedResponse("VALIDATION FAILURE", validationErrors), HttpStatus.BAD_REQUEST);
    }

    private String parameterName(final ObjectError error) {
        return (error instanceof FieldError fieldError)
                ? fieldError.getField()
                : error.getObjectName();
    }
}
