package ClasesYObjetos;

import javafx.scene.control.Button;
import javafx.stage.Stage;

public abstract class Boton {
    //Atributos de boton
    public Button boton = new Button();
    public Stage stage;

    public Boton(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
     
    
    public Button getBoton() {
        return boton;
    }

    public void setBoton(Button boton) {
        this.boton = boton;
    }
    
    //Funciones abstractas
    public abstract void setCSStyle();

    public abstract void setDimensiones();

    public abstract void setAccion();

    public abstract void setPos();
}
