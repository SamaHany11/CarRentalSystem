package org.FirstTest;

public class RentalAlreadyCompletedException extends Exception {
    public RentalAlreadyCompletedException(String message) {
        super(message);
    }
}