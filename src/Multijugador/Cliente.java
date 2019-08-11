package Multijugador;

import Game.Game;
import Game.entidades.JugadorMP;
import Multijugador.opciones.Paquete;
import Multijugador.opciones.Paquete00Login;
import Multijugador.opciones.Paquete01Disconnect;
import Multijugador.opciones.Paquete02Move;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente extends Thread {
    private InetAddress ipAddress;
    private DatagramSocket socket;
    public int i;
    public Game game;
    
    public Cliente(Game game, String ipAdress) {
        this.game = game;
        try {
            this.socket = new DatagramSocket();
            this.ipAddress = InetAddress.getByName(ipAdress);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @Override
    public void run() {

        while (true) {
            
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());

        }

    }
    
    
    public void sendData(byte[] data) {

        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
        try {
            this.socket.send(packet);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
    //Decodifica y maneja el paquete
    public void parsePacket(byte[] data, InetAddress address, int port){
        String message = new String(data).trim();
        Paquete.PacketTypes type = Paquete.lookupPacket(message.substring(0, 2));
        Paquete packet = null;
        
        switch (type){
            case INVALID:
                break;
            case LOGIN: {
                packet = new Paquete00Login(data);
                System.out.println("[" + address.getHostAddress() + ":" + port + "] "
                        + ((Paquete00Login) packet).getUsername() + " (" + ((Paquete00Login) packet).getPersonaje() + ") se unio...");
                JugadorMP jugador = new JugadorMP(((Paquete00Login) packet).getPersonaje(), 100, 100, ((Paquete00Login) packet).getUsername(), address, port);
                game.getHandler().getMapa().addPlayer(jugador);
              
                break;
            }
            case DISCONNECT: {
                packet = new Paquete01Disconnect(data);
                System.out.println("[" + address.getHostAddress() + ":" + port + "] " + ((Paquete01Disconnect) packet).getUsername() + " se fue...");
                game.getHandler().getMapa().removePlayer(((Paquete01Disconnect) packet).getUsername());
                break;
            }
            
            case MOVE: {
                packet = new Paquete02Move(data);
                handlePacket((Paquete02Move) packet);
                
                break;
            }
             
        }
    }
    
   private void handlePacket(Paquete02Move packet) {
        this.game.getHandler().getMapa().movePlayer(packet.getUsername(), packet.getX(),packet.getY(),packet.getIsMoving(),packet.getMovingDir());
    }
    
}
