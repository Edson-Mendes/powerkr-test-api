package br.com.emendes.powerkrtestapi.exception;

/**
 * Exception usado em caso de Recurso não encontrado.
 */
public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String message) {
    super(message);
  }

}
