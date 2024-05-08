package es.uji.al426285.Reader;

import es.uji.al426285.Table.Table;
import es.uji.al426285.Table.TableWithLabels;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CSVUnlabeledFileReaderTest {
    Table tabla_sin_etiquetas;
    List<String> prueba_etiquetas1 =new ArrayList<>();
    String prueba_data1 ="1211.0, 1802.0";

    List<String> prueba_etiquetas2 =new ArrayList<>();

    TableWithLabels tablaWithLabels1;
    Scanner fichero;
    String header_sin_label="sepal length,sepal width,petal length,petal width,class";
    String header_con_label="Miles,Dollars";


    CSVUnlabeledFileReader unlabeled=new CSVUnlabeledFileReader("src/main/java/es/uji/al426285/Files/miles_dollars.csv");
//    CSVLabeledFileReader labeled=new CSVLabeledFileReader("src/main/java/es/uji/al426285/Files/iris.csv");
//
//    CSVUnlabeledFileReader unlabeled_error=new CSVUnlabeledFileReader("src/main/java/es/uji/al426285/Files/iris.csv");
//    CSVLabeledFileReader labeled_error=new CSVLabeledFileReader("src/main/java/es/uji/al426285/Files/miles_dollars.csv");

    public void empezar() throws Exception {
        tabla_sin_etiquetas =new Table("src/main/java/es/uji/al426285/Files/miles_dollars.csv");

        prueba_etiquetas1.add("sepal length");prueba_etiquetas1.add("sepal width");prueba_etiquetas1.add("petal length");
        prueba_etiquetas1.add("petal width");prueba_etiquetas1.add("class");

        prueba_etiquetas2.add("Miles");prueba_etiquetas2.add("Dollars");
        //  tablaWithLabels1=new TableWithLabels("src/main/java/es/uji/al426285/Files/iris.csv");
        fichero = new Scanner(new File("src/main/java/es/uji/al426285/Files/miles_dollars.csv"));
    }


    @Test
    void openSource() throws Exception {
        empezar();
        assertThrows(FileNotFoundException.class, ()->unlabeled.openSource("src/main/java/es/uji/al426285/Files/miles_dollarss.csv"));
//        assertThrows(FileNotFoundException.class, ()->unlabeled_error.openSource("src/main/java/es/uji/al426285/Files/miles_dollarss.csv"));
//        assertThrows(FileNotFoundException.class, ()->labeled.openSource("src/main/java/es/uji/al426285/Files/iriss.csv"));
    }

    @Test
    void processHeaders() throws Exception {
        empezar();//unlabeled.readTableFromSource();
        unlabeled.openSource("src/main/java/es/uji/al426285/Files/miles_dollars.csv");
        unlabeled.processHeaders("Miles,Dollars");
        assertEquals(prueba_etiquetas2,unlabeled.getTabla().getHeader());

    }

    @Test
    void processData() throws Exception {
        empezar();
        unlabeled.openSource("src/main/java/es/uji/al426285/Files/miles_dollars.csv");
        unlabeled.processHeaders("Miles,Dollars");
        unlabeled.processData(prueba_data1);
        System.out.println("esto:"+unlabeled.getTabla().getLista().toString());;
        assertEquals("["+prueba_data1+"]",unlabeled.getTabla().getLista().get(0).getData().toString());
    }
}