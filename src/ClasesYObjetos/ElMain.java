package ClasesYObjetos;

import javafx.application.Application;
import javafx.stage.Stage;


public class ElMain extends Application{


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Ventanas vent=new Ventanas();
        vent.start(stage);
    }
    
}
