package es.uji.al426285.Algorithms;

import java.util.List;

public class EuclideanDistance implements Distancia {

    public Double calculateDistance(List<Double> data, List<Double> fila_tabla) {
        double res = 0;
        if (data.size() != fila_tabla.size())
            throw new IllegalArgumentException("Tamaño de la fila introducida incorrecto");
        for (int i = 0; i < data.size(); i++) {
            res += Math.pow(data.get(i) - fila_tabla.get(i), 2);
        }
        return Math.sqrt(res);
    }
}