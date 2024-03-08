package isthatkirill.vkproject.error;

import isthatkirill.vkproject.error.exception.NotUniqueException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kirill Emelyanov
 */

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage argumentNotValidHandle(final MethodArgumentNotValidException e) {
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        String errorMessage = errors.stream()
                .map(error -> String.format("Field: %s, error: %s, value: %s",
                        error.getField(), error.getDefaultMessage(), error.getRejectedValue()))
                .collect(Collectors.joining("\n"));
        log.error("Error: {} Description: {}", HttpStatus.BAD_REQUEST.getReasonPhrase(), errorMessage);
        return new ErrorMessage(HttpStatus.BAD_REQUEST.getReasonPhrase(), errorMessage);
    }

    @ExceptionHandler({NotUniqueException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage notUniqueHandle(final RuntimeException e) {
        log.error("Error: {} Description: {}", HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage());
        return new ErrorMessage(HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage());
    }

}
