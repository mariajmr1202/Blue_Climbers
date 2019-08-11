package Estados;

import Game.Game;
import Game.Handler;
import Game.entidades.Jugador;
import Mapa.Mapa;
import display.Display;
import java.awt.Graphics;

public class GameState extends Estado{
    int i=0;
    public Mapa mapa;
    public Jugador player;
    Handler handler;
    Game game;
    Display ventana;
    
    public GameState(Handler handler) {
        super(handler);
        mapa = new Mapa(handler);
        handler.setMapa(mapa);
       
    }
    
    public GameState(Handler handler, Mapa mapa){
        super(handler);
        this.mapa = mapa;
        handler.setMapa(mapa);
    }
    
    @Override
    public void update() {
        mapa.update(); //actualizar mapa
    }
    
    @Override
    public void render(Graphics g) {
        mapa.render(g);//dibujar mapa
    }
}
