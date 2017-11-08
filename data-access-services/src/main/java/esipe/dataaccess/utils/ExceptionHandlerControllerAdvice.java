package esipe.dataaccess.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ExceptionMessage> genericExceptionHandler(HttpServletRequest request, Exception exception) {
        ExceptionMessage message = ExceptionMessage.builder()
                .date(LocalDateTime.now().format(formatter))
                .path(request.getRequestURI().toString() + "?" + request.getQueryString())
                .className(exception.getClass().getName())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}