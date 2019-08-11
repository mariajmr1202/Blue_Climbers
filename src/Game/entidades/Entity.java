package Game.entidades;

import Game.Game;
import Game.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class Entity {
    public Handler handler;
    public float x;
    public float y;
    protected int ancho, alto; 
    protected Game game;
    protected Rectangle bounds;
    
    public Entity(Handler handler, float x, float y, int ancho, int alto) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        bounds=new Rectangle(0,0,ancho,alto);
    }
         
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    //Clases abstractas
    public abstract void update();

    public abstract void render(Graphics g);
}
