package util;

import exception.InvalidInputException;

public class InputValidator {

    public static void validateString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty.");
        }
    }

    public static void validatePositiveNumber(int value, String fieldName) {
        if (value <= 0) {
            throw new InvalidInputException(fieldName + " must be greater than 0.");
        }
    }

    public static void validateEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new InvalidInputException("Invalid email format.");
        }
    }
}