package ClasesYObjetos;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;


public class Ventanas extends Application{
    public static AudioClip theme;
    static int band=0;
    public static boolean sonido=true;
    //Variables de botones
    public static Boton botonOn;
    public static Boton botonOff;
    public static Pane pane;
    public static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        this.stage=stage;
        stage.setTitle("DumbyApp");
        setMainMenu(stage);
    }
    
    public Scene setMainMenu(Stage stage){
        //Comienza la musica al ejecutar
        if(band==0){
            theme = new AudioClip(this.getClass().getResource("/Sonidos/Gerudo_Valley.m4a").toString());
            theme.play();
            band=1;
        }
        //Crear botones y modificarlos
        Boton[] boton = new Boton[4];
        boton[0]=new BotonS(stage);
        boton[1]=new BotonE(stage);
        boton[2]=new BotonAcerca(stage);
        boton[3]=new BotonAyuda(stage);
        //Botones de la musica
        botonOn=new BotonOn(stage);
        botonOff=new BotonOff(stage);
        
        for (int i = 0; i < 4; i++) {
            boton[i].setCSStyle();
            boton[i].setDimensiones();
            boton[i].setAccion();
        }
        botonOn.setCSStyle();
        botonOn.setDimensiones();
        botonOn.setAccion();
        botonOff.setCSStyle();
        botonOff.setDimensiones();
        botonOff.setAccion();
        
        StackPane pane = setMainPane(boton);
        //Cargar caracteristicas de la ventana principal (fondo,letra,etc)
        pane.setId("ventana");
        Scene scene = new Scene(pane, 850, 500);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
        

        return scene;
    }
    //Funcion que agrega componentes a la ventana
     public StackPane setMainPane(Boton[] botones) {
        pane = new Pane();
        StackPane SPane = new StackPane();
        pane.setPadding(new Insets(5, 25, 25, 25));

        SPane.getChildren().add(pane);
        for (int i = 0; i < 4; i++) {
            pane.getChildren().add(botones[i].boton);
            botones[i].setPos();
        }
       //Agregar componentes al pane
       pane.getChildren().add(botonOn.boton);
       botonOn.setPos();
       pane.getChildren().add(botonOff.boton);
       botonOff.getBoton().setTranslateX(-150);
       botonOff.getBoton().setTranslateY(1000);
       return SPane;
    }
    
    public AudioClip getTheme(){
        return theme;
    }
}
