package mat.study.store.product.exeption;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

  private static final String NOT_FOUND_PRODUCT = "Not found product";
  private static final String NOT_FOUND_CATEGORY = "Not found category";
  private static final String PARAMETER_MISSING_PATH = "Parameter is missing in the path";
  private static final String PARAMETER_MISSING = "Parameter is missing";
  private static final String BODY_ERROR = "Error body request";
  private static final String PARAMETER_RESTRICTION = "Violation of parameter restriction";
  private static final String USER_EXIST = "User exist";
  private static final String INVALID_CREDENTIALS = "Invalid Credentials";

  @ExceptionHandler(value = NoFoundProductException.class)
  public ProblemDetail handleNoProductException(
      NoFoundProductException ex) {
    var problemDetail = ProblemDetail
        .forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    problemDetail.setType(URI.create("http://localhost:8080/errors/product-not-found"));
    problemDetail.setTitle(NOT_FOUND_PRODUCT);
    problemDetail.setProperty("productId", ex.getId());
    return problemDetail;
  }

  @ExceptionHandler(value = NoFoundCategoryException.class)
  public ProblemDetail handleNoCategoryException(
      NoFoundCategoryException ex) {
    var problemDetail = ProblemDetail
        .forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    problemDetail.setType(URI.create("http://localhost:8080/errors/category-not-found"));
    problemDetail.setTitle(NOT_FOUND_CATEGORY);
    problemDetail.setProperty("categoryId", ex.getId());
    return problemDetail;
  }

  @Override
  protected ResponseEntity<Object> handleMissingPathVariable
      (MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    var problemDetail = ProblemDetail
        .forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    problemDetail.setType(URI.create("http://localhost:8080/errors/parameter-missing-path"));
    problemDetail.setTitle(PARAMETER_MISSING_PATH);
    problemDetail.setDetail(ex.getVariableName() + " parameter is missing in the path");

    return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter
      (MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    var problemDetail = ProblemDetail
        .forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    problemDetail.setType(URI.create("http://localhost:8080/errors/parameter-missing"));
    problemDetail.setTitle(PARAMETER_MISSING);
    problemDetail.setDetail(ex.getParameterName() + " parameter is missing");

    return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid
      (MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    var problemDetail = ProblemDetail
        .forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    problemDetail.setType(URI.create("http://localhost:8080/errors/body-error"));
    problemDetail.setTitle(BODY_ERROR);
    problemDetail.setDetail("Validation failed for arguments");
    problemDetail.setProperty("invalid-params", formatDetailMessage(ex.getFieldErrors()));

    return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
  }

  private static List<Map<String, String>> formatDetailMessage(List<FieldError> errors) {
    return errors.stream()
        .map(error -> Map.of("name", error.getField(), "reason", error.getDefaultMessage()))
        .toList();
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ProblemDetail handleConstraintViolation(ConstraintViolationException ex) {
    var problemDetail = ProblemDetail
        .forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    problemDetail.setType(URI.create("http://localhost:8080/errors/parameter-restriction"));
    problemDetail.setTitle(PARAMETER_RESTRICTION);
    problemDetail.setDetail(buildValidationErrors(ex.getConstraintViolations()));

    return problemDetail;
  }

  private static String buildValidationErrors(
      Set<ConstraintViolation<?>> violations) {
    return violations.stream()
        .map(ConstraintViolation::getMessage)
        .collect(Collectors.joining("; "));
  }

  @ExceptionHandler(NonUniqueUserException.class)
  public ProblemDetail handleNonUniqueUserException(NonUniqueUserException ex) {
    var problemDetail = ProblemDetail
        .forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    problemDetail.setType(URI.create("http://localhost:8080/errors/user-exist"));
    problemDetail.setTitle(USER_EXIST);
    problemDetail.setDetail("This user already exists");

    return problemDetail;
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ProblemDetail handleIllegalArgumentException(IllegalArgumentException ex) {
    var problemDetail = ProblemDetail
        .forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    problemDetail.setType(URI.create("http://localhost:8080/errors/invalid-credentials"));
    problemDetail.setTitle(INVALID_CREDENTIALS);
    problemDetail.setDetail("Invalid email or password");

    return problemDetail;
  }


}
