package ClasesYObjetos;

import static ClasesYObjetos.SelecionMenu.userTextField;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javax.swing.JTextField;

public class BotonPersonaje extends Boton{
    private String personaje;
    private int x, y;
    private Label etiqueta;
    
   public BotonPersonaje(Stage stage, String personaje, int x, int y) {
      super(stage);
      this.personaje = personaje.toLowerCase();
      this.x = x;
      this.y = y;
    }
   
   public void init() {
     setCSStyle();
     setDimensiones();
     setPos();
     setAccion();
    }
   
    @Override
   public void setCSStyle() {
      super.boton.setId("boton" + "-" + personaje);
      super.boton.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
    }
   
    @Override
    public void setDimensiones() {
      super.boton.setPickOnBounds(false);
      super.boton.setMinSize(130, 90);
      super.boton.setMaxSize(130, 90);
    }
    
    @Override
     public void setAccion() {
         super.boton.setOnAction(event -> {
            AudioClip clicked1 = new AudioClip(this.getClass().getResource("/Sonidos/click.m4a").toString());
            clicked1.play();
            
            SelecionMenu.setSelected(this.personaje);
            
            Label pin = new Label();
            pin.setId(personaje + "-Pin");
            pin.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
            pin.setLayoutX(300);
            pin.setLayoutY(400);
            SelecionMenu.pane.getChildren().add(pin);
            
            etiqueta= new Label();
            etiqueta.setId("-etiqueta-");
            etiqueta.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
            SelecionMenu.pane.getChildren().add(etiqueta);
            
            userTextField = null;
            userTextField = new TextField("Insert here");
            userTextField.setOnKeyReleased(event2 -> {
            SelecionMenu.username = userTextField.getText();
            
          });
         
            userTextField.setLayoutX(300);
            userTextField.setLayoutY(400);
            userTextField.setId("user-text-field");
            userTextField.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
            SelecionMenu.pane.getChildren().add(userTextField);
         });
         
         
     }
    
    
    @Override
    public void setPos() {
        super.boton.setTranslateX(x);
        super.boton.setTranslateY(y);
    }
    
    
}