package es.uji.al426285.Row;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private List<Double> linea;
    public Row(){
        linea=new ArrayList<>();
    }

    public Row(List<Double> linea){
        this.linea=linea;
    }

    public List<Double> getData(){
        return linea;
    }
    public List<Double> getLinea(){
        return linea;
    }

    @Override
    public String toString(){
        String row="";
        for (Double valor:linea){
            row+= valor+", ";
        }
       return row.substring(0,row.length()-2);
    }
}
