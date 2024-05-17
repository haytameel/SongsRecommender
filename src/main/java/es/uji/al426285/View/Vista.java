package es.uji.al426285.View;

import es.uji.al426285.Controlador.Controlador;
import es.uji.al426285.Modelo.Modelo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * JavaFX Vista
 */
public class Vista extends Application {
    private Modelo modelo;
    private Controlador controlador;

    public Vista() throws Exception {
        modelo = new Modelo();
        modelo.setVista(this);
        controlador = new Controlador();
        controlador.setVista(this);
        controlador.setModelo(modelo);
    }

    //TIPO DE RECOMENDACIÓN
    private ToggleGroup recommendation_type = new ToggleGroup(); // Grupo para los RadioButton
    private RadioButton song_features = new RadioButton("Recommend based on song features");
    private RadioButton guessed_genre = new RadioButton("Recommend based on guessed genre");
    //TIPO DE DISTANCIA
    private ToggleGroup distance_type = new ToggleGroup(); // Grupo para los RadioButton
    private RadioButton euclidean = new RadioButton("Euclidean");
    private RadioButton manhattan = new RadioButton("Manhattan");
    //LISTA QUE CONTENDRÁ LAS CANCIONES
    public ListView<String> lista_canciones = new ListView<>();
    //BOTON RECOMENDAR
    private Button boton_recomendar = new Button("Recommend...");
    private Button boton_cambiar_tema = new Button("Dark theme");
    private boolean is_darktheme=false;
    private CheckBox checkbox_ordenar_genero = new CheckBox();
    //Para saber si está la opción de generos
    private boolean is_list_genre=false;


    //LISTA QUE DEVOLVERÁ LAS CANCIONES RECOMENDADAS
    private ListView<String> lista_recomendadas = new ListView<>();
    //ETIQUETA DE LA RECOMENDACIÓN
    Label etiqueta_recomendacion=new Label();
    //BOTON CERRAR RECOMENDACIONES
    private Button boton_cerrar_recomendaciones = new Button("Close");
    Stage segundaVentana = new Stage();
    // Crear un BorderPane
    BorderPane borderPane1 = new BorderPane();
    BorderPane borderPane2 = new BorderPane();
    BorderPane boton_recomendar_atras=new BorderPane();

    //Botón atrás para cambiar el genero
    Button boton_atras =new Button("Back");

    //Caja para seleccionar el numero de recomendaciones a mostrar
    private Spinner<Integer> etiqueta_flechitas = new Spinner<>();

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        AtomicBoolean boolean_lista_seleccionada = new AtomicBoolean(false);
        //icono de la ventana principal
        InputStream entrada = new FileInputStream("src/main/java/es/uji/al426285/View/imagen.png");
        Image imagen = new Image(entrada);
        ImageView imagen_vista = new ImageView();
        stage.getIcons().add(imagen);
        stage.setX(50);
        stage.setY(70);

        //nombre de ventana
        stage.setTitle("Songs recommendation");

        //Caja donde irán, tipo de recomendacion, tipo de distancia y titulos de canciones
        VBox vbox = vista();


        RadioButton radio_features = (RadioButton) vbox.getChildren().get(1);
        RadioButton radio_genre = (RadioButton) vbox.getChildren().get(2);
        RadioButton radio_euclidean = (RadioButton) vbox.getChildren().get(4);
        RadioButton radio_manhattan = (RadioButton) vbox.getChildren().get(5);
        var scene = new Scene(new StackPane(vbox), 640, 480);
        setLightTheme(scene);
        boton_recomendar.setDisable(true);
        lista_canciones.setTooltip(new Tooltip("Double click for receomendations on this song"));
        controlador.añadir_canciones();
        checkbox_ordenar_genero.setDisable(true);
        boton_atras.setVisible(false);
        radio_features.setOnAction(e -> {
            if (radio_features.isSelected()) {
                if (radio_euclidean.isSelected() || radio_manhattan.isSelected() && boolean_lista_seleccionada.get()) {
                    boton_recomendar.setDisable(false);
                } else {
                    boton_recomendar.setDisable(true);
                }
            }
        });
        radio_genre.setOnAction(e -> {
            if (radio_genre.isSelected()) {
                if (radio_euclidean.isSelected() || radio_manhattan.isSelected() && boolean_lista_seleccionada.get()) {
                    boton_recomendar.setDisable(false);
                } else {
                    boton_recomendar.setDisable(true);
                }
            }
        });
        radio_manhattan.setOnAction(e -> {
            if (radio_manhattan.isSelected()) {
                try {
                    controlador.entrenar();
                    checkbox_ordenar_genero.setDisable(false);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                if ((radio_features.isSelected() || radio_genre.isSelected()) && boolean_lista_seleccionada.get()) {
                    boton_recomendar.setDisable(false);
                } else {
                    boton_recomendar.setDisable(true);
                }
            }
        });
        radio_euclidean.setOnAction(e -> {
            if (radio_euclidean.isSelected()) {
                try {
                    controlador.entrenar();
                    checkbox_ordenar_genero.setDisable(false);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                if ((radio_features.isSelected() || radio_genre.isSelected()) && boolean_lista_seleccionada.get()) {
                    boton_recomendar.setDisable(false);
                } else {
                    boton_recomendar.setDisable(true);
                }
            }
        });

        lista_canciones.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            //  System.out.println("Elemento seleccionado: " + lista_canciones.getItems().get(newValue.intValue()));
            boolean_lista_seleccionada.set(true);
            if ((radio_manhattan.isSelected() || radio_euclidean.isSelected()) && (radio_features.isSelected() || radio_genre.isSelected())) {
                boton_recomendar.setDisable(false);
            } else
            {
                boton_recomendar.setDisable(true);}

        });
        lista_canciones.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
                if (is_list_genre){
                    controlador.getCanciones();
                    is_list_genre=false;
                }else {
                    boton_recomendar.fire();
                }
            }

        });
        BorderPane bra=(BorderPane) vbox.getChildren().get(8);
        Button boton= (Button)bra.getChildren().get(0);
        boton.setOnAction(e-> {
            try {
                segundaVentana.close();
                ventana2();

            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            try {
                controlador.recomendar();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            aplicarEstiloBoton(boton_cerrar_recomendaciones);

        });
        Button boton_atrass= (Button)bra.getChildren().get(1);
        boton_atrass.setOnAction(e->{
            if (is_list_genre) {
                controlador.añadir_canciones();
                is_list_genre = false;
                boton_atras.setVisible(false);
                checkbox_ordenar_genero.fire();
            }
            else {
                controlador.añadir_generos();
                is_list_genre=true;
            }
        });
        BorderPane borderpane1=(BorderPane) vbox.getChildren().get(0);
        Button dark_theme=(Button) borderpane1.getChildren().get(1);
        dark_theme.setOnAction(e-> {
                    if (is_darktheme){
                        setLightTheme(scene);
                        is_darktheme=false;
                    }
                    else {
                        setDarkTheme(scene);
                        is_darktheme=true;
                    }
                }
        );


        BorderPane borderpane2 = (BorderPane) vbox.getChildren().get(6);
        HBox og=(HBox) borderpane2.getChildren().get(1);
        CheckBox ordenar_genero=(CheckBox) og.getChildren().get(1);
        ordenar_genero.setOnAction(e->{
            if (ordenar_genero.isSelected()){
                controlador.añadir_generos();
                is_list_genre=true;
                boton_atras.setVisible(true);
            }else {
                controlador.añadir_canciones();
                is_list_genre=false;
                boton_atras.setVisible(false);
            }
        });


        //Insertamos la caja vertical en la escena
        stage.setScene(scene);//insertamos la escena en el escenario
        stage.show();


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
        VBox vbox = new VBox(borderPane1, song_features, guessed_genre,
                distance_label, euclidean, manhattan,
                borderPane2, lista_canciones,boton_recomendar_atras
                );
        vbox.setSpacing(6);
        vbox.setPadding(new Insets(10, 10, 10, 10));

        aplicarEstiloBoton(boton_recomendar);//aplicamos estilo a nuestro boton recomendar
        boton_recomendar_atras.setLeft(boton_recomendar);
        boton_recomendar_atras.setCenter(boton_atras);

        // Crear un HBox para el texto y la CheckBox
        HBox checkBoxWithText = new HBox(5); // Espacio de 5 píxeles entre los nodos
        checkBoxWithText.setAlignment(Pos.CENTER_RIGHT);
        checkBoxWithText.setPadding(new Insets(10));

        // Crear un Label para el texto de la CheckBox
        Label checkBoxLabel = new Label("Sort by gender:");

        // Agregar el Label y la CheckBox al HBox en el orden deseado
        checkBoxWithText.getChildren().addAll(checkBoxLabel, checkbox_ordenar_genero);


        // Configurar las posiciones de los nodos
        borderPane1.setLeft(recomendation_label);
        borderPane1.setRight(boton_cambiar_tema);
        borderPane2.setLeft(songs_label);
        borderPane2.setRight(checkBoxWithText);
        VBox vborderPane2=new VBox(2);


//NO ES NECESARIO
// ScrollPane barra_desplazamiento=new ScrollPane(lista_canciones);
//        barra_desplazamiento.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
//        barra_desplazamiento.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);


        return vbox;
    }

    private void ventana2() throws FileNotFoundException {

        Label etiqueta_num_recomendaciones = new Label("Number of recomendations");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0);
        etiqueta_flechitas.setValueFactory(valueFactory);
        FlowPane flow1 = new FlowPane(etiqueta_num_recomendaciones, etiqueta_flechitas);
        flow1.setHgap(10);
        VBox vbox2 = new VBox(flow1,
                etiqueta_recomendacion, lista_recomendadas, boton_cerrar_recomendaciones

        );



        vbox2.setSpacing(6);
        vbox2.setPadding(new Insets(10, 10, 10, 10));

        Scene scene2 = new Scene(new StackPane(vbox2), 400, 300);
        if (!is_darktheme){
            setLightTheme(scene2);
        }
        else {
            setDarkTheme(scene2);
        }
        segundaVentana.setScene(scene2);
        segundaVentana.setTitle("Recommended titles");
        segundaVentana.setX(800);
        segundaVentana.setY(150);
        InputStream entrada = new FileInputStream("src/main/java/es/uji/al426285/View/imagen.png");
        Image imagen = new Image(entrada);
        segundaVentana.getIcons().add(imagen);
        segundaVentana.show();
        FlowPane pane=(FlowPane) vbox2.getChildren().get(0);
        Spinner<Integer> spinner=(Spinner<Integer>) pane.getChildren().get(1);
        spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            try {
                controlador.recomendar();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            // Aquí puedes realizar las acciones que desees cuando cambie el valor del Spinner
        });
        controlador.modificar_etiqueta_recomendacion();

        Button boton_cerrar=(Button) vbox2.getChildren().get(3);
        boton_cerrar.setOnAction(e->{
                segundaVentana.close();
        });

    }


    private void aplicarEstiloBoton(Button boton) {
        boton.setStyle("-fx-background-color:\n"
                + "        #000080,\n"
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

    private void setLightTheme(Scene scene) {
        scene.getRoot().setStyle(
                "-fx-background-color: white; " +
                        "-fx-control-inner-background: white; " +
                        "-fx-text-fill: black;"
        );
    }

    // Método para aplicar el tema oscuro
    private void setDarkTheme(Scene scene) {
        scene.getRoot().setStyle(
                "-fx-background-color: #000000;" + // Fondo oscuro
                        "-fx-base: #3f474f;" +
                        "-fx-accent: #e7eff7;" +
                        "-fx-default-button: #7f878f;" +
                        "-fx-focus-color: #efefef;" +
                        "-fx-faint-focus-color: #efefef22;" +
                        "-fx-focused-text-base-color: ladder(" +
                        "-fx-selection-bar," +
                        "-fx-light-text-color 45%," +
                        "-fx-dark-text-color 46%," +
                        "-fx-dark-text-color 59%," +
                        "-fx-mid-text-color 60%" +
                        ");" +
                        "-fx-focused-mark-color: -fx-focused-text-base-color;"
        );
    }


    public RadioButton getSong_features() {
        return song_features;
    }

    public ToggleGroup getRecommendation_type() {
        return recommendation_type;
    }

    public RadioButton getGuessed_genre() {
        return guessed_genre;
    }

    public ToggleGroup getDistance_type() {
        return distance_type;
    }

    public RadioButton getEuclidean() {
        return euclidean;
    }

    public RadioButton getManhattan() {
        return manhattan;
    }

    public Button getBoton_recomendar() {
        return boton_recomendar;
    }

    public ListView<String> getLista_canciones() {
        return lista_canciones;
    }

    public ListView<String> getLista_recomendadas() {
        return lista_recomendadas;
    }

    public Label getEtiqueta_recomendacion() {
        return etiqueta_recomendacion;
    }

    public Button getBoton_cerrar_recomendaciones() {
        return boton_cerrar_recomendaciones;
    }
    public Spinner<Integer> getEtiqueta_flechitas() {
        return etiqueta_flechitas;
    }



    public static void main(String[] args) {
        launch();
    }

}