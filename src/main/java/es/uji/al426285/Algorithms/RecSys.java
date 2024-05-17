package es.uji.al426285.Algorithms;

import es.uji.al426285.Exceptions.FunctionNotExecutedException;
import es.uji.al426285.Exceptions.NameNotFoundException;
import es.uji.al426285.Exceptions.RowsLowerClustersException;
import es.uji.al426285.Exceptions.TableNotTrainedException;
import es.uji.al426285.Table.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecSys implements Algorithm<Table, Integer> {
    private Algorithm<Table, Integer> algorithm;

    private Map<String, Integer> mapa;

    public Map<String, Integer> getMapa() {
        return mapa;
    }

    public RecSys(Algorithm algorithm) {
        this.algorithm = algorithm;
        this.mapa = new HashMap<>();
    }

    public Integer estimate(List<Double> dato) throws TableNotTrainedException {
        return algorithm.estimate(dato);
    }
    @Override
    public void train(Table datos) throws RowsLowerClustersException {
        algorithm.train(datos);
    }

    public void run(Table testData, List<String> testItemNames) throws TableNotTrainedException {
        try {
            for (int i = 0; i < testData.getLista().size(); i++) {
                Integer agrupacion = algorithm.estimate(testData.getLista().get(i).getData());
                mapa.put(testItemNames.get(i), agrupacion);
            }
        } catch (Exception e) {
            throw new TableNotTrainedException("No se ha entrenado el algoritmo");
        }
    }

    ;

    public List<String> recommend(String nameLikedItem, int numRecommendations) throws NameNotFoundException {
        List<String> recomendaciones = new ArrayList<>();
        Integer grupo = -1;
        if (mapa.isEmpty()) {
            throw new FunctionNotExecutedException("run()");
        }
        try {
            grupo = mapa.get(nameLikedItem);
        } catch (Exception e) {
            throw new NameNotFoundException("Nombre no encontrado en el Algoritmo");
        }
        int cont = 0;
        for (String key : mapa.keySet()) {
            if (mapa.get(key) == grupo) {
                cont++;
                recomendaciones.add(key);
                if (cont >= numRecommendations) {
                    return recomendaciones;
                }
            }
        }
        return recomendaciones;
    }
}
