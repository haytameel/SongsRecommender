package es.uji.al426285.Controlador;


import es.uji.al426285.Algorithms.EuclideanDistance;
import es.uji.al426285.Algorithms.ManhattanDistance;
import es.uji.al426285.Exceptions.NameNotFoundException;
import es.uji.al426285.Modelo.Modelo;
import es.uji.al426285.View.Vista;

import java.util.List;

public class Controlador {
    private Modelo modelo;
    private Vista vista;

    public Controlador() throws Exception {
        modelo = new Modelo();
    }
    public void setVista(Vista vista){
        this.vista=vista;
    }
    public void setModelo(Modelo modelo){
        this.modelo=modelo;
    }
    public void añadir_canciones() {
        modelo.añadir_canciones();
    }

    public void añadir_generos() {
        modelo.añadir_generos();
    }


    //USAR ESTO: etiqueta_recomendacion
    public void entrenar() throws Exception {
        if (vista.getEuclidean().isSelected()){
            modelo.create(new EuclideanDistance());
        }
        else{
            modelo.create(new ManhattanDistance());
        }
        modelo.run();
    }
    public void recomendar() throws Exception {
        if (vista.getSong_features().isSelected()){
            modelo.recommend_songs_features(vista.lista_canciones.getSelectionModel().getSelectedItem(),vista.getEtiqueta_flechitas().getValue());
        } else if (vista.getGuessed_genre().isSelected()) {
            modelo.recommend_guessed_genre(vista.lista_canciones.getSelectionModel().getSelectedItem(),vista.getEtiqueta_flechitas().getValue());
        }
    }

    public void modificar_etiqueta_recomendacion(){
        vista.getEtiqueta_recomendacion().setText("If you liked \""+vista.lista_canciones.getSelectionModel().getSelectedItem()+"\"you might like the following songs");
    }



}
