package es.uji.al426285.Table;

import es.uji.al426285.Reader.CSVLabeledFileReader;
import es.uji.al426285.Row.Row;
import es.uji.al426285.Row.RowWithLabel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableWithLabels extends Table{

    private Map<String, Integer> labelsToIndex;//etiquetas-numero_correspondiente
    private List<String> header;
    private List<RowWithLabel> lista;

   // private TableWithLabels tabla;
    public TableWithLabels() {
        lista=new ArrayList<>();
        this.lista=new ArrayList<>();
        labelsToIndex = new HashMap<>();
    }
    public TableWithLabels(String path) throws Exception {
        TableWithLabels g = new CSVLabeledFileReader(path).readTableFromSource();
        this.header = g.getHeader(); // No hace falta asignar el header, ya está hecho en el constructor de la superclase
        this.lista = g.getListaWithLabels();
        this.labelsToIndex = g.getLabelsToIndex();
        System.out.println("messi:"+lista.toString());
        System.out.println("mesii2:"+g.getListaWithLabels());
    }

    public void addRowWithLabel(RowWithLabel fila) {
        lista.add(fila);
    }

    @Override
    public RowWithLabel getRowAt(int row) {
        return lista.get(row);
    }

    public Map<String, Integer> getLabelsToIndex() {
        return labelsToIndex;
    }
    public Integer addLabel(String etiqueta){
        if (labelsToIndex.containsKey(etiqueta)) {
            return labelsToIndex.get(etiqueta);
        }
        Integer dev= labelsToIndex.put(etiqueta, labelsToIndex.size());
        return labelsToIndex.get(etiqueta);
    }

    public List<RowWithLabel> getListaWithLabels(){
        List<RowWithLabel> lista= new ArrayList<>();
        for (Row linea: super.getLista()){
            lista.add((RowWithLabel) linea);
        }
        System.out.println("aquiii: "+this.lista.toString());
        return this.lista=lista;
    }

    public String headerToString(){
        String string=header.get(0);
        for (int i=1;i<header.size();i++){
            string=string+","+header.get(i);
        }
        return string;
    }
    public String listaToString(){
        String string="";
        for (int i=0; i<lista.size();i++){
            RowWithLabel linea=lista.get(i);
            String lin=linea.getData().get(0).toString();
            for (int j=1;i<linea.getData().size();i++){
                lin=lin+linea.getData().get(j);
            }
            string=string+lin;
        }
        return string;
    }

}


