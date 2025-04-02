package com.dsfyr.ecommerce.Error;

public abstract class  CustomError extends RuntimeException {
    CustomError(String message) {
        super(message);
    }
}
