package mat.study.store.product.exeption;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import mat.study.store.product.model.enums.InfoError;
import mat.study.store.product.model.response.Error;
import mat.study.store.product.model.response.ErrorDetail;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ProductControllerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {NoFoundProductException.class, NoFoundCategoryException.class})
  public ResponseEntity<Error> handleNoProductException(
      Exception ex) {
    Error body = Error.builder()
        .code(InfoError.NOT_FOUND.getCode())
        .message(ex.getMessage())
        .status(HttpStatus.NOT_FOUND)
        .time(LocalDateTime.now())
        .build();

    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }

  @Override
  protected ResponseEntity<Object> handleMissingPathVariable
      (MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    Error body = Error.builder()
        .code(InfoError.MISSING_PATH_VARIABLE.getCode())
        .message(ex.getVariableName() + InfoError.MISSING_PATH_VARIABLE.getMessage())
        .status(HttpStatus.BAD_REQUEST)
        .time(LocalDateTime.now())
        .build();

    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter
      (MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    Error body = Error.builder()
        .code(InfoError.MISSING_REQUEST_PARAMETER.getCode())
        .message(ex.getParameterName() + InfoError.MISSING_REQUEST_PARAMETER.getMessage())
        .status(HttpStatus.BAD_REQUEST)
        .time(LocalDateTime.now())
        .build();

    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid
      (MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    ErrorDetail error = ErrorDetail.builder()
        .code(InfoError.ARGUMENT_NOT_VALID.getCode())
        .message(InfoError.ARGUMENT_NOT_VALID.getMessage())
        .status(HttpStatus.BAD_REQUEST)
        .time(LocalDateTime.now())
        .detail(formatDetailMessage(ex.getFieldErrors()))
        .build();
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  private static String formatDetailMessage(List<FieldError> errors) {
    StringBuilder detailBuilder = new StringBuilder();
    if (!errors.isEmpty()) {
      for (int i = 0; i < errors.size(); i++) {
        detailBuilder.append("Error ").append(i + 1).append(": {").append(errors.get(i).getDefaultMessage() + "} ");
      }
    }
    return detailBuilder.toString();
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorDetail> handleConstraintViolation(ConstraintViolationException ex) {

    ErrorDetail body = ErrorDetail.builder()
        .code(InfoError.CONSTRAIN_VIOLATION.getCode())
        .message(InfoError.CONSTRAIN_VIOLATION.getMessage())
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .detail(buildValidationErrors(ex.getConstraintViolations()))
        .time(LocalDateTime.now())
        .build();
    return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  private static String buildValidationErrors(
      Set<ConstraintViolation<?>> violations) {
    return violations.stream()
        .map(ConstraintViolation::getMessage)
        .collect(Collectors.joining("; "));
  }

}
