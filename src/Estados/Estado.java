package Estados;

import Game.Handler;
import java.awt.Graphics;

public abstract class Estado {
    protected Handler handler;
    private static Estado estadoActual = null;
    
    public static void setEstado(Estado estado) {
        estadoActual = estado;
    }
    
    public static Estado getEstado() {
        return estadoActual;
    }
    
    public Estado(Handler handler) {
        this.handler = handler;
    }
    
    public abstract void update();
    
    public abstract void render(Graphics g);
}
