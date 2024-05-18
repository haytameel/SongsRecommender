package es.uji.al426285.Controlador;


import es.uji.al426285.Algorithms.EuclideanDistance;
import es.uji.al426285.Algorithms.ManhattanDistance;
import es.uji.al426285.Modelo.Modelo;
import es.uji.al426285.View.Vista;

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
    public void anadir_canciones() {
        modelo.anadir_canciones();
    }

    public void anadir_generos() {
        modelo.anadir_generos();
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
        vista.getLista_recomendadas().getItems().clear();
        if(vista.getEtiqueta_flechitas().getValue()!=0) {
            if (vista.getSong_features().isSelected()) {
                modelo.recommend_songs_features(vista.lista_canciones.getSelectionModel().getSelectedItem(), vista.getEtiqueta_flechitas().getValue());
            } else if (vista.getGuessed_genre().isSelected()) {
                modelo.recommend_guessed_genre(vista.lista_canciones.getSelectionModel().getSelectedItem(), vista.getEtiqueta_flechitas().getValue());
            }
        }
    }

    public void modificar_etiqueta_recomendacion(){
        vista.getEtiqueta_recomendacion().setText("If you liked \""+vista.lista_canciones.getSelectionModel().getSelectedItem()+"\"you might like the following songs");
    }
    public void getCanciones(){
        modelo.make_lista_acotada(vista.lista_canciones.getSelectionModel().getSelectedItem());
    }




}
