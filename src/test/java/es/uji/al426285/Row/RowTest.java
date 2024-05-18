package es.uji.al426285.Row;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RowTest {

    List<Double> lista1 =new ArrayList<>();
    List<Double> lista2 =new ArrayList<>();
    List<Double> lista3=new ArrayList<>();
    Row linea1;
    Row linea2;
    Row linea3;



    void crear_listas() throws Exception {

        lista1.add(5.1);lista1.add(3.5);lista1.add(1.4);lista1.add(0.2);
        lista2.add(1211.0); lista2.add(1802.0);
        linea1=new Row(lista1);
        linea2=new Row(lista2);
        linea3=new Row(lista3);
    }

    @Test
    void getData() throws Exception {
        crear_listas();
        //Prueba construcción de rows correcta
        assertEquals(lista1,linea1.getData());
        assertEquals(lista2,linea2.getData());
        assertEquals(lista3,linea3.getData());
    }
}