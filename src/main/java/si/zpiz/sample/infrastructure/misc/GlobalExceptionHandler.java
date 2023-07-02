package si.zpiz.sample.infrastructure.misc;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import si.zpiz.sample.domain.misc.ErrorDto;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidationErrors(MethodArgumentNotValidException ex,
            HandlerMethod handlerMethod, HttpServletRequest request) {
        log.error("Validation error", ex);

        String controllerName = handlerMethod.getMethod().getDeclaringClass().getName();
        String methodName = handlerMethod.getMethod().getName();

        String message = ex.getFieldErrors().stream()
                .map(fe -> fe.getField() + " " + fe.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(message);
        errorDto.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDto.setFromClass(ex.getStackTrace()[0].getClassName());
        errorDto.setControllerName(controllerName);
        errorDto.setMethodName(methodName);

        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleValidationErrors(EntityNotFoundException ex,
            HandlerMethod handlerMethod, HttpServletRequest request) {
        log.error("Entity not found error", ex);

        String controllerName = handlerMethod.getMethod().getDeclaringClass().getName();
        String methodName = handlerMethod.getMethod().getName();

        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(ex.getMessage());
        errorDto.setStatus(HttpStatus.NOT_FOUND.value());
        errorDto.setFromClass(ex.getStackTrace()[0].getClassName());
        errorDto.setControllerName(controllerName);
        errorDto.setMethodName(methodName);

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDto> handleAccessDeniedException(AccessDeniedException ex,
            HandlerMethod handlerMethod, HttpServletRequest request) {
        log.error("Access denied error", ex);

        String controllerName = handlerMethod.getMethod().getDeclaringClass().getName();
        String methodName = handlerMethod.getMethod().getName();

        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(ex.getMessage());
        errorDto.setStatus(HttpStatus.FORBIDDEN.value());
        errorDto.setFromClass(ex.getStackTrace()[0].getClassName());
        errorDto.setControllerName(controllerName);
        errorDto.setMethodName(methodName);

        return new ResponseEntity<>(errorDto, HttpStatus.FORBIDDEN);
    }

    // *****
    // AuthenticationException is handled by CustomAuthenticationEntryPoint
    // *****

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> generalExceptionHandler(Exception ex, HandlerMethod handlerMethod,
            HttpServletRequest request) {
        log.error("General error", ex);

        String controllerName = handlerMethod.getMethod().getDeclaringClass().getName();
        String methodName = handlerMethod.getMethod().getName();

        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(ex.getMessage());
        errorDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDto.setFromClass(ex.getStackTrace()[0].getClassName());
        errorDto.setControllerName(controllerName);
        errorDto.setMethodName(methodName);

        return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}