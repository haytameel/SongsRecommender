package es.uji.al426285.Reader;

import es.uji.al426285.Row.RowWithLabel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CSVLabeledFileReader extends CSVUnlabeledFileReader{
    public CSVLabeledFileReader(String source) {
        super(source);
        super.setUsoEtiqueta(true);
    }
    @Override
    protected void openSource(String source) throws FileNotFoundException {
       super.openSource(source);
       super.setUsoEtiqueta(true);
    }

    @Override
    protected void processHeaders(String headers)throws Exception {
       super.processHeaders(headers);
    }

    @Override
    protected void processData(String data) {
        String etiqueta="";
        List<Double> fila= new ArrayList<>();
        String[] vector=data.split(",");
        for (int i=0;i<vector.length-1;i++) {
            try{
                double valor = Double.parseDouble(vector[i]);
                fila.add(valor);
            }
            catch (NumberFormatException e){
                throw new NumberFormatException("El fichero contiene valores erroneos");
            }
        }
        etiqueta=vector[vector.length-1];
        Integer e=super.getTabla().addLabel(etiqueta);
        RowWithLabel linea=new RowWithLabel(fila,e);//creamos la linea que almacena los doubles y la añadimos al la lista de rows

        super.getLista_rows().add(linea);
        super.getTabla().addLabel(etiqueta);
    }
    @Override
    protected boolean hasMoreData() {
        return super.hasMoreData();
    }
    @Override
    protected void closeSource() {
        super.closeSource();
    }

    @Override
    protected String getNextData() {
        return super.getNextData();
    }
}
