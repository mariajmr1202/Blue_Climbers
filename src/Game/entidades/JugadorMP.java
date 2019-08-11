package Game.entidades;

import Game.Handler;
import java.net.InetAddress;

public class JugadorMP extends Jugador{
    public InetAddress ipAddress;
    public int port;
    
    public JugadorMP(String personajeSelec, Handler handler, float x, float y, String username,InetAddress ipAddress, int port) {
        super(personajeSelec, handler, x, y, username);
        this.ipAddress = ipAddress;
        this.port = port;
    }
    
    public JugadorMP(String personajeSelec, float x, float y, String username,InetAddress ipAddress, int port) {
        super(personajeSelec, null, x, y, username);
        this.ipAddress = ipAddress;
        this.port = port;
    }
    
    @Override
    public void update(){
        super.update();
    }
}
