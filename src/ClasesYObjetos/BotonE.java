package ClasesYObjetos;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;


public class BotonE extends Boton{
     public BotonE(Stage stage){
       super(stage);
    } 
     
    @Override
    public void setCSStyle() {
        super.boton.setId("boton2");
        super.boton.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
    }
    
   @Override
    public void setDimensiones() {
        super.boton.setPickOnBounds(false);
        super.boton.setMinSize(350, 255);
        super.boton.setMaxSize(350, 255);
    }
    
   @Override
    public void setAccion() {
        super.boton.setOnAction(event -> {
           MenuEstadisticas menuE = new MenuEstadisticas();
           try {
               menuE.start(stage);
           } catch (Exception ex) {
               Logger.getLogger(BotonAcerca.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
    }

    @Override
    public void setPos() {
       super.boton.setTranslateX(240);
       super.boton.setTranslateY(300);
    }
}
