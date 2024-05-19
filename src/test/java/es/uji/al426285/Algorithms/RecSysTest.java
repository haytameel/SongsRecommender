package es.uji.al426285.Algorithms;

import es.uji.al426285.Exceptions.NameNotFoundException;
import es.uji.al426285.Exceptions.RowsLowerClustersException;
import es.uji.al426285.Exceptions.TableNotTrainedException;
import es.uji.al426285.Table.Table;
import es.uji.al426285.Table.TableWithLabels;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecSysTest {
    String sep = System.getProperty("file.separator");
    String iris = "src" + sep + "Files" + sep + "iris.csv";
    String miles_dollars = "src" + sep + "Files" + sep + "miles_dollars.csv";
    String canciones="src" + sep + "Files" + sep +"Datos_y_codigo"+sep+"recsys"+sep+"songs_files"+sep+"songs_test_names.csv";
    Distancia ditancia_euclideana;
    Distancia distancia_manhattan;


    //KNN
    Algorithm<TableWithLabels, Integer> prueba_knn_euclideana;
    Algorithm<TableWithLabels, Integer> prueba_knn_manhattan;

    //KMEANS
    Algorithm<Table, Integer> prueba_Kmeans_euclideana;
    Algorithm<Table, Integer> prueba_Kmeans_manhattan;
    Algorithm<Table, Integer> kmeansclustersexception;


    RecSys recsys_kmeans_euclideana1;
    RecSys recsys_knn_manhattan1;

    //LISTAS
    List<Double> lista1 = null;
    List<Double> lista2 = new ArrayList<>();
    List<Double> lista3 = new ArrayList<>();
    List<Double> lista4=new ArrayList<>();
    List<Double> lista5 = new ArrayList<>();
    List<Double> lista6=new ArrayList<>();
    //TABLAS
    Table tabla;
    TableWithLabels tablawithlabels;
    List<String> lista_canciones;


    @BeforeEach
    void setUp() throws Exception {
        ditancia_euclideana = new EuclideanDistance();
        distancia_manhattan = new ManhattanDistance();

        //KNN
        prueba_knn_euclideana = new KNN(ditancia_euclideana);
        prueba_knn_manhattan = new KNN(distancia_manhattan);

        //KMEANS
        prueba_Kmeans_euclideana = new Kmeans(5, 5, 10000, ditancia_euclideana);
        prueba_Kmeans_manhattan = new Kmeans(5, 5, 10000, distancia_manhattan);
        kmeansclustersexception =new Kmeans(155, 5, 10000, new EuclideanDistance());
        //RECSYS
        recsys_kmeans_euclideana1 = new RecSys(prueba_Kmeans_euclideana);
        recsys_knn_manhattan1 = new RecSys(prueba_knn_manhattan);

       // recsys_kmeans_euclideana2 = new RecSys(prueba_Kmeans_euclideana);
        //recsys_knn_manhattan2 = new RecSys(prueba_knn_manhattan);

        //LISTAS
        lista2.add(5.1); lista2.add(3.5);lista2.add(1.4); lista2.add(0.2);
        lista3.add(6.4);lista3.add(2.9); lista3.add(4.3); lista3.add(1.3);
        lista4.add(6.2); lista4.add(2.5); lista4.add(5.0); lista4.add(1.9);
        lista5.add(1211.0);lista5.add(1802.0);
        lista6.add(5233.0);lista6.add(7026.0);

        //TABLAS
        tabla = new Table(miles_dollars);
        tablawithlabels = new TableWithLabels(iris);

        //LISTA CANCIONES
        lista_canciones=nombre_canciones(canciones);

    }


    @Test
    void estimate() throws RowsLowerClustersException, TableNotTrainedException {
        //entrenamos antes
        prueba_Kmeans_manhattan.train(tabla);
        prueba_knn_manhattan.train(tablawithlabels);

        assertThrows(NullPointerException.class, ()-> recsys_knn_manhattan1.estimate(lista1));
        assertThrows(TableNotTrainedException.class, ()-> recsys_kmeans_euclideana1.estimate(lista1));

        //Entrenamos la que falta, y hacemos las estimaciones
        recsys_kmeans_euclideana1.train(tabla);
        //lista2 se aproxima al 0
        //lista3 se aproxima al 1
        //lista4 se aproxima al 2
        assertEquals(0,recsys_knn_manhattan1.estimate(lista2));
        assertEquals(1,recsys_knn_manhattan1.estimate(lista3));
        assertEquals(2,recsys_knn_manhattan1.estimate(lista4));
        //lista5 (probamos a meterle una fila de tamaño distinto a los de su tabla)
        assertThrows(IllegalArgumentException.class, ()->recsys_kmeans_euclideana1.estimate(lista2));
       //lista5 forma parte del 1 y lista6 del 3
        assertEquals(1,recsys_kmeans_euclideana1.estimate(lista5));
        assertEquals(3,recsys_kmeans_euclideana1.estimate(lista6));

    }

    @Test
    void train() throws RowsLowerClustersException {
        //comprobamos que se entrena bien
        assertDoesNotThrow(()-> prueba_Kmeans_euclideana.train(tabla));
        assertDoesNotThrow(()-> prueba_Kmeans_manhattan.train(tabla));
        assertDoesNotThrow(()-> prueba_knn_euclideana.train(tablawithlabels));
        assertDoesNotThrow(()-> prueba_knn_manhattan.train(tablawithlabels));
        //comprobamos que se lanza excepcion cuando clusters>rows
        assertThrows(RowsLowerClustersException.class, () -> kmeansclustersexception.train(tabla));
    }


    private List<String> nombre_canciones(String fileOfItemNames) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileOfItemNames));
        String line;
        List<String> names = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            names.add(line);
        }
        br.close();
        return names;
    }
}
