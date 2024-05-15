package es.uji.al426285.Controlador;


import es.uji.al426285.Algorithms.EuclideanDistance;
import es.uji.al426285.Algorithms.ManhattanDistance;
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
    public void euclidean() throws Exception {
        modelo.create(new EuclideanDistance());
    }
    public void manhattan() throws Exception {
        modelo.create(new ManhattanDistance());
    }

    public void entrenar() {

    }



}
