package es.uji.al426285.Table;

import es.uji.al426285.Row.Row;
import es.uji.al426285.Row.RowWithLabel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {
    Table tabla1=new Table();
    Table tabla2=new Table();

    List<Double> lista1 =new ArrayList<>();
    List<Double> lista2 =new ArrayList<>();
    List<Double> lista3=new ArrayList<>();

    RowWithLabel row1=new RowWithLabel();
    RowWithLabel row2=new RowWithLabel();
    RowWithLabel row3=new RowWithLabel();

    List<RowWithLabel> lista_rows=new ArrayList<>();

    List<String> prueba_header1 = new ArrayList<>();

    void crear(){
        lista1.add(5.1);lista1.add(3.5);lista1.add(1.4);
        lista2.add(1211.0); lista2.add(1802.0);lista1.add(1500.2);
        lista3.add(120.1); lista3.add(130.1); lista3.add(150.1);

        prueba_header1.add("Milles");
        prueba_header1.add("Dollars");

        row1=new RowWithLabel(lista1,-1);
        row2=new RowWithLabel(lista2,-1);
        row3=new RowWithLabel(lista3,-1);

        lista_rows.add(row1);lista_rows.add(row2);lista_rows.add(row3);

    }

    @Test
    void getRowAt() {
        crear();
        tabla2.addElements(lista_rows);
        assertEquals(tabla2.getRowAt(0), lista_rows.get(0));
        assertEquals(tabla2.getRowAt(1), lista_rows.get(1));
        assertEquals(tabla2.getRowAt(2), lista_rows.get(2));

    }

    @Test
    void addHeader() {
        crear();
        assertFalse(tabla1.addHeader(null));
        assertTrue(tabla2.addHeader(prueba_header1));
        assertEquals(tabla2.getHeader(), prueba_header1);
    }

    @Test
    void addElements() {
        crear();
        assertFalse(tabla1.addElements(null));
        assertTrue(tabla2.addElements(lista_rows));
    }


}