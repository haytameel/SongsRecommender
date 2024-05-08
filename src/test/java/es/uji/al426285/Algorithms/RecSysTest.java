package es.uji.al426285.Algorithms;

import es.uji.al426285.Exceptions.FunctionNotExecutedException;
import es.uji.al426285.Exceptions.NameNotFoundException;
import es.uji.al426285.Exceptions.RowsLowerClustersException;
import es.uji.al426285.Exceptions.TableNotTrainedException;
import es.uji.al426285.Table.Table;
import es.uji.al426285.Table.TableWithLabels;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecSysTest {
    String sep = System.getProperty("file.separator");
    String iris = "src" + sep + "Files" + sep + "iris.csv";
    String miles_dollars = "src" + sep + "Files" + sep + "miles_dollars.csv";
    Distancia ditancia_euclideana;
    Distancia distancia_manhattan;

    //KNN
    Algorithm<TableWithLabels, Integer> prueba_knn_euclideana;
    Algorithm<TableWithLabels, Integer> prueba_knn_manhattan;

    //KMEANS
    Algorithm<Table, Integer> prueba1_Kmeans_euclideana;
    Algorithm<Table, Integer> prueba2_Kmeans_manhattan;


    RecSys recsys_kmeans_euclideana1;
    RecSys recsys_kmeans_euclideana2;
    RecSys recsys_knn_manhattan1;
    RecSys recsys_knn_manhattan2;


    @BeforeEach
    void setUp() {
        ditancia_euclideana = new EuclideanDistance();
        distancia_manhattan = new ManhattanDistance();

        //KNN
        prueba_knn_euclideana = new KNN(ditancia_euclideana);
        prueba_knn_manhattan = new KNN(distancia_manhattan);

        //KMEANS
        prueba1_Kmeans_euclideana = new Kmeans(5, 5, 10000, ditancia_euclideana);
        prueba2_Kmeans_manhattan = new Kmeans(5, 5, 10000, distancia_manhattan);

        //RECSYS
        recsys_kmeans_euclideana1 = new RecSys(prueba1_Kmeans_euclideana);
        recsys_knn_manhattan1 = new RecSys(prueba_knn_manhattan);

        recsys_kmeans_euclideana2 = new RecSys(prueba1_Kmeans_euclideana);
        recsys_knn_manhattan2 = new RecSys(prueba_knn_manhattan);
    }


    @Test
    void estimate() {
            recsys_kmeans_euclideana1.estimate();
    }

    @Test
    void train() {
    }

    @Test
    void run() {
    }

    @Test
    void recommend() {
    }

    void estimate_ValidData_ReturnsInteger() throws TableNotTrainedException {
        // Verificar si el método estimate devuelve un entero válido para un conjunto de datos válido
        int result = recsys_kmeans_euclideana1.estimate(Arrays.asList(1.0, 2.0));
        assertEquals(/* resultado esperado */, result);
    }

    @Test
    void train_ValidData_NoExceptionThrown() {
        // Verificar si el método train no arroja excepciones con datos válidos
        assertDoesNotThrow(() -> recsys_kmeans_euclideana1.train(testData));
    }

    @Test
    void run_ValidData_NoExceptionThrown() throws RowsLowerClustersException {
        // Verificar si el método run no arroja excepciones con datos válidos
        recSys.train(testData); // Asegurar que el algoritmo está entrenado
        assertDoesNotThrow(() -> recSys.run(testData, testItemNames));
    }

    @Test
    void recommend_ValidName_ReturnsList() throws TableNotTrainedException, NameNotFoundException, RowsLowerClustersException {
        // Verificar si el método recommend devuelve una lista válida para un nombre válido
        recSys.train(testData); // Asegurar que el algoritmo está entrenado
        recSys.run(testData, testItemNames); // Asegurar que se ejecutó la función run
        List<String> recommendations = recSys.recommend("Item1", 1);
        assertNotNull(recommendations);
        assertFalse(recommendations.isEmpty());
    }

    @Test
    void recommend_InvalidName_ThrowsNameNotFoundException() throws TableNotTrainedException, RowsLowerClustersException {
        // Verificar si el método recommend arroja NameNotFoundException para un nombre no válido
        recSys.train(testData); // Asegurar que el algoritmo está entrenado
        recSys.run(testData, testItemNames); // Asegurar que se ejecutó la función run
        assertThrows(NameNotFoundException.class, () -> recSys.recommend("InvalidItem", 1));
    }

    @Test
    void recommend_FunctionNotExecuted_ThrowsFunctionNotExecutedException() {
        // Verificar si el método recommend arroja FunctionNotExecutedException si no se ha ejecutado el método run previamente
        assertThrows(FunctionNotExecutedException.class, () -> recSys.recommend("Item1", 1));
    }