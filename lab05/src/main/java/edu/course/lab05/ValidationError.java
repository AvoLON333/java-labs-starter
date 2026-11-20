package edu.course.lab05;

import java.util.Objects;

public final class ValidationError {

    private final int lineNumber;
    private final String field;
    private final String message;

    public ValidationError(int lineNumber, String field, String message) {
        if (lineNumber <= 0) {
            throw new IllegalArgumentException("Line number must be positive");
        }

        this.lineNumber = lineNumber;
        this.field = Objects.requireNonNull(field, "Field cannot be null");
        this.message = Objects.requireNonNull(message, "Message cannot be null");
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
