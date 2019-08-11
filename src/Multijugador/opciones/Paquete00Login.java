package Multijugador.opciones;

import Multijugador.Cliente;
import Multijugador.Servidor;

public class Paquete00Login extends Paquete {
    private String username, personaje;
    private int x,y;
    
   //construccion cuando se recibe la data
    public Paquete00Login(byte[] data){
        super(00);
        String[] dataArray = readData(data).split(":");
        this.username = dataArray[0];
        this.personaje = dataArray[1];
    }    
    
    //construccion al crear desde el cliente
    public Paquete00Login(String username, String personaje){
        super(00);
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
        return ("00" + this.username +":"+ this.personaje).getBytes();
    }

    public String getUsername() {
       return username;
    }
    
    public String getPersonaje() {
        return personaje;
    }
}
