package Game.entidades;

import Game.Game;
import Game.Handler;
import Input.Teclado;
import Mapa.Bloque;
import Mapa.Mapa;
import Multijugador.Cliente;
import Multijugador.opciones.Paquete02Move;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import juego.gfx.Assets;


public class Jugador extends Entity{
    //Atributos
    public static final int ANCHO_PREDET=100, ALTO_PREDET=100;
    public static final float VELOCIDAD_PREDET = 4.0f, GRAVITY = 1.0f;
     private int  timer, direction = 1, arSize, flyDir = 1;
     public int vidas=3;
     private float xMove, yMove;
     private float velocidad;
     public boolean  canJump,attacking = false, isMoving = false, isHurt = false;
     private Rectangle ar,cb;
     private boolean falling=true;
     Teclado teclado;
     //Varibles para el salto y caida
     public static int seg=0,dy=0,ds=0,band=1,b2=0,caida=1700,presion=0;
     public int bd=0;
     public boolean existencia=true;
     public int puntos,enemigos;
     //personaje seleccionado
     private String personajeSelec;
     public BufferedImage stock;
     Graphics g;
     //Nombre que tendra el jugador
     private String username;
     //Collisiones con bloques
     public Bloque jbl[];
     private Personaje personaje;
    
     //Constructor
    public Jugador(String personajeSelec, Handler handler, float x, float y, String username){
        super(handler, x, y, ANCHO_PREDET, ALTO_PREDET);
        this.username = username;
        this.personajeSelec = personajeSelec;
        velocidad = VELOCIDAD_PREDET;
        initPersonaje();
    }
    
    //Mover a la derecha o izquierda   
    private void moverX(){
        if (xMove > 0){
            x += xMove;
            xMove--;
        }
        if (xMove < 0){
            x += xMove;
            xMove++;
        }
    }

    //Actualiza las animaciones del personaje
    private void animationUpdate() {
        personaje.animation.start();
        personaje.animation.update();  
    }
    
    //Funcion IMPORTANTE para la colision del Personaje con los demas objetos a interactuar(Como bloques o Enemigos)
     public Rectangle2D get_Personaje(){
      return new Rectangle2D.Double(x,y,100,100);
    }
     
    //Funcion para atacar enemigos
     public Rectangle2D get_Attack(){
      if(direction==1)
        return new Rectangle2D.Double(x+20,y-50,100,100);
      else{
          return new Rectangle2D.Double(x-20,y-50,100,100);
      }
    }
     

    //Control de los jugadores por teclado
    private void getInput(){
       //Control del jugador local
        if (handler != null) {
           y+=dy; 
           seg+=ds;
           //Saltar
           if(handler.getTeclado().jump){
              bd=1;
           }else
             bd=0;
           //Mover izquierda
           if (handler.getTeclado().left && x>0) {
                direction = -1;
                xMove = -velocidad;
                personaje.animation = personaje.getMove();
            }
           //Mover derecha
           if (handler.getTeclado().right && x<810) {
                direction = 1;
                xMove = velocidad;
                personaje.animation = personaje.getMove();
            }
           //Condiciones para salto y caida 
            if(presion==0 && handler.getTeclado().jump && band!=1){
               presion=1;
               dy = -4;
               ds=4;
               personaje.animation=personaje.getJump();
            }
            else if(y<caida && seg==0){
                 dy=4;
                 band=1;
          
            }
            else if(y==caida){
                dy=0;
                band=0;
                presion=0;
            }
            else if(y>caida){
                y-=4;
            }
            if(seg==220){
              seg=ds=0;
              dy=4;
            }
            //Atacar
            if(handler.getTeclado().attack){
               personaje.animation = personaje.getAttack();
               attacking=true;
            }
            if(!handler.getTeclado().attack){
                attacking=false;
            }
            //Estado normal
            if(!handler.getTeclado().left && !handler.getTeclado().right && !handler.getTeclado().jump && !handler.getTeclado().attack){
                personaje.animation = personaje.getIdle();
                
            }
        //Control por multijugador    
        }else if(isMoving) {
            if (direction == -1 ) {
                personaje.animation = personaje.getMove();
            }
            if (direction == 1) {
                personaje.animation = personaje.getMove();
            }
        }
        else if(!isMoving){
            personaje.animation = personaje.getIdle();
        } 
 }
    
    //Actualiza las coordenadas de x
    public void mover() { 
      isMoving=false;
      if (xMove != 0 ) {
        moverX();
        isMoving = true;
      }
    }
    
    //Inicializa personajes con sus respectivas animaciones
    public void initPersonaje(){ 
        switch (personajeSelec){
            case "link": {
                personaje = new Personaje(Assets.linkIdle, Assets.linkAttack, Assets.linkJump, Assets.linkMove,Assets.linkHurt,puntos,enemigos);
                stock = Assets.linkStock;
                ancho = 70;
                alto = 70;
                bounds.x = 18;
                bounds.y = 25;
                bounds.width = 38;
                bounds.height = 40;
                break;
        }
         case "shadow": {
                personaje = new Personaje(Assets.shadowIdle, Assets.shadowAttack, Assets.shadowJump, Assets.shadowMove,Assets.shadowHurt,puntos,enemigos);
                stock = Assets.shadowStock;
                ancho = 70;
                alto = 70;
                bounds.x = 4;
                bounds.y = 12;
                bounds.width = 55;
                bounds.height = 56;
                break;
            }
         case "sonic":{
                personaje = new Personaje(Assets.sonicIdle, Assets.sonicAttack, Assets.sonicJump, Assets.sonicMove,Assets.sonicHurt,puntos,enemigos);
                stock= Assets.sonicStock;
                ancho = 70;
                alto = 70;
                bounds.x = 4;
                bounds.y = 12;
                bounds.width = 55;
                bounds.height = 56;
                break;
         }
         case "mega":{
             personaje = new Personaje(Assets.megaIdle, Assets.megaAttack, Assets.megaJump, Assets.megaMove,Assets.megaHurt,puntos,enemigos);
             stock= Assets.megaStock;
             ancho = 70;
             alto = 70;
             bounds.x = 4;
             bounds.y = 12;
             bounds.width = 55;
             bounds.height = 56;
             break;
         }
     }
    }
    
    //Hilo de jugador
    @Override
    public void update(){
        animationUpdate();
        getInput();
        mover();
        Paquete02Move packet = new Paquete02Move(this.getUsername(), this.getPersonaje(), (int) this.getX(), (int) this.getY(), this.isMoving, this.direction);
        packet.writeData(Game.game.socketCliente);
    
    }

    //Renderizado de graficos
     @Override
    public void render(Graphics g) {
        
        if (direction == 1) {
            g.drawImage(personaje.animation.getSprite(), (int) x, (int) y, ancho, alto, null);
        } else if (direction == -1) {
            g.drawImage(personaje.animation.getSprite(), (int) x + 74, (int) y, -ancho, alto, null);
        }
        if (username != null) {
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.setColor(Color.black);
            g.drawString(username, (int) x - ((username.length() - 1) / 2) * 16, (int) y);
        }
    }

   //Gets y Sets
    public float getxMove(){
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPersonaje() {
        return this.personajeSelec;
    }

    public Personaje getPJ() {
        return this.personaje;
    }

    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public void setDirection(int movingDir) {
        this.direction = movingDir;
    }
    
    public int getdY(){
        return dy;
    }
    public boolean getHurt(){
        return isHurt;
    }
    public void setCanJump(boolean canJump){
        this.canJump=canJump;
    }
    
    public void setHurt(boolean hurt){
        isHurt=hurt;
    }
    
    public int getYs(){
        return (int) y;
    }
}
