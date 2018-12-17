package io.claystation.validation;

import io.claystation.exception.ParseException;

public interface Parser<T> {

   T parse(String input) throws ParseException;

}
