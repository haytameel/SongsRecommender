package es.uji.al426285.Reader;

import es.uji.al426285.Row.RowWithLabel;
import es.uji.al426285.Table.TableWithLabels;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;


class CSVLabeledFileReaderTest {
    //rutas archivos
    String sep = System.getProperty("file.separator");
    String iris = "src"+sep+"Files"+sep+"iris.csv";


    CSVLabeledFileReader labeled = new CSVLabeledFileReader(iris);

    //para processdata
    String data1 = "1.2, 3.4, 5.6, etiqueta1";
    ArrayList<Double> data1_lista=new ArrayList<>();
    String data2 = "2.5, 4.4, 9.6, etiqueta2";
    ArrayList<Double> data2_lista=new ArrayList<>();
    String data3 = "31.2, 1.4, 2.6, etiqueta1";
    ArrayList<Double> data3_lista=new ArrayList<>();

    //para processheader
    String header="sepal length,sepal width,petal length,petal width,class";
    List<String> prueba_header1 = new ArrayList<>();

    public void empezar() throws Exception {
        prueba_header1.add("sepal length");
        prueba_header1.add("sepal width");
        prueba_header1.add("petal length");
        prueba_header1.add("petal width");
        prueba_header1.add("class");

        //processdata
        data1_lista.add(1.2);data1_lista.add(3.4);data1_lista.add(5.6);
        data2_lista.add(2.5);data2_lista.add(4.4);data2_lista.add(9.6);
        data3_lista.add(31.2);data3_lista.add(1.4);data3_lista.add(2.6);

    }

    @Test
    void openSource() throws Exception {
        empezar();
        //probamos ruta errónea
        assertThrows(FileNotFoundException.class, () -> labeled.openSource("src/main/java/es/uji/al426285/Files/iriss.csv"));
        assertDoesNotThrow(() ->labeled.openSource(iris));
    }

    @Test
    void processHeaders() throws Exception {
        empezar();
        labeled.openSource(iris);
        labeled.processHeaders(header);
        assertEquals(((labeled.getTabla().getHeader())),prueba_header1);
    }

    @Test
    void processData() throws Exception {
        empezar();
        labeled.processData(data1);
        labeled.processData(data2);
        labeled.processData(data3);

        //Podriamos usar en su lugar un aseerttrue--> assertTrue(labeled.getLista_rows().get(0).getData().equals(data1_lista));
        assertEquals(labeled.getLista_rows().get(0).getData(), data1_lista);
        assertEquals(labeled.getLista_rows().get(1).getData(), data2_lista);
        assertEquals(labeled.getLista_rows().get(2).getData(), data3_lista);


    }
}