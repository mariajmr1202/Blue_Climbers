package Game.entidades;

import java.awt.image.BufferedImage;
import juego.gfx.Animation;

public class Personaje {
    //Atributos
   public Animation animation;
   private Animation idle, attack, jump, move,hurt; 
   public int puntos,enemigos;
   public String ptsTotales="0",ptsGanador="0",ptsPerdedor="0";
   
   //Constructor
   public Personaje(BufferedImage[] idle, BufferedImage[] attack, BufferedImage[] jump, BufferedImage[] move,BufferedImage[] hurt,int puntos,int enemigos) {
       //Cargar animaciones
        this.idle = new Animation(idle, 25); 
        this.attack = new Animation(attack, 3);
        this.move = new Animation(move, 5);
        this.jump = new Animation(jump, 7);
        this.hurt= new Animation(hurt,25);
        this.puntos=puntos;
        this.enemigos=enemigos;
        animation = this.idle;
    }
   
   public Animation getIdle() {
        return idle;
    }

    public Animation getJump() {
        return jump;
    }

    public Animation getMove() {
        return move;
    }

    public Animation getAttack() {
        return attack;
    }
    
    public Animation getHurt(){
        return hurt;
    }
    
    public void setPuntos(int puntos){
        this.puntos=this.puntos+puntos;
    }
    public int getPuntos(){
        return puntos;
    }
    
    public void setEnemigos(int enemigos){
        this.enemigos=this.enemigos+enemigos;
    }
    public int getEnemigos(){
        return enemigos;
    }

    public String getPtsTotales() {
        return ptsTotales;
    }

    public String getPtsGanador() {
        return ptsGanador;
    }

    public String getPtsPerdedor() {
        return ptsPerdedor;
    }

    public void setPtsTotales(String ptsTotales) {
        this.ptsTotales = ptsTotales;
    }

    public void setPtsGanador(String ptsGanador) {
        this.ptsGanador = ptsGanador;
    }

    public void setPtsPerdedor(String ptsPerdedor) {
        this.ptsPerdedor = ptsPerdedor;
    }
    
    
    
}
