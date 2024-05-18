package es.uji.al426285.Row;

import java.util.ArrayList;
import java.util.List;

public class RowWithLabel extends Row {
    private int number_class;

    public RowWithLabel() {
        super();
        this.number_class = -1;
    }

    public RowWithLabel(List<Double> linea, int numero) {
        super(linea);
        this.number_class = numero;
    }

    public int getNumber_class() {
        return number_class;
    }

    public List<Double> getData() {
        return super.getData();
    }

    @Override
    public String toString() {
        String rowWithLabel = "";
        for (Double valor : super.getLinea()) {
            rowWithLabel += valor + ", ";
        }
        rowWithLabel += getNumber_class();

        return rowWithLabel;
    }

}