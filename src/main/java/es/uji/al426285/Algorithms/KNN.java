package es.uji.al426285.Algorithms;


import es.uji.al426285.Row.RowWithLabel;
import es.uji.al426285.Table.TableWithLabels;

import java.util.List;

public class KNN implements Algorithm<TableWithLabels,Integer> {


    TableWithLabels tabla;
    Distancia distance;
    public KNN(Distancia distance){
        this.distance=distance;
    }
    public void train(TableWithLabels tabla) {
        this.tabla = tabla;
    }

    public Integer estimate(List<Double> data){
        TableWithLabels aux=tabla;
        double menor_distancia= Double.MAX_VALUE;
        Integer candidato=null;

        if (aux==null || data==null) throw new NullPointerException("Tabla/Lista vacia");
        if (data.isEmpty()) return candidato;//si la lista esta vacia devolvemos null
        for (RowWithLabel fila_aux: aux.getLista()){//recorremos la lista de filas de la tabla aux
            //calculamos las distancia
            //entre las listas de la tabla y la lista que tenemos como parametros
            double distancia= distance.calculateDistance(data,fila_aux.getData());

            if(distancia<menor_distancia){//si es menor, actualizamos la distancia y tenemos un nuevo candidato
                candidato= fila_aux.getNumber_class();
                menor_distancia=distancia;
            }
        }
        return candidato;
    }



}
