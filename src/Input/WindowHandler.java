package Input;

import Game.Game;
import Multijugador.opciones.Paquete01Disconnect;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowHandler implements WindowListener{
    private final Game game;
    
    public WindowHandler(Game game){
        this.game = game;
        this.game.getHandler().getDisplay().getVentana().addWindowListener(this);
    }
    
    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //Si cierra la ventana, el jugador se desconecta de la partida
        Paquete01Disconnect packet =  new Paquete01Disconnect(this.game.getJugador().getUsername());
        packet.writeData(this.game.socketCliente);
    }
    
    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
