package es.uji.al426285.Algorithms;

import es.uji.al426285.Algorithms.Distancia;
import es.uji.al426285.Algorithms.Kmeans;
import es.uji.al426285.Exceptions.RowsLowerClustersException;
import es.uji.al426285.Exceptions.TableNotTrainedException;
import es.uji.al426285.Row.Row;
import es.uji.al426285.Row.RowWithLabel;
import es.uji.al426285.Table.Table;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KmeansTest {
    @Test
    void testEntrenamientoExitoso() {
        // Arrange
        int numClusters = 3;
        int numIterators = 1;
        long seed = 123456789;
        List<RowWithLabel> data = Arrays.asList(
                new RowWithLabel(Arrays.asList(1.0, 2.0), 0),
                new RowWithLabel(Arrays.asList(1.5, 1.8), 0),
                new RowWithLabel(Arrays.asList(5.0, 8.0), 1),
                new RowWithLabel(Arrays.asList(8.0, 8.0), 1),
                new RowWithLabel(Arrays.asList(1.0, 0.6), 0),
                new RowWithLabel(Arrays.asList(9.0, 11.0), 2),
                new RowWithLabel(Arrays.asList(8.0, 2.0), 1),
                new RowWithLabel(Arrays.asList(10.0, 2.0), 2),
                new RowWithLabel(Arrays.asList(9.0, 3.0), 2)
        );
        Table tabla = new Table();
        tabla.addElements(data);
        Kmeans kmeans = new Kmeans(numClusters, numIterators, seed, new EuclideanDistance());

        // Act & Assert
        assertDoesNotThrow(() -> {
            kmeans.train(tabla);
        });
    }

    @Test
    void testExcepcionNumeroClustersInsuficiente() {
        // Arrange
        int numClusters = 10; // Número mayor que el número de filas en los datos
        int numIterators = 1;
        long seed = 123456789;
        List<RowWithLabel> data = Arrays.asList(
                new RowWithLabel(Arrays.asList(1.0, 2.0), 0),
                new RowWithLabel(Arrays.asList(1.5, 1.8), 0)
        );
        Table tabla = new Table();
        tabla.addElements(data);
        Kmeans kmeans = new Kmeans(numClusters, numIterators, seed, new EuclideanDistance());

        // Act & Assert
        assertThrows(RowsLowerClustersException.class, () -> {
            kmeans.train(tabla);
        });
    }

    @Test
    void testAgrupamiento() throws TableNotTrainedException, RowsLowerClustersException {
        // Arrange
        int numClusters = 3;
        int numIterators = 1;
        long seed = 123456789;
        List<RowWithLabel> data = Arrays.asList(
                new RowWithLabel(Arrays.asList(1.0, 2.0), 0),
                new RowWithLabel(Arrays.asList(1.5, 1.8), 0),
                new RowWithLabel(Arrays.asList(5.0, 8.0), 1),
                new RowWithLabel(Arrays.asList(8.0, 8.0), 1),
                new RowWithLabel(Arrays.asList(1.0, 0.6), 0),
                new RowWithLabel(Arrays.asList(9.0, 11.0), 2),
                new RowWithLabel(Arrays.asList(8.0, 2.0), 1),
                new RowWithLabel(Arrays.asList(10.0, 2.0), 2),
                new RowWithLabel(Arrays.asList(9.0, 3.0), 2)
        );
        Table tabla = new Table();
        tabla.addElements(data);
        Kmeans kmeans = new Kmeans(numClusters, numIterators, seed, new EuclideanDistance());
        kmeans.train(tabla);

        //los centroides calculados: {{9,2'3},{8'5,9'5},{2'125,3'1}}

        // se aproxima al segundo, deberia devolver 2
        ArrayList<Double> prueba1=new ArrayList<>();
        prueba1.add(0.5);prueba1.add(1.5);
        Integer cluster1 = kmeans.estimate(prueba1);

        // se aproxima al primero, deberia devolver 1
        ArrayList<Double> prueba2=new ArrayList<>();
        prueba2.add(20.0);prueba2.add(19.5);
        Integer cluster2 = kmeans.estimate(prueba2);

        // se aproxima al primero, deberia devolver 0
        ArrayList<Double> prueba3=new ArrayList<>();
        prueba3.add(8.2);prueba3.add(2.5);
        Integer cluster3 = kmeans.estimate(prueba3);


        // Assert
        assertEquals(2, cluster1); // El índice de los clústeres empieza en 0
        assertEquals(1, cluster2); // El índice de los clústeres empieza en 0
        assertEquals(0, cluster3); // El índice de los clústeres empieza en 0


    }
}

/*package es.uji.al426285.Algorithms;

import es.uji.al426285.Exceptions.RowsLowerClustersException;
import es.uji.al426285.Exceptions.TableNotTrainedException;
import es.uji.al426285.Table.Table;
import es.uji.al426285.Table.TableWithLabels;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//sin etiquetas

class KmeansTest {
    String sep = System.getProperty("file.separator");
    String iris = "src"+sep+"Files"+sep+"iris.csv";
    String miles_dollars = "src"+sep+"Files"+sep+"miles_dollars.csv";
    Table tabla1;
    Table tabla2;
    Table tabla3;
    TableWithLabels tablaWithLabels1;
    Kmeans kexception;
    Kmeans k1;

    void crear() throws Exception {
        tabla2 = new Table(miles_dollars);

        tablaWithLabels1 = new TableWithLabels(miles_dollars);
        kexception = new Kmeans(155, 5, 10000, new EuclideanDistance());

        k1 = new Kmeans(5, 5, 10000, new EuclideanDistance());
    }

    @Test
    void train() throws Exception {
        crear();
        assertThrows(RowsLowerClustersException.class, () -> kexception.train(tabla2));
    }
    @Test
    void estimate() throws Exception {
        crear();
        assertThrows(TableNotTrainedException.class, () -> k1.estimate(tabla2.getLista().get(0).getData()));

    }

    @Test
    void calcularRepresentantes() throws RowsLowerClustersException, Exception {
        crear();
        k1.train(tabla2);
    }
}
*/
