package es.uji.al426285.Reader;

import es.uji.al426285.Table.TableWithLabels;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CSVLabeledFileReaderTest {
    TableWithLabels tablaWithLabels1;
    List<String> prueba_header1 = new ArrayList<>();
    Scanner fichero;
    CSVLabeledFileReader labeled = new CSVLabeledFileReader("src/main/java/es/uji/al426285/Files/iris.csv");


    public void empezar() throws Exception {
        tablaWithLabels1 = new TableWithLabels("src/main/java/es/uji/al426285/Files/iris.csv");
        prueba_header1.add("sepal length");
        prueba_header1.add("sepal width");
        prueba_header1.add("petal length");
        prueba_header1.add("petal width");
        prueba_header1.add("class");

        fichero = new Scanner(new File("src/main/java/es/uji/al426285/Files/iris.csv"));
    }

    @Test
    void openSource() throws Exception {
        empezar();
        assertThrows(FileNotFoundException.class, () -> labeled.openSource("src/main/java/es/uji/al426285/Files/iriss.csv"));

    }

    @Test
    void processHeaders() throws Exception {
        empezar();
    }

    @Test
    void processData() {
    }
}