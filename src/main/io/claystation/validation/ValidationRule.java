package io.claystation.validation;

import io.claystation.exception.ValidationException;

public interface ValidationRule<T> {

   T validate(String input) throws ValidationException;

}
