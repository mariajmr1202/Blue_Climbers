package ClasesYObjetos;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class BotonAceptar extends Boton{
    private int x, y;
    private String id;
    
        public BotonAceptar(Stage stage, int x, int y, String id) {
        super(stage);
        this.x = x;
        this.y = y;
        this.id = id;
    }
        
    public void init() {
        setCSStyle();
        setDimensiones();
        setAccion();
        setPos();
    }

    @Override
    public void setCSStyle() {
        super.boton.setId(id);
        super.boton.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
    }

    @Override
    public void setDimensiones() {
        super.boton.setPickOnBounds(false);
        super.boton.setMinSize(40, 29);
        super.boton.setMaxSize(40, 29);
    }

    @Override
    public void setAccion() {
        super.boton.setOnAction(event ->{
 
                Ventanas mainMenu = new Ventanas();
                 try {
                     mainMenu.start(stage);
                 } catch (Exception ex) {
                     Logger.getLogger(BotonAtras.class.getName()).log(Level.SEVERE, null, ex);
                 }
            
            
    });
    }

    @Override
    public void setPos() {
        super.boton.setTranslateX(x);
        super.boton.setTranslateY(y);
    }
    
}
