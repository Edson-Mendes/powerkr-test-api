package br.com.emendes.powerkrtestapi.handler;

import br.com.emendes.powerkrtestapi.exception.EmailAlreadyInUseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      @NonNull MethodArgumentNotValidException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {
    String fields = ex.getFieldErrors().stream().map(FieldError::getField)
        .collect(Collectors.joining("; "));
    String messages = ex.getFieldErrors().stream().map(FieldError::getDefaultMessage)
        .collect(Collectors.joining("; "));

    ProblemDetail problemDetail = ProblemDetail
        .forStatusAndDetail(HttpStatusCode.valueOf(400), "Some fields are invalid");

    problemDetail.setType(URI.create("https://github.com/Edson-Mendes/powerkr-test-api/problem-detail/invalid-fields"));
    problemDetail.setTitle("Invalid arguments");
    problemDetail.setProperty("fields", fields);
    problemDetail.setProperty("messages", messages);

    return ResponseEntity.badRequest().body(problemDetail);
  }

  @ExceptionHandler(EmailAlreadyInUseException.class)
  public ResponseEntity<ProblemDetail> handleEmailAlreadyInUse(EmailAlreadyInUseException ex) {
    ProblemDetail problemDetail = ProblemDetail
        .forStatusAndDetail(HttpStatusCode.valueOf(409), ex.getMessage());

    problemDetail.setType(URI.create("https://github.com/Edson-Mendes/powerkr-test-api/problem-detail/email-already-in-use"));
    problemDetail.setTitle("Email conflict");

    return ResponseEntity.status(409).body(problemDetail);
  }

}
