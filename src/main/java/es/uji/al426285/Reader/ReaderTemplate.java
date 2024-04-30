package es.uji.al426285.Reader;

import es.uji.al426285.Row.Row;
import es.uji.al426285.Table.TableWithLabels;

import java.io.FileNotFoundException;
import java.util.List;

public abstract class ReaderTemplate {
    private String source;
    private TableWithLabels tabla;

    public List<Row> getLista_rows() {
        return tabla.getLista();
    }

    public String getSource() {
        return source;
    }

    public TableWithLabels getTabla() {
        return tabla;
    }

    protected ReaderTemplate(String source) {
        this.source = source;
        this.tabla = new TableWithLabels();
    }

    abstract void openSource(String source) throws FileNotFoundException;

    abstract void processHeaders(String headers) throws Exception;

    abstract void processData(String data);

    abstract void closeSource();

    abstract boolean hasMoreData(); // comprueba si hay más datos; en nuestro caso, si hay mas línea(s) en el fichero CSV

    abstract String getNextData(); // obtener el siguiente dato; una línea del fichero CSV en nuestro caso

    public final TableWithLabels readTableFromSource() throws FileNotFoundException,Exception {
        openSource(source);
        processHeaders(getNextData());
        while (hasMoreData()) {
            processData(getNextData());
        }
        closeSource();
        return tabla;
    }

}
