package isthatkirill.vkproject.error;

import isthatkirill.vkproject.error.exception.NotUniqueException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kirill Emelyanov
 */

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> argumentNotValidHandle(final MethodArgumentNotValidException e) {
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        String errorMessage = errors.stream()
                .map(error -> String.format("Field: %s, error: %s, value: %s",
                        error.getField(), error.getDefaultMessage(), error.getRejectedValue()))
                .collect(Collectors.joining("\n"));

        HttpStatus status = HttpStatus.BAD_REQUEST;
        log.error("Error: {} Description: {}", status.getReasonPhrase(), errorMessage);

        return ResponseEntity.status(status)
                .body(new ErrorMessage(status.getReasonPhrase(), errorMessage));
    }

    @ExceptionHandler({NotUniqueException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorMessage> notUniqueHandle(final RuntimeException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        log.error("Error: {} Description: {}", status.getReasonPhrase(), e.getMessage());

        return ResponseEntity.status(status)
                .body(new ErrorMessage(status.getReasonPhrase(), e.getMessage()));
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<ErrorMessage> webClientExceptionHandle(final WebClientResponseException e) {
        log.error("Error: {} Description: {}", e.getStatusText(), e.getMessage());
        return ResponseEntity.status(e.getStatusCode())
                .body(new ErrorMessage(
                        HttpStatus.valueOf(e.getStatusCode().value()).getReasonPhrase(),
                        e.getMessage())
                );
    }

}
