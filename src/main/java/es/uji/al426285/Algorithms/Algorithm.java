package es.uji.al426285.Algorithms;


import es.uji.al426285.Exceptions.RowsLowerClustersException;
import es.uji.al426285.Exceptions.TableNotTrainedException;
import es.uji.al426285.Table.Table;

import java.util.List;

public interface Algorithm<T extends Table, R> {
    public void train(T datos) throws RowsLowerClustersException;

    public R estimate(List<Double> dato) throws TableNotTrainedException;
}

