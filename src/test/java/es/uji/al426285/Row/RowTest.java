package es.uji.al426285.Row;

import es.uji.al426285.Table.Table;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RowTest {
    Table tabla1 ;
    Table tabla2 ;

    List<Double> lista_tabla1=new ArrayList<>();
    List<Double> lista_tabla2=new ArrayList<>();
    List<Double> lista3=new ArrayList<>();
    Row linea1;
    Row linea2;


    void crear_listas() throws Exception {
        lista_tabla1.add(5.1);lista_tabla1.add(3.5);lista_tabla1.add(1.4);lista_tabla1.add(0.2);
        lista_tabla2.add(1211.0);lista_tabla2.add(1802.0);

        // tabla1=new Table("src/main/java/es/uji/al426285/Files/iris.csv");
        tabla2 = new Table("src/main/java/es/uji/al426285/Files/miles_dollars.csv");
        linea1=new Row(lista_tabla1);
        linea2=new Row(lista_tabla2);
    }

    @Test
    void getData() throws Exception {
        crear_listas();
        //Prueba construcción de rows correcta
        assertEquals(lista_tabla1,linea1.getData());
        assertEquals(lista_tabla2,linea2.getData());
        //Prueba obtención de row
        assertEquals(lista_tabla2,tabla2.getRowAt(0).getData());

    }
}