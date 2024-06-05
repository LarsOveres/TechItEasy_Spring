package org.example.techiteasy.exceptions;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(Long id) {
        super("Record not found: " + id);
    }
}
