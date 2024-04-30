package es.uji.al426285.Exceptions;

public class RowsLowerClustersException extends Exception{

    public RowsLowerClustersException() {
        super("El numero de grupos es mayor al numero de filas existentes");
    }
}