package ClasesYObjetos;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class MenuAyuda extends Application{
    private BotonAtras botonBack;
    @Override
    public void start(Stage stage) throws Exception {
       stage.setTitle("Ayuda");
       showAyuda(stage);
    }
    
    public Scene showAyuda(Stage stage){
        botonBack=new BotonAtras(stage, 60, 30, "boton-atras");
        botonBack.init();
        
        StackPane pane = setAyudaPane();
        
        pane.setId("pane-ayuda");
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
