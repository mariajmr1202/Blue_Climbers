package Multijugador.opciones;

import Multijugador.Cliente;
import Multijugador.Servidor;


public class Paquete01Disconnect extends Paquete{
     private String username,personaje;
    
    //construccion cuando se recibe la data
    public Paquete01Disconnect(byte[] data){
        super(01);
        String[] dataArray = readData(data).split(":");
        this.username = dataArray[0];
        this.personaje = dataArray[1];    
    }    
    
    public Paquete01Disconnect(String username){
        super(01);
        this.username = username;
    }
    
    //construccion al crear desde el cliente
    public Paquete01Disconnect(String username, String personaje){
        super(01);
        this.username = username;
        this.personaje = personaje;
    }
    
     //enviar al server
    @Override
    public void writeData(Cliente cliente) {
        cliente.sendData(getData());
    }

    //enviar a los clientes
    @Override
    public void writeData(Servidor server) {
        server.sendDataToAll(getData());
    }

    @Override
    public byte[] getData() {
        return ("01" + this.username + ":" + this.personaje).getBytes();
    }

    public String getUsername() {
        return username;
    }
    
    public String getPersonaje(){
        return personaje;
    }
}
