package ClasesYObjetos;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SelecionMenu extends Application {
    private String[] personajes = {"shadow", "sonic", "mega", "link"};
    public static String selected;
    public static String mapa;
    public static String username,ipAddress;
    private static boolean esHost = true;
    private int x = 60, y = 150;
    public static TextField userTextField, tuIP, insertIP;
    public static StackPane pane;
    public static Label labelLayout = new Label();
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Personajes");
        showSeleccionMenu(stage);
    }
    
     public Scene showSeleccionMenu(Stage stage){
          BotonPersonaje[] botonPersonaje = new BotonPersonaje[personajes.length];
          
          for (int i = 0; i < personajes.length; i++) {
            botonPersonaje[i] = new BotonPersonaje(stage, personajes[i], x, y);
            botonPersonaje[i].init();
            x += 200;
        }
          
         BotonAtras botonBack = new BotonAtras(stage, 10, 1, "boton-atras");
         BotonStart botonStart = new BotonStart(stage, 700, 300, "boton-start");
         HostBoton botonHost = new HostBoton(stage, 155, 8, "host-button");
         HostBoton botonJoin = new HostBoton(stage, 289, 8, "join-button");
         botonBack.init();
         botonStart.init();
         botonHost.init();
         botonJoin.init();
        
         userTextField = new TextField("Username");
         
         try {
            InetAddress ipAddress = InetAddress.getLocalHost();

            tuIP = new TextField("IP : " + ipAddress.getHostAddress());
            tuIP.setLayoutX(705);
            tuIP.setLayoutY(14);
            tuIP.setId("tuIP");
            tuIP.setEditable(false);
            tuIP.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        } catch (UnknownHostException ex) {
            Logger.getLogger(SelecionMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         StackPane pane = setSmashPane(botonPersonaje, botonBack, botonStart, botonHost, botonJoin);
         this.pane = pane;
         
         pane.setId("pane-smash");
         
        Scene scene = new Scene(pane, 850, 500);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.sizeToScene();
        return scene;
     }
     
     public StackPane setSmashPane(Boton[] botones, Boton boton1,Boton botonStart, Boton hostButton, Boton joinButton){
        Pane pane = new Pane();
        StackPane SPane = new StackPane();
        pane.setPadding(new Insets(5, 25, 25, 25));

        SPane.getChildren().add(pane);
        
        pane.getChildren().add(tuIP);

        pane.getChildren().add(boton1.boton);
        pane.getChildren().add(botonStart.boton);
        pane.getChildren().add(hostButton.boton);
        pane.getChildren().add(joinButton.boton);
        
        for (int i = 0; i < personajes.length; i++) {
            pane.getChildren().add(botones[i].boton);
        }
        return SPane;
     }
     
    public static void setSelected(String personaje) {
       selected = personaje;
    }
    
    public static String getSelected(){
        return selected;
    }
    public static void setHost(boolean EsHost) {
        esHost = EsHost;
    }
    
    public static boolean getHost() {
        return esHost;
    }
        
    public static String getMapa(){
        return mapa;
    }
    
    public static String getUsername(){
        return username;
    }
    
    public static String getIPaddress(){
        return ipAddress;
    }
}
