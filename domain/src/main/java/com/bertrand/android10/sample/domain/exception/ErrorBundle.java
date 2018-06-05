
package com.bertrand.android10.sample.domain.exception;

/**
 * Interface to represent a wrapper around an {@link java.lang.Exception} to manage errors.
 */
public interface ErrorBundle {
  Exception getException();

  String getErrorMessage();
}
