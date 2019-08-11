package ClasesYObjetos;

import static ClasesYObjetos.SelecionMenu.tuIP;
import Registros.Archivo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import Game.Handler;
import Game.entidades.Personaje;
import java.awt.Font;
import javafx.scene.text.Text;

public class MenuEstadisticas extends Application {
     private BotonAtras botonBack;
     Label pin1,pin2,pin3,pin4;
     public static TextField sonicg,sonicp,sonict;
     public static TextField shadowg,shadowp,shadowt;
     public static TextField linkg,linkp,linkt;
     public static TextField megag,megap,megat;
     public Archivo aplicacion;
     Font font;
     Personaje personaje;
     @Override
    public void start(Stage stage) throws Exception {
       stage.setTitle("Estadisticas del juego");
       showEstadisticas(stage);
    }
    
    public Scene showEstadisticas(Stage stage){
        botonBack=new BotonAtras(stage, 60, 30, "boton-atras");
        botonBack.init();
        pin1 = new Label();
        pin2 = new Label();
        pin3 = new Label();
        pin4 = new Label();
        pin1.setId("stock-Link");
        pin2.setId("stock-Shadow");
        pin3.setId("stock-Mega");
        pin4.setId("stock-Sonic");
        pin1.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        pin2.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        pin3.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        pin4.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
       
        aplicacion = new Archivo();
        Agregar("link");
        linkg= new TextField(aplicacion.getPtosGanadas());
        linkp=new TextField(aplicacion.getPtosPerdidas());
        linkt=new TextField(aplicacion.getPtosTotales());
  
        Agregar("shadow");
        shadowg=new TextField(aplicacion.getPtosGanadas());
        shadowp=new TextField(aplicacion.getPtosPerdidas());
        shadowt=new TextField(aplicacion.getPtosTotales());

        Agregar("mega");
        megag=new TextField(aplicacion.getPtosGanadas());
        megap=new TextField(aplicacion.getPtosPerdidas());
        megat=new TextField(aplicacion.getPtosTotales());
         
        Agregar("sonic");
        sonicg=new TextField(aplicacion.getPtosGanadas());
        sonicp=new TextField(aplicacion.getPtosPerdidas());
        sonict=new TextField(aplicacion.getPtosTotales());
         
        Configuracion(shadowp,"ShadowP",430,280);
        Configuracion(shadowg,"ShadowG",270,280);
        Configuracion(shadowt,"ShadowT",600,280);
        Configuracion(linkp,"LinkP",430,220);
        Configuracion(linkg,"LinkG",270,220);
        Configuracion(linkt,"LinkT",600,220); 
        Configuracion(megap,"MegaP",430,340);
        Configuracion(megag,"MegaG",270,340); 
        Configuracion(megat,"MegaT",600,340);
        Configuracion(sonicp,"SonicP",430,400); 
        Configuracion(sonicg,"SonicG",270,400);
        Configuracion(sonict,"SonicT",600,400);
        
    
        StackPane pane = setEstadisticaPane(pin1,pin2,pin3,pin4);
        
        pane.setId("pane-Estadistica");
        Scene scene = new Scene(pane, 850, 500);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.sizeToScene();

        return scene;      
    }
    
     public StackPane setEstadisticaPane(Label pin1,Label pin2,Label pin3, Label pin4){
        Pane pane = new Pane();
        StackPane SPane = new StackPane();
        pane.setPadding(new Insets(5, 25, 25, 25));

        SPane.getChildren().add(pane);
        pane.getChildren().add(shadowp);
        pane.getChildren().add(shadowg);
        pane.getChildren().add(shadowt);
        pane.getChildren().add(linkp);
        pane.getChildren().add(linkg);
        pane.getChildren().add(linkt);
        pane.getChildren().add(megap);
        pane.getChildren().add(megag);
        pane.getChildren().add(megat);
        pane.getChildren().add(sonicp);
        pane.getChildren().add(sonicg);
        pane.getChildren().add(sonict);
        pane.getChildren().add(botonBack.boton);
        pane.getChildren().add(pin1);  
        pane.getChildren().add(pin2); 
        pane.getChildren().add(pin3); 
        pane.getChildren().add(pin4); 
        
        return SPane;
     }
     
    //Manejo de archivos
     public void Agregar(String personaje){
         if(aplicacion.abrirArchivoLectura(personaje)==0){
                  aplicacion.abrirArchivoLectura(personaje);
                  aplicacion.leerRegistros();
                  aplicacion.cerrarArchivo();
              }
     }
     
     public void Configuracion(TextField personaje,String pj,int x,int y){
         personaje.setLayoutX(x);
         personaje.setLayoutY(y);
         personaje.setId(pj);
         personaje.setEditable(false);
         personaje.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
     }
}
