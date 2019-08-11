package ClasesYObjetos;

import javafx.stage.Stage;

public class BotonA extends Boton{
    public BotonA(Stage stage){
       super(stage);
    }
    
    @Override
    public void setCSStyle() {
        super.boton.setId("boton1");
        super.boton.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
    }
    
    @Override
    public void setAccion() {
       
    }
    
    @Override
    public void setPos() {
       super.boton.setTranslateX(70);
       super.boton.setTranslateY(250);
    }

    @Override
    public void setDimensiones() {
        super.boton.setPickOnBounds(false);
        super.boton.setMinSize(350, 255);
        super.boton.setMaxSize(350, 255);
    }
}
