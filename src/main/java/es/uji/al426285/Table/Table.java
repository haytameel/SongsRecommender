package es.uji.al426285.Table;

import es.uji.al426285.Reader.CSVUnlabeledFileReader;
import es.uji.al426285.Row.Row;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<String> header;
    private List<Row> lista;

    public Table() {
        this.header = new ArrayList<>();
        this.lista = new ArrayList<>();
    }

    public Table(String path) throws Exception {
        Table g;

        try {
            g= new CSVUnlabeledFileReader(path).readTableFromSource();
        }catch (Exception e){
            throw new Exception("Error al leer el archivo");
        }
        this.header = g.getHeader();
        this.lista = g.getLista();
    }

    public Row getRowAt(int row) {
        return lista.get(row);
    }

    public boolean addHeader(List<String> header) {
        if (header == null) {
            return false;
        }
        this.header = header;
        return true;
    }

    public boolean addElements(List<Row> lista) {
        if (lista == null) {
            return false;
        }
        this.lista = lista;
        return true;
    }

    public List<String> getHeader() {
        return header;
    }

    public List<Row> getLista() {
        return lista;
    }
    public String headerToString() {
        String string = header.get(0);
        for (int i = 1; i < header.size(); i++) {
            string = string + "," + header.get(i);
        }
        return string;
    }
}
