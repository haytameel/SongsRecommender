package es.uji.al426285.Modelo;

import es.uji.al426285.Algorithms.*;
import es.uji.al426285.Exceptions.NameNotFoundException;
import es.uji.al426285.Row.RowWithLabel;
import es.uji.al426285.Table.Table;
import es.uji.al426285.Table.TableWithLabels;
import es.uji.al426285.View.Vista;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Modelo {
    private Vista vista;

    public Modelo() throws Exception {
        tablawhitlables=new TableWithLabels(songs_test);
        tabla=new Table(songs_test_withoutnames);
        lista_generos=new LinkedList<>();
        makeLista_generos();
    }

    public void setVista(Vista vista){
        this.vista=vista;
    }
    RecSys recSys_knn;
    RecSys recSys_kmeans;
    String sep = System.getProperty("file.separator");

    //ficheros para entrenar:
    String songs_train = "src" + sep + "Files" + sep + "Datos_y_codigo" + sep + "recsys" + sep + "songs_files" + sep + "songs_train.csv";
    String songs_train_names =  "src" + sep + "Files" + sep + "Datos_y_codigo" + sep + "recsys" + sep + "songs_files" + sep + "songs_train_names.csv";
    String songs_train_withoutnames =  "src" + sep + "Files" + sep + "Datos_y_codigo" + sep + "recsys" + sep + "songs_files" + sep + "songs_train_withoutnames.csv";

    //ficheros para trabajar:
    String songs_test = "src" + sep + "Files" + sep + "Datos_y_codigo" + sep + "recsys" + sep + "songs_files" + sep + "songs_test.csv";
    String songs_test_names =  "src" + sep + "Files" + sep + "Datos_y_codigo" + sep + "recsys" + sep + "songs_files" + sep + "songs_test_names.csv";
    String songs_test_withoutnames =  "src" + sep + "Files" + sep + "Datos_y_codigo" + sep + "recsys" + sep + "songs_files" + sep + "songs_test_withoutnames.csv";

    Algorithm<TableWithLabels, Integer> knn;
    Algorithm<Table, Integer> kmeans;
    TableWithLabels tablawhitlables;
    Table tabla;

    public List<String> getLista_nombres_canciones() {
        return lista_nombres_canciones;
    }

    List<String> lista_nombres_canciones=leer_nombres_fichero(songs_test_names);

    public List<String> getLista_generos() {
        return lista_generos;
    }

    List<String> lista_generos;

    private void makeLista_generos(){
        Set<String> col=new TreeSet<>(tablawhitlables.getLabelsToIndex().keySet());
        for (String genero: col){
            lista_generos.add(genero);
        }
    }
    private List<String> leer_nombres_fichero(String fichero) throws FileNotFoundException {
        List<String> res=new ArrayList<>();
        Scanner sc=new Scanner(new File(fichero));
        while(sc.hasNext()){
            res.add(sc.nextLine());
        }
        return res;
    }

    public void create(Distancia distancia) throws Exception {
        TableWithLabels tableWithLabels=new TableWithLabels(songs_train);
        Table tabla=new Table(songs_train_withoutnames);
        knn = new KNN(distancia);
        kmeans = new Kmeans(tableWithLabels.getLabelsToIndex().size(), 2,1,distancia);
        recSys_knn=new RecSys(knn);
        recSys_kmeans=new RecSys(kmeans);
        recSys_knn.train(tableWithLabels);
        recSys_kmeans.train(tabla);
    }



    public void run() throws Exception {

        recSys_knn.run(tablawhitlables,lista_nombres_canciones);
        recSys_kmeans.run(tabla, lista_nombres_canciones);
    }
    public void recommend_songs_features(String cancion, int recomendaciones) throws NameNotFoundException {
        String e=vista.lista_canciones.getSelectionModel().getSelectedItem();
        List<String> sa=recSys_kmeans.recommend(cancion, recomendaciones);
        sa.remove(e);
        vista.getLista_recomendadas().getItems().addAll(sa);
    }

    public void recommend_guessed_genre(String cancion, int recomendaciones) throws NameNotFoundException {
        String e=vista.lista_canciones.getSelectionModel().getSelectedItem();
        List<String> sa=recSys_knn.recommend(cancion, recomendaciones);
        sa.remove(e);
        vista.getLista_recomendadas().getItems().addAll(sa);
    }

    public void añadir_canciones() {
        vista.getLista_canciones().getItems().clear();
        for (int i = 0; i < lista_nombres_canciones.size(); i++) {
                vista.getLista_canciones().getItems().add(lista_nombres_canciones.get(i));
        }
    }

    public void añadir_generos(){
        vista.getLista_canciones().getItems().clear();
        for (int i = 0; i < lista_generos.size(); i++) {
                vista.getLista_canciones().getItems().add(lista_generos.get(i));
        }
    }
    public void make_lista_acotada(String genero){
        vista.getLista_canciones().getItems().clear();
        List<RowWithLabel> lista=tablawhitlables.getLista();
        int grupo=tablawhitlables.getLabelsToIndex().get(genero);
        for (String cancion:recSys_knn.getMapa().keySet()){
            if (recSys_knn.getMapa().get(cancion)==grupo){
                vista.getLista_canciones().getItems().add(cancion);
            }
        }
    }



}
