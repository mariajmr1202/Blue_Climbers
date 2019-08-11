package ClasesYObjetos;

import static ClasesYObjetos.Ventanas.botonOff;
import static ClasesYObjetos.Ventanas.botonOn;
import javafx.stage.Stage;


public class BotonOn extends Boton {
     public BotonOn(Stage stage){
       super(stage);
    }
    
    @Override
    public void setCSStyle() {
        super.boton.setId("botonOn");
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
           Ventanas.theme.stop();
           super.boton.setTranslateX(-150);
           super.boton.setTranslateY(1000);
           botonOff.setPos();
       });
    }

    @Override
    public void setPos() {
       super.boton.setTranslateX(-150);
       super.boton.setTranslateY(350);
    } 
}
