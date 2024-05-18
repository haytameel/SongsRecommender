package es.uji.al426285.Reader;

import es.uji.al426285.Row.Row;
import es.uji.al426285.Row.RowWithLabel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVUnlabeledFileReader extends ReaderTemplate {
    private Scanner fichero;

    private Boolean usoEtiqueta;

    public CSVUnlabeledFileReader(String source) {
        super(source);
        this.usoEtiqueta=false;
    }

    protected void setUsoEtiqueta(Boolean usoEtiqueta) {
        this.usoEtiqueta = usoEtiqueta;
    }

    @Override
    protected void openSource(String source) throws FileNotFoundException {
        try {
            fichero = new Scanner(new File(source)); //Usar new Filereader();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("El fichero " + source + " no ha sido encontrado");
        }
    }

    @Override
    protected void processHeaders(String headers) throws Exception {
        List<String> header = new ArrayList<>();
        if (hasMoreData()) {
            String[] vector = headers.split(",");
            for (int i = 0; i < vector.length; i++) {
                header.add(vector[i]);
            }
        }
        super.getTabla().addHeader(header);
        comprobarEtiqueta();
    }

    private void comprobarEtiqueta() throws Exception {
        List<String> header = super.getTabla().getHeader();
        if (header.contains("class") && !this.usoEtiqueta) {
            throw new FileNotFoundException("Uso erroneo de un fichero con etiquetas");
        }
    }

    @Override
    protected void processData(String data) {
        List<Double> fila = new ArrayList<>();
        String[] vector = data.split(",");
        for (int i = 0; i < vector.length; i++) {
            try {
                double valor = Double.parseDouble(vector[i]);
                fila.add(valor);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("El fichero contiene valores erroneos");
            }
        }
        RowWithLabel linea = new RowWithLabel(fila,-1);//creamos la linea que almacena los doubles y la añadimos al la lista de rows
       // super.getTabla().addRowWithLabel((RowWithLabel) linea)
        super.getTabla().addRowWithLabel(linea);
    }

    @Override
    protected void closeSource() {
        fichero.close();
    }

    @Override
    protected boolean hasMoreData() {
        return fichero.hasNext();
    }

    @Override
    protected String getNextData() {
        return fichero.nextLine();
    }

    protected Scanner getFichero() {
        return fichero;
    }
}
