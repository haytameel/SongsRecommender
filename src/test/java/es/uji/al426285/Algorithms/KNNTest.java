package es.uji.al426285.Algorithms;

import es.uji.al426285.Table.TableWithLabels;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KNNTest {
    String sep = System.getProperty("file.separator");
    String iris = "src"+sep+"Files"+sep+"iris.csv";
    TableWithLabels prueba;
    KNN knn=new KNN(new EuclideanDistance());

    List<Double> lista1=null;//lista null
    List<Double> lista2=new ArrayList<>();//iris-setosa (0)
    List<Double> lista3=new ArrayList<>();//Iris-versicolor (1)
    List<Double> lista4=new ArrayList<>();//Iris-virginica (2)
    List<Double> lista5=new ArrayList<>();//lista vacia
    public void asignar_valores() throws Exception {
        prueba= new TableWithLabels(iris);
        lista2.add(5.1);lista2.add(3.5);lista2.add(1.4);lista2.add(0.2);
        lista3.add(6.4); lista3.add(2.9); lista3.add(4.3); lista3.add(1.3);
        lista4.add(6.2); lista4.add(2.5); lista4.add(5.0); lista4.add(1.9);
        knn.train(prueba);
    }

    @Test
    void estimate() throws Exception {
        asignar_valores();
        assertThrows(NullPointerException.class, () -> knn.estimate(lista1));//si lista vacia es null, comprobar que lanza la excepcion
        assertEquals(0,knn.estimate(lista2));
        assertEquals(1,knn.estimate(lista3));
        assertEquals(2,knn.estimate(lista4));
        assertNull(knn.estimate(lista5));//usar assertnull en lugar de assertequals

    }
}