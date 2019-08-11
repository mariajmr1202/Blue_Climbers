package Multijugador.opciones;

import Multijugador.Cliente;
import Multijugador.Servidor;

public class Paquete02Move extends Paquete{
    
    private String username, personaje;
    private int x, y;
    private boolean isMoving, isHurt;
    private int movingDir = 1;
    private boolean canJump = true;
    private boolean attackKey;
    
     //construccion cuando se recibe la data
    public Paquete02Move(byte[] data) {
        super(02);
        String[] dataArray = readData(data).split(":");
        this.username = dataArray[0];
        this.personaje = dataArray[1];
        this.x = Integer.parseInt(dataArray[2]);
        this.y = Integer.parseInt(dataArray[3]);
        this.isMoving = Integer.parseInt(dataArray[4]) == 1;
        this.movingDir = Integer.parseInt(dataArray[5]);

    }
    
    public Paquete02Move(String username, String personaje, int x, int y,boolean isMoving, int movingDir) {
        super(02);
        this.username = username;
        this.personaje = personaje;
        this.x = x;
        this.y = y;
        this.isMoving = isMoving;
        this.movingDir = movingDir;
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
        return ("02" + username + ":" + personaje + ":" + x + ":" + y + ":" + (isMoving? 1 : 0) + ":" +movingDir).getBytes();
    }
    
        public String getUsername() {
        return username;
    }

    public String getPersonaje() {
        return personaje;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getMovingDir() {
        return movingDir;
    }

    public void setMovingDir(int movingDir) {
        this.movingDir = movingDir;
    }
    
      public boolean getIsMoving() {
        return isMoving;
    }
      public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }
}
