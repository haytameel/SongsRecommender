package es.uji.al426285.Exceptions;


public class FunctionNotExecutedException extends RuntimeException {
    public FunctionNotExecutedException(String functionName) {
        super("La función '" + functionName + "' no ha sido ejecutada.");
    }
}