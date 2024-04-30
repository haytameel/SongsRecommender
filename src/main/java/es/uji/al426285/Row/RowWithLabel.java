package es.uji.al426285.Row;

import java.util.ArrayList;
import java.util.List;

public class RowWithLabel extends Row {
    private List<Double> linea;
    private int number_class;

    public RowWithLabel() {
        this.linea = new ArrayList<>();
        this.number_class = -1;
    }

    public RowWithLabel(List<Double> linea, int numero) {
        this.linea = linea;
        this.number_class = numero;
    }

    public int getNumber_class() {
        return number_class;
    }

    public List<Double> getData() {
        return linea;
    }

    @Override
    public String toString() {
        String rowWithLabel = "";
        for (Double valor : linea) {
            rowWithLabel += valor + ", ";
        }
        rowWithLabel += getNumber_class();

        return rowWithLabel;
    }

}