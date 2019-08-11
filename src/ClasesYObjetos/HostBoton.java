package ClasesYObjetos;

import static ClasesYObjetos.SelecionMenu.insertIP;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;


public class HostBoton extends Boton{
    private int x, y;
    private String id;
    
    public HostBoton(Stage stage, int x, int y, String id) {
        super(stage);
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    public void init() {
        setCSStyle();
        setDimensiones();
        setPos();
        setAccion();
    }
    
    @Override
    public void setCSStyle() {
        super.boton.setId(id);
        super.boton.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
    }

    @Override
    public void setDimensiones() {
        super.boton.setPickOnBounds(false);
        super.boton.setMinSize(140, 25);
        super.boton.setMaxSize(140, 25);
    }
    
       @Override
    public void setAccion() {
        super.boton.setOnAction(event -> {
            
            insertIP = new TextField("Inserte ip");
            if ("host-button".equals(this.id)) {

                try {
                    SelecionMenu.ipAddress = InetAddress.getLocalHost().getHostAddress();
                    System.out.println("host:" + SelecionMenu.ipAddress);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(HostBoton.class.getName()).log(Level.SEVERE, null, ex);
                }
                insertIP.setText(SelecionMenu.ipAddress);
                insertIP.setEditable(false);
                SelecionMenu.setHost(true);
            } else if ("join-button".equals(this.id)) {
                
                insertIP.setEditable(true);

                insertIP.setOnKeyReleased(event2 -> {
                    SelecionMenu.ipAddress = insertIP.getText();
                    System.out.println("ipAddress:" + SelecionMenu.ipAddress);
                });
                insertIP.setId("ip-field");
                insertIP.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
                SelecionMenu.pane.getChildren().add(insertIP);
                SelecionMenu.setHost(false);
            }

        });
    }
    
    @Override
    public void setPos() {
        super.boton.setTranslateX(x);
        super.boton.setTranslateY(y);
    }
}
