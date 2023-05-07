package br.com.emendes.powerkrtestapi.exception;

/**
 * Exception usado em caso de conflito de email do usuário no banco de dados.
 */
public class EmailAlreadyInUseException extends RuntimeException {

  public EmailAlreadyInUseException(String message) {
    super(message);
  }

}
