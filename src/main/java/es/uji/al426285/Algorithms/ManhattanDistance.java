package es.uji.al426285.Algorithms;

import java.util.List;

public class ManhattanDistance implements Distancia {
    public Double calculateDistance(List<Double> data, List<Double> fila_tabla) {
        double res = 0;
        if (data == null || fila_tabla == null) throw new NullPointerException("Alguna de las listas es null, o ambas");
        if (data.size() != fila_tabla.size())
            throw new IllegalArgumentException("Tamano de la fila intorducida incorrecto");
        for (int i = 0; i < data.size(); i++) {
            res += data.get(i) - fila_tabla.get(i);
        }
        return Math.abs(res);
    }
}