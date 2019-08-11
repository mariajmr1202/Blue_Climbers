package ClasesYObjetos;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;


public class BotonS extends Boton{

    public BotonS(Stage stage){
       super(stage);
    }
    
    @Override
    public void setCSStyle() {
        super.boton.setId("boton0");
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
           AudioClip clicked = new AudioClip(this.getClass().getResource("/Sonidos/click.m4a").toString());
           clicked.play();
           SelecionMenu menu=new SelecionMenu();
           try {
               menu.start(stage);
           } catch (Exception ex) {
               Logger.getLogger(BotonS.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
    }

    @Override
    public void setPos() {
       super.boton.setTranslateX(240);
       super.boton.setTranslateY(150);
    }
    
}
