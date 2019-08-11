package Multijugador.opciones;

import Multijugador.Cliente;
import Multijugador.Servidor;

public abstract class Paquete {
   public static enum PacketTypes {
        //tipos de paquetes
        INVALID(-1), LOGIN(00), DISCONNECT(01), MOVE(02); 

        private int packetId;

        private PacketTypes(int packetId) {
            this.packetId = packetId;
        }

        public int getId() {
            return packetId;
        }
    } 
   
   public byte packetId;
    
    public Paquete(int packetId){
        this.packetId = (byte) packetId;
    }
    
    public abstract byte[] getData();
    
    public abstract void writeData(Cliente cliente);//enviar la data al servidor desde el cliente
    public abstract void writeData(Servidor servidor);//enviar la data a los clientes desde el server
    
    //Leer los bytes del id de los paquetes
    public String readData(byte[] data){
        String message = new String(data).trim();
        return message.substring(2);
    }
    
    //Deducir el tipo de paquete a partir de los id y tipos existentes
    public static PacketTypes lookupPacket(int id){
        for(PacketTypes p : PacketTypes.values()){
            if(p.getId() == id){
                return p;
            }
        }
        return PacketTypes.INVALID;
    }
    
    //se hace para que lookupPacket pueda recibir strings y validar si no estan enviando letras
    public static PacketTypes lookupPacket(String packetId){
        try{
            return lookupPacket(Integer.parseInt(packetId));
        }catch(NumberFormatException ex){
            return PacketTypes.INVALID;
        }
    }   
}
