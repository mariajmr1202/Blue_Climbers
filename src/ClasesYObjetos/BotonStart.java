package ClasesYObjetos;

import Game.MainGame;
import javafx.stage.Stage;


public class BotonStart extends Boton{
    private MainGame game;
    private int x, y;
    private String id;
    
    public BotonStart(Stage stage, int x, int y, String id) {
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
        super.boton.setMinSize(90, 90);
        super.boton.setMaxSize(90, 90);
    }
    
     @Override
    public void setAccion(){
         super.boton.setOnAction(event ->{
          stage.hide();
          game = new MainGame(SelecionMenu.getSelected(), SelecionMenu.getHost(), SelecionMenu.getUsername(),SelecionMenu.getIPaddress(),SelecionMenu.getMapa(),stage);
            
         });
    }
    
    @Override
    public void setPos() {
        super.boton.setTranslateX(x);
        super.boton.setTranslateY(y);
    }
}
