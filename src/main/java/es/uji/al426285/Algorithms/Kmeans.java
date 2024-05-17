package es.uji.al426285.Algorithms;

import es.uji.al426285.Exceptions.RowsLowerClustersException;
import es.uji.al426285.Exceptions.TableNotTrainedException;
import es.uji.al426285.Row.Row;
import es.uji.al426285.Table.Table;
import es.uji.al426285.Table.TableWithLabels;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.round;

public class Kmeans implements Algorithm<Table, Integer> {


    Table tabla;
    int numClusters;
    int numIterators;
    long seed;
    private List<Row> centroides;
    Distancia distance;

    public TableWithLabels getTabla() {
        return null;
    }

    public Kmeans(int numClusters, int numIterators, long seed, Distancia distance){
        this.numClusters=numClusters;
        this.numIterators=numIterators;
        this.seed=seed;
        this.centroides = new ArrayList<>();
        this.distance=distance;
    }

    public void train(Table datos) throws RowsLowerClustersException {
        this.tabla=datos;
        if (numClusters> tabla.getLista().size()){
            throw new RowsLowerClustersException();
        }
        calcularRepresentantes();

        for (int i=0; i<numIterators; i++){
            List<List<Row>> grupos=asignarGrupo();
            calcularCentroide(grupos);
        }
        asignarGrupo();
    }
    private List<Row> calcularRepresentantes(){
        // Paso 1: Elegir aleatoriamente un representante por cada grupo
        List<Row> repre = new ArrayList<>();
        Random semilla=new Random(seed);
        for (int i=0; i<numClusters; i++) {
            int num=Math.abs(semilla.nextInt()%tabla.getLista().size());
            repre.add(tabla.getRowAt(num));
      //      System.out.println("representanteeee: "+tabla.getRowAt(num));
        }
        centroides= repre;
        return repre;
    }
    private List<Row> calcularCentroide(List<List<Row>> grupos){
        List<Row> centroides=new ArrayList<>();
        int ncomp=tabla.getRowAt(0).getData().size();
        for (int i=0; i<grupos.size(); i++){//i es cada grupo de los x grupos que tenemos
            List<Double> sumatorio=new ArrayList<>(ncomp);
            for (int k=0; k<ncomp; k++){
                sumatorio.add(0.0);
            }
            for (int j=0; j<grupos.get(i).size(); j++) {//j es cada row de ese grupo
                for (int k = 0; k < ncomp; k++) {//k son los columnas/componentes de cada row
                    sumatorio.set(k, sumatorio.get(k) + grupos.get(i).get(j).getData().get(k));
                    //añadimos en la columna de cada grupo el valor de la suma de todos los rows
                }
            }
            int m=grupos.get(i).size();//numero de rows en cada grupo
            List<Double> centroide=new ArrayList<Double>(ncomp);
            for (int k=0; k<ncomp; k++){//hacemos la division por m y lo añadimos
                double valor=sumatorio.get(k)/m;
                double roundedNumber = Math.round(valor * 100) / 100.0;
                centroide.add(k,roundedNumber);
            }
            centroides.add(new Row(centroide)) ;
        }
        this.centroides=centroides;
        return centroides;
    }
    private List<List<Row>> asignarGrupo(){
        List<List<Row>> res=new ArrayList<>();
        for (int i=0; i<numClusters; i++){
            List<Row> fila=new ArrayList<>();
            res.add(fila);
        }

        for (Row fila: tabla.getLista()) {
            int pertenece = nueva_asignacion(fila);
            res.get(pertenece).add(fila);
        }
        return res;
    }
    private Integer nueva_asignacion(Row fila) {
        int pertenece = 0;
        double menorDistancia = distance.calculateDistance(fila.getData(), centroides.get(0).getData());

        for (int i = 1; i < numClusters; i++) {
            double distancia = distance.calculateDistance(fila.getData(), centroides.get(i).getData());
            if (distancia < menorDistancia) {
                pertenece = i;
                menorDistancia = distancia;
            }
        }
        return pertenece;
    }


    public Integer estimate(List<Double> dato) throws TableNotTrainedException {
        if (tabla == null) {
            throw new TableNotTrainedException("No se ha usado el metodo train. Llama al método train() primero.");
        }
        Row n=new Row(dato);
        return nueva_asignacion(n);
    }
}

