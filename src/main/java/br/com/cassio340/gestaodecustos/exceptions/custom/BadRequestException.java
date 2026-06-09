package br.com.cassio340.gestaodecustos.exceptions.custom;


import java.io.Serializable;

public class BadRequestException extends RuntimeException implements Serializable {
    private static final long  serialVersionUID = 1L;

    public BadRequestException(String message) {

        super(message);
    }
}
