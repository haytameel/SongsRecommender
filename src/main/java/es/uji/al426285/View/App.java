package es.uji.al426285.View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


/**
 * JavaFX App
 */
public class App extends Application {
    //TIPO DE RECOMENDACIÓN
    private ToggleGroup recommendation_type = new ToggleGroup(); // Grupo para los RadioButton
    private RadioButton song_features = new RadioButton("Recommend based on song features");
    private RadioButton guessed_genre = new RadioButton("Recommend based on guessed genre");
    //TIPO DE DISTANCIA
    private ToggleGroup distance_type = new ToggleGroup(); // Grupo para los RadioButton
    private RadioButton euclidean = new RadioButton("Euclidean");
    private RadioButton manhattan = new RadioButton("Manhattan");
    //LISTA QUE CONTENDRÁ LAS CANCIONES
    private ListView<String> lista_canciones = new ListView<>();
    //BOTON RECOMENDAR
    private Button boton_recomendar = new Button("Recommend...");
    //LISTA QUE DEVOLVERÁ LAS CANCIONES RECOMENDADAS
    private ListView<String> lista_recomendadas = new ListView<>();
    //ETIQUETA DE LA RECOMENDACIÓN
    Label etiqueta_recomendacion = new Label();
    //BOTON CERRAR RECOMENDACIONES
    private Button boton_cerrar_recomendaciones = new Button("Close");


    @Override
    public void start(Stage stage) throws FileNotFoundException {
        //icono de la ventana principal
        InputStream entrada = new FileInputStream("src/main/java/es/uji/al426285/View/imagen.png");
        Image imagen = new Image(entrada);
        ImageView imagen_vista = new ImageView();
        stage.getIcons().add(imagen);

        //nombre de ventana
        stage.setTitle("Recomendacion de canciones");

        //Caja donde irán, tipo de recomendacion, tipo de distancia y titulos de canciones
        VBox vbox = vista();

        //Insertamos la caja vertical en la escena
        var scene = new Scene(new StackPane(vbox), 640, 480);
        stage.setScene(scene);//insertamos la escena en el escenario
        stage.show();


      ventana2();
    }


    private VBox vista() {
        //hay que asignar los radiobuttons a su grupo
        song_features.setToggleGroup(recommendation_type);
        guessed_genre.setToggleGroup(recommendation_type);

        manhattan.setToggleGroup(distance_type);
        euclidean.setToggleGroup(distance_type);

        Label recomendation_label = new Label("Recomendation Type");
        recomendation_label.setFont(Font.font("System", FontWeight.BOLD, 20));

        Label distance_label = new Label("Distance Type");
        distance_label.setFont(Font.font("System", FontWeight.BOLD, 20));

        Label songs_label = new Label("Songs Titles");
        songs_label.setFont(Font.font("System", FontWeight.BOLD, 35));

        //creamos la caja vertical
        VBox vbox = new VBox(recomendation_label, song_features, guessed_genre,
                distance_label, euclidean, manhattan,
                songs_label, lista_canciones,
                boton_recomendar);
        vbox.setSpacing(6);
        vbox.setPadding(new Insets(10, 10, 10, 10));

        aplicarEstiloBoton(boton_recomendar);//aplicamos estilo a nuestro boton recomendar

//NO ES NECESARIO
// ScrollPane barra_desplazamiento=new ScrollPane(lista_canciones);
//        barra_desplazamiento.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
//        barra_desplazamiento.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);


        return vbox;
    }

    private void ventana2() {
        Label etiqueta_num_recomendaciones = new Label("Number of recomendations");
        Spinner<Integer> etiqueta_flechitas = new Spinner<>();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        etiqueta_flechitas.setValueFactory(valueFactory);
        FlowPane flow1 = new FlowPane(etiqueta_num_recomendaciones, etiqueta_flechitas);
        flow1.setHgap(10);

        VBox vbox = new VBox(flow1,
                etiqueta_recomendacion, lista_recomendadas, boton_cerrar_recomendaciones

        );
        aplicarEstiloBoton(boton_cerrar_recomendaciones);
        vbox.setSpacing(6);
        vbox.setPadding(new Insets(10, 10, 10, 10));

        Stage segundaVentana = new Stage();
        Scene scene = new Scene(new StackPane(vbox), 400, 300);
        segundaVentana.setScene(scene);
        segundaVentana.setTitle("Recommended titles");
        segundaVentana.show();
    }


    private void aplicarEstiloBoton(Button boton) {
        boton.setStyle("-fx-background-color:\n"
                + "        #090a0c,\n"
                + "        linear-gradient(#38424b 0%, #1f2429 20%, #6ba1e7 100%),\n"
                + "        linear-gradient(#20262b, #191d22),\n"
                + "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n"
                + "    -fx-background-radius: 5,4,3,5;\n"
                + "    -fx-background-insets: 0,1,2,0;\n"
                + "    -fx-text-fill: white;\n"
                + "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n"
                + "    -fx-font-family: \"Arial\";\n"
                + "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n"
                + "    -fx-font-size: 12px;\n"
                + "     -fx-background-radius: 35px;\n "
                + "    -fx-padding: 10 20 10 20;");
//        boton.setCancelButton(true);
//        boton.borderProperty(Border.EMPTY);
    }

    public static void main(String[] args) {
        launch();
    }

}