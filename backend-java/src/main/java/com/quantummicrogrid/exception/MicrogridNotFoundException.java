package com.quantummicrogrid.exception;

public class MicrogridNotFoundException extends RuntimeException{

    public MicrogridNotFoundException(Long id) {
        super("Microgrid introuvable avec l'id : " + id);
    }
}
