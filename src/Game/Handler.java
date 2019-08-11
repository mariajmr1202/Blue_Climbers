package Game;

import Input.Teclado;
import Mapa.Mapa;
import display.Display;

public class Handler {
    private Game game;
    private Mapa mapa;
  
    
    public Handler(Game juego) {
        this.game = juego;
    }

    public int getAncho() {
        return game.getAncho();
    }

    public int getAlto() {
        return game.getAlto();
    }

    public Display getDisplay() {
        return game.getDisplay();
    }

    public Teclado getTeclado() {
        return game.getTeclado();
    }
    
     public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Mapa getMapa() {
        return mapa;
    }
    
    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }
}
