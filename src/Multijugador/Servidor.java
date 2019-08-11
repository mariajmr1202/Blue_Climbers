package Multijugador;

import Game.Game;
import Game.entidades.JugadorMP;
import Multijugador.opciones.Paquete;
import Multijugador.opciones.Paquete.PacketTypes;
import Multijugador.opciones.Paquete00Login;
import Multijugador.opciones.Paquete01Disconnect;
import Multijugador.opciones.Paquete02Move;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Servidor extends Thread{
    private DatagramSocket socket;
    private List<JugadorMP> connectedPlayers;  //jugadores conectados al servidor
    public Game game;
    public Servidor(Game game) {
        this.game = game;
        try {
            connectedPlayers = new ArrayList<>();
            this.socket = new DatagramSocket(1331);
        } catch (SocketException ex) {
            ex.printStackTrace();
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
                ex.printStackTrace();
            }

            //toma los paquetes los maneja y consigue de que tipo son
            this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
        }

    }
    
     private void sendData(byte[] data, InetAddress ipAddress, int port) {

        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
        try {
            socket.send(packet);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    //enviar data a todos los clientes desde el server
    public void sendDataToAll(byte[] data) {
        for (JugadorMP p : connectedPlayers) {
            sendData(data, p.ipAddress, p.port);
        }
    }
    
    //decodifica y maneja el paquete
    private void parsePacket(byte[] data, InetAddress address, int port){
        String message = new String(data).trim();
        PacketTypes type = Paquete.lookupPacket(message.substring(0, 2));
        Paquete packet = null;
        
         switch (type){
             case INVALID:
                break;
            case LOGIN: {
                packet = new Paquete00Login(data);
                System.out.println("[" + address.getHostAddress() + ":" + port + "] " + ((Paquete00Login) packet).getUsername()+
                        " ("+((Paquete00Login) packet).getPersonaje()+") se conecto...");
                JugadorMP jugador = new JugadorMP(((Paquete00Login) packet).getPersonaje(), 100, 100, ((Paquete00Login) packet).getUsername(), address, port);  
                this.addConnection(jugador,((Paquete00Login) packet));
                break;
            }
            case DISCONNECT:{
                packet = new Paquete01Disconnect(data);
                System.out.println("[" + address.getHostAddress() + ":" + port + "] " + ((Paquete01Disconnect) packet).getUsername() + " se desconecto...");
                this.removeConnection((Paquete01Disconnect) packet);
                break;
            }
            case MOVE: {
                packet = new Paquete02Move(data);
                this.handleMove(((Paquete02Move) packet));
                break;
            }
         }
    }
    
     public void addConnection(JugadorMP jugador, Paquete00Login packet){
        boolean alreadyConnected = false; 
        Paquete00Login paqueton = packet;
        for(JugadorMP p : this.connectedPlayers){ 
            packet = paqueton;
            if(jugador.getUsername().equalsIgnoreCase(p.getUsername())){
                if(p.ipAddress == null){
                    p.ipAddress = jugador.ipAddress;
                }               
                if(p.port == -1){
                    p.port = jugador.port;
                }
                alreadyConnected = true;
            }else{
                sendData(packet.getData(),p.ipAddress,p.port); 
                
                packet = new Paquete00Login(p.getUsername(),p.getPersonaje());
                sendData(packet.getData(),jugador.ipAddress,jugador.port);
            }
        }
        if(!alreadyConnected){
            this.connectedPlayers.add(jugador); 
        }
    }
     
    private void removeConnection(Paquete01Disconnect packet){
        this.connectedPlayers.remove(getJugadorMPindex(getJugadorMP(packet.getUsername()).getUsername()));
        packet.writeData(this);
    }
    
    private JugadorMP getJugadorMP(String username){
        for(JugadorMP p : this.connectedPlayers){
            if(p.getUsername().equalsIgnoreCase(username)){
                return p;
            }
        }
        return null;
    }
    
    //obtener el indice de uno de los jugadores conectados
    private int getJugadorMPindex(String username){
        int index = 0;
        for(JugadorMP p : this.connectedPlayers){
            if(p.getUsername().equalsIgnoreCase(username)){
                break;
            }
            index++;
        }
        return index;
    }
    
     //paso de variables de paquete movimientos
    private void handleMove(Paquete02Move packet) {
        if(getJugadorMP(packet.getUsername()) != null){
            int index = getJugadorMPindex(packet.getUsername());
            JugadorMP jugador = this.connectedPlayers.get(index);
            jugador.x = packet.getX();
            jugador.y = packet.getY();
            jugador.setDirection(packet.getMovingDir());
            packet.writeData(this);        
        }
    }
}
