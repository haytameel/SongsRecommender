package es.uji.al426285.Row;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RowWithLabelTest {
    RowWithLabel rowwithlabel1;
    RowWithLabel rowwithlabel2;
    RowWithLabel rowwithlabel3;

    List<Double> lista1=new ArrayList<>();
    List<Double> lista2=new ArrayList<>();
    List<Double> lista3=new ArrayList<>();

    void crear(){
        lista1.add(1.0);lista1.add(2.0);lista1.add(3.0);
        lista2.add(2.0);lista2.add(4.0);lista2.add(6.0);
        lista3.add(4.0);lista3.add(8.0);lista3.add(20.0);
        //////////////
        rowwithlabel1=new RowWithLabel(lista1,0);
        rowwithlabel2=new RowWithLabel(lista2,1);
        rowwithlabel3=new RowWithLabel(lista3,2);
    }

    @Test
    void getNumber_class() {
        crear();
        assertEquals(rowwithlabel1.getNumber_class(),0);
        assertEquals(rowwithlabel2.getNumber_class(),1);
        assertEquals(rowwithlabel3.getNumber_class(),2);
    }

    @Test
    void getData() {
        crear();
        assertEquals(rowwithlabel1.getData(),lista1);
        assertEquals(rowwithlabel2.getData(),lista2);
        assertEquals(rowwithlabel3.getData(),lista3);
    }
}
