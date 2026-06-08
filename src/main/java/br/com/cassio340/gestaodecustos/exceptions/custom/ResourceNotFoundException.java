package br.com.cassio340.gestaodecustos.exceptions.custom;

import java.io.Serializable;

public class ResourceNotFoundException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;



    public ResourceNotFoundException(Long id) {
        super("Resource Not Found " + id);
    }
}
