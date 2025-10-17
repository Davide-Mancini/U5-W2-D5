package davidemancini.U5_W2_D5.exceptions;

import davidemancini.U5_W2_D5.payloads.ErrorDTO;
import davidemancini.U5_W2_D5.payloads.ErrorListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(MyValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorListDTO validationErrorHandler(MyValidationException ex){
        return new ErrorListDTO(ex.getMessage(), LocalDateTime.now(),ex.getErrorsMex());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO notFoundHandler(NotFoundException ex){
        return new ErrorDTO(ex.getMessage(),LocalDateTime.now());
    }
    @ExceptionHandler(MyBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO badRequestHandler(MyBadRequestException ex){
        return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
    }
}
