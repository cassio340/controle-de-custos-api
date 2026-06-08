package br.com.cassio340.gestaodecustos.exceptions.custom;

import java.io.Serializable;

public class DataBaseException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
    public DataBaseException(String message) {

      super(message);
    }
}
