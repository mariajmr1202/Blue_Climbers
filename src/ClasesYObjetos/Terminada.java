package ClasesYObjetos;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Terminada extends Application {
     private BotonAceptar botonBack;
    @Override
    public void start(Stage stage) throws Exception {
       stage.setTitle("Partida terminada");
       showTerminada(stage);
    }
    
    public Scene showTerminada(Stage stage){
        botonBack=new BotonAceptar(stage, 60, 30, "boton-aceptar");
        botonBack.init();
        
        StackPane pane = setAyudaPane();
        
        pane.setId("pane-resultado");
        Scene scene = new Scene(pane, 850, 500);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.sizeToScene();

        return scene; 
    }
    
    public StackPane setAyudaPane(){
        Pane pane = new Pane();
        StackPane SPane = new StackPane();
        pane.setPadding(new Insets(5, 25, 25, 25));

        SPane.getChildren().add(pane);

        pane.getChildren().add(botonBack.boton);

        return SPane;
     }
}
