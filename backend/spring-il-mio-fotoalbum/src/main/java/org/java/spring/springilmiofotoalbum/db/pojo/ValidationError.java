package org.java.spring.springilmiofotoalbum.db.pojo;

public class ValidationError {

    private String field;
    private String message;

    public ValidationError() {
    }

    public ValidationError(String field, String message) {
        setField(field);
        setMessage(message);
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
