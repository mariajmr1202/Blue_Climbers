package Game.entidades;

import Game.Handler;
import java.awt.Graphics;
import java.util.ArrayList;

public class EntityManager {
    private Handler handler;
    public Jugador jugador;

    //Entidades en multijugador
    private ArrayList<Entity> entities;
    
    public EntityManager(Handler handler) {
        this.handler = handler;
        entities = new ArrayList<>();
    }

    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.update();
        }
    }
    
     public void render(Graphics g) {
        for (Entity e : entities) {
            e.render(g);
        }
    }

    public void addEntity(Entity e) {
        this.jugador = (Jugador) e;
        entities.add(e);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}
