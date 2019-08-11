package ClasesYObjetos;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;

public class BotonAcerca extends Boton {
    public BotonAcerca(Stage stage){
       super(stage);
    }
    
     @Override
    public void setCSStyle() {
        super.boton.setId("boton3");
        super.boton.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
    }
    
    @Override
    public void setAccion() {
       super.boton.setOnAction(event -> {
           MenuAcercaDe menuAyuda = new MenuAcercaDe();
           try {
               menuAyuda.start(stage);
           } catch (Exception ex) {
               Logger.getLogger(BotonAcerca.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
    }
    
    @Override
    public void setPos() {
       super.boton.setTranslateX(600);
       super.boton.setTranslateY(-80);
    }

    @Override
    public void setDimensiones() {
        super.boton.setPickOnBounds(false);
        super.boton.setMinSize(350, 255);
        super.boton.setMaxSize(350, 255);
    }
}
