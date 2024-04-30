package es.uji.al426285.Algorithms;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EuclideanDistanceTest {

    EuclideanDistance prueba1 = new EuclideanDistance();
    List<Double> lista1 = new ArrayList<>();
    List<Double> lista2 = new ArrayList<>();

    List<Double> lista3 = new ArrayList<>();
    List<Double> lista4 = new ArrayList<>();

    void asignar_valores() {
        for (double i = 0; i < 6; i++) {
            lista1.add(i);
            lista2.add(i + 1);

            lista3.add(i);
            lista4.add(i * 2);
        }

    }

    @Test
    void calculateDistance() {
        asignar_valores();
        assertEquals(Math.sqrt(6), prueba1.calculateDistance(lista1, lista2));
        assertEquals(Math.sqrt(55), prueba1.calculateDistance(lista3, lista4));
        lista1 = lista2 = null;
        assertThrows(NullPointerException.class, () -> prueba1.calculateDistance(lista1, lista2));
        lista1 = new ArrayList<>(4);
        assertThrows(IllegalArgumentException.class, () -> prueba1.calculateDistance(lista1, lista3));

    }
}