package ClasesYObjetos;

import static ClasesYObjetos.Ventanas.botonOff;
import static ClasesYObjetos.Ventanas.botonOn;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class BotonOff extends Boton{
      public BotonOff(Stage stage){
       super(stage);
    }
    
    @Override
    public void setCSStyle() {
        super.boton.setId("botonOff");
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
       super.boton.setOnAction(event ->{
           Ventanas.theme.play();  
           super.boton.setTranslateX(-150);
           super.boton.setTranslateY(1000);
           botonOn.setPos();
       });
    }

    @Override
    public void setPos() {
       super.boton.setTranslateX(-150);
       super.boton.setTranslateY(350);
    }
}

