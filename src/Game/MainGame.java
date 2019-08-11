package Game;

import javafx.stage.Stage;

public class MainGame {
    public MainGame(String personajeSelec,boolean esHost,String username,String ipAddress,String mapa,Stage stage){
        Game game = new Game("DumbyApp", 900, 500,personajeSelec,esHost,username,ipAddress,mapa,stage);  
    }
}
