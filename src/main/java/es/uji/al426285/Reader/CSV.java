package es.uji.al426285.Reader;

import es.uji.al426285.Row.Row;
import es.uji.al426285.Row.RowWithLabel;
import es.uji.al426285.Table.Table;
import es.uji.al426285.Table.TableWithLabels;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CSV {
        public Table readTable(String path){
            Table tabla=new Table();
            List<List> todo= leerFichero(path,false);
            tabla.addHeader(todo.get(0));
            tabla.addElements(todo.get(1));
            return tabla;
        }
        public TableWithLabels readTableWithLabels(String path){
            //0 header
            //1 lista de rows de valores
            //2 etiquetas

            //comprobar---------------- (ver si podemos hacer que el indice se asigne desde un metodo externo)
            TableWithLabels tabla=new TableWithLabels();
            List<List> todo=leerFichero(path,true);
            List<String> lista_header=todo.get(0);
            List<Row> lista_valores=todo.get(1);
            List<String> lista_etiquetas=todo.get(2);
            lista_header.add(lista_etiquetas.get(0));//al header le añadimos "class"
            lista_etiquetas.remove(0);//quitamos el termino class de la lista de etiquetas

            //AÑADIMOS LA CABECERA A LA TABLA
            tabla.addHeader(lista_header);

            //AÑADIMOS LOS VALORES JUNTO A SU CORRESPONDIENTE ETIQUETA
            //List<Row> lista_valores_etiquetas=new ArrayList<>();
            int cont=0;
            for(int i=0;i<lista_valores.size();i++){
                int indice=cont;
                String etiqueta_actual=lista_etiquetas.get(i);
                if (tabla.getLabelsToIndex().containsKey(etiqueta_actual)){
                    indice=tabla.getLabelsToIndex().get(etiqueta_actual);
                }
                else{
                    tabla.getLabelsToIndex().put(etiqueta_actual,cont++);
                }

                RowWithLabel nueva_linea=new RowWithLabel(lista_valores.get(i).getData(),indice);
                tabla.addRowWithLabel(nueva_linea);

            }
            return tabla;
        }



        public List<List> leerFichero(String path,boolean is_etiqueta){
            Scanner fichero=null;
            try {
                fichero = new Scanner(new File(path)); //Usar new Filereader();
            } catch (FileNotFoundException e) {
                System.out.println("El fichero " + path + " no ha sido encontrado");
                return null;
            }
            List<String> header=new LinkedList<>();
            List<String> etiqueta=new LinkedList<>();
            if (fichero!=null && fichero.hasNext()){
                String[] vector=fichero.nextLine().split(",");
                for (int i=0;i<vector.length;i++){
                    if (is_etiqueta && i==vector.length-1) {
                        etiqueta.add(vector[i]);
                    }
                    else {
                        header.add(vector[i]);
                    }
                }
            }

            List<Row> lista=new LinkedList<>();//lista de lineas
            while (fichero!=null && fichero.hasNext()){
                List<Double> fila= new LinkedList<>();
                String[] vector=fichero.nextLine().split(",");
                for (int i=0;i<vector.length;i++) {
                    if (etiqueta.size()>=1 && i==vector.length-1) {//si es un fichero con etiquetas
                        etiqueta.add(vector[i]);
                    }
                    else {
                        try{
                            double valor = Double.parseDouble(vector[i]);
                            fila.add(valor);
                        }
                        catch (NumberFormatException e){

                            System.out.println("El fichero contiene valores erroneos");
                            return null;
                        }
                    }

                }
                Row linea=new Row(fila);//creamos la linea que almacena los doubles y la añadimos al la lista de rows
                lista.add(linea);
            }
            List<List> dev=new ArrayList<>();
            dev.add(header);
            dev.add(lista);
            dev.add(etiqueta);
            return dev;//devolvemos una lista que contiene: los headers, la lista de lineas de doubles, etiquetas si es el caso
        }

}
