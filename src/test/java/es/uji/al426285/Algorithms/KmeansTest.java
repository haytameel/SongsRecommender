package es.uji.al426285.Algorithms;

import es.uji.al426285.Exceptions.RowsLowerClustersException;
import es.uji.al426285.Exceptions.TableNotTrainedException;
import es.uji.al426285.Table.Table;
import es.uji.al426285.Table.TableWithLabels;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//sin etiquetas

class KmeansTest {
    Table tabla1;
    Table tabla2;
    Table tabla3;
    TableWithLabels tablaWithLabels1;
    Kmeans kexception;
    Kmeans k1;

    void crear() throws Exception {
        tabla2 = new Table("src/main/java/es/uji/al426285/Files/miles_dollars.csv");

        tablaWithLabels1 = new TableWithLabels("src/main/java/es/uji/al426285/Files/iris.csv");
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