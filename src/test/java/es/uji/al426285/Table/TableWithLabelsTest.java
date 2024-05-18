package es.uji.al426285.Table;

import es.uji.al426285.Row.RowWithLabel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableWithLabelsTest {
    TableWithLabels tabla1=new TableWithLabels();
    TableWithLabels tabla2=new TableWithLabels();
    TableWithLabels tabla3=new TableWithLabels();

    List<Double> lista1 =new ArrayList<>();
    List<Double> lista2 =new ArrayList<>();
    List<Double> lista3=new ArrayList<>();

    RowWithLabel row1=new RowWithLabel();
    RowWithLabel row2=new RowWithLabel();
    RowWithLabel row3=new RowWithLabel();

    List<RowWithLabel> lista_rows=new ArrayList<>();


    void crear(){
        lista1.add(5.1);lista1.add(3.5);lista1.add(1.4);
        lista2.add(1211.0); lista2.add(1802.0);lista1.add(1500.2);
        lista3.add(120.1); lista3.add(130.1); lista3.add(150.1);


        row1=new RowWithLabel(lista1,-1);
        row2=new RowWithLabel(lista2,-1);
        row3=new RowWithLabel(lista3,-1);

        lista_rows.add(row1);lista_rows.add(row2);lista_rows.add(row3);

    }

    @Test
    void addRowWithLabel() {
        crear();
        tabla1.addRowWithLabel(row1);
        tabla1.addRowWithLabel(row2);
        tabla1.addRowWithLabel(row3);
        assertEquals(tabla1.getRowAt(0),row1);
        assertEquals(tabla1.getRowAt(1),row2);
        assertEquals(tabla1.getRowAt(2),row3);
    }

    @Test
    void getRowAt() {
        crear();
        tabla1.addRowWithLabel(row1);
        tabla1.addRowWithLabel(row2);
        tabla1.addRowWithLabel(row3);
        assertEquals(tabla1.getRowAt(0),row1);
        assertEquals(tabla1.getRowAt(1),row2);
        assertEquals(tabla1.getRowAt(2),row3);
    }

    @Test
    void addLabel(){
        String etiqueta0="primera";
        String etiqueta1="segunda";
        String etiqueta2="tercera";
        String etiqueta2_repetida="tercera";
        String etiqueta0_repetida="primera";
        String etiqueta3="quarta";

        assertEquals(tabla1.addLabel(etiqueta0),0);
        assertEquals(tabla1.addLabel(etiqueta1),1);
        assertEquals(tabla1.addLabel(etiqueta2),2);
        assertEquals(tabla1.addLabel(etiqueta2_repetida),2);
        assertEquals(tabla1.addLabel(etiqueta0_repetida),0);
        assertEquals(tabla1.addLabel(etiqueta3),3);
    }


    @Test
    void getLabelsToIndex(){
        String etiqueta0="primera";
        String etiqueta1="segunda";
        String etiqueta2="tercera";
        String etiqueta3="quarta";

        tabla1.addLabel(etiqueta0);
        tabla1.addLabel(etiqueta1);
        tabla1.addLabel(etiqueta2);
        tabla1.addLabel(etiqueta3);

        assertFalse(tabla1.getLabelsToIndex().size()==3);
        assertTrue(tabla1.getLabelsToIndex().size()==4);

        assertTrue(tabla1.getLabelsToIndex().containsKey("primera"));
        assertTrue(tabla1.getLabelsToIndex().containsKey("segunda"));
        assertTrue(tabla1.getLabelsToIndex().containsKey("tercera"));
        assertTrue(tabla1.getLabelsToIndex().containsKey("quarta"));
        assertFalse(tabla1.getLabelsToIndex().containsKey("x"));

    }
}