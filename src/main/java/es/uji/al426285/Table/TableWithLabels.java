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

   // private TableWithLabels tabla;
   public TableWithLabels() {
       super();
       labelsToIndex = new HashMap<>();
   }
    public TableWithLabels(String path) throws Exception {
       super();
        TableWithLabels g = new CSVLabeledFileReader(path).readTableFromSource();
        super.addHeader(g.getHeader()); // No hace falta asignar el header, ya está hecho en el constructor de la superclase
        super.addElements(g.getLista());
        this.labelsToIndex = g.labelsToIndex;
    }

    public void addRowWithLabel(RowWithLabel fila) {
        super.getLista().add(fila);
    }

    @Override
    public RowWithLabel getRowAt(int row) {
        return super.getLista().get(row);
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
    @Override
    public List<RowWithLabel> getLista() {
        return super.getLista();
    }

}


