package br.com.emendes.powerkrtestapi.exception;

public class EmailAlreadyInUseException extends RuntimeException {

  public EmailAlreadyInUseException(String message) {
    super(message);
  }

}
