package Mapa;


import Game.Handler;
import Game.entidades.Entity;
import Game.entidades.EntityManager;
import Game.entidades.Jugador;
import Game.entidades.JugadorMP;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import juego.gfx.Assets;
import juego.gfx.SpriteLoader;


public class Mapa extends JPanel{
    public int ancho, alto,i=0,min_i,max_i,level=1,timer=0;
    private int spawnX=40, spawnY=1700,xs,ys;
    public Jugador jugador;
    public Jugador pj2;
    public Enemigo rb=new Enemigo(500,1560);
    public Enemigo rb1[];
    public Bloque bl[];
    public Handler handler;
    public int linea[]= new int[100];
    public EntityManager entityManager;
    public int nump=0;
    Boolean exist;
    int j=0;
    JLabel puntos;
    Graphics g2,g3;       
    ImageIcon image= new ImageIcon(getClass().getResource("/res/textures/Background.png"));
    public Mapa(Handler handler) {
        int j=0,k=1640;//Posiciones X(J=0) y Y(k=890) donde se van a generar el primer piso de Bloques
        this.handler=handler;
        entityManager = new EntityManager(handler);
        bl=new Bloque[62];
        for(i=0;i<bl.length;i++){ //Inicializa el arreglo de bloques
         bl[i]=new Bloque(j,k,handler);
         j+=80;//Cada bloque de cada piso esta separado por una distancia de 35
         if(i!=0 && i%11==0){//Al llegar al extremo del JPanel,significa que se a completado un piso,pasa a generar al siguiente
           j=0;
           k-=180;
         }
    }
      bl[59]=new Bloque_Movil(400,600,handler);
      bl[60]=new Bloque_Movil(680,600,handler);
      bl[61]=new Bloque_Movil(340,340,handler);
      Assets.background = Assets.backgroundSnow;
      rb1=new Enemigo[5];
      rb1[0]=new Enemigo(40,1560);
      rb1[0].m=1;
      rb1[1]=new Enemigo(40,1380);
      rb1[1].m=1;
      rb1[2]=new Enemigo(780,1200);
      rb1[2].m=-1;
      rb1[3]=new Enemigo(40,1020);
      rb1[3].m=1;
      rb1[4]=new Enemigo(780,840);
      rb1[4].m=-1;
    }
    
    //Insertar jugadores a la partida
    public void addPlayer(Jugador jugador) {
        entityManager.addEntity(jugador);
        jugador.setX(spawnX);
        jugador.setY(spawnY);
        nump=nump+1;
        if(nump==2)
           pj2=jugador;
    }
    
    //Eliminar jugadores de la partida
    public void removePlayer(String username) {
        int index = 0;
        for (Entity e : entityManager.getEntities()) {
            if (e instanceof JugadorMP && ((JugadorMP) e).getUsername().equals(username)) {
                break;
            }
            index++;
        }
        this.entityManager.getEntities().remove(index);
    }
    
     //Movimiento que se envian por el socket para actualizar animaciones
    public void movePlayer(String username, int x, int y,boolean isMoving,int movingDir) {
        jugador.x = x;
        jugador.y = y;
        jugador.setIsMoving(isMoving);
        jugador.setDirection(movingDir);
        if(pj2!=null){
           RevisarMP(pj2,bl);
       }  
    }
    
    public void Llenar(){

        linea[0]=5;
        linea[1]=7;
        for(int i=2;i<36;i++){
            linea[i]=0;
        }
    }
    
    public void Destruir(int i,Boolean exist){ 
        bl[i].existencia=exist;
    }
    public void update() {
        entityManager.update();
    }
    
    public int RevisarMP(Jugador pj,Bloque[] bl){
        for(int i=min_i;i<=max_i;i++){
         if(pj.y==bl[i].get_Bloque().getMaxY() && (pj.x+pj.getAncho()>=bl[i].x+20 && pj.x+pj.getAncho()<=bl[i].x+80)){
            bl[i].existencia=false; 
            return i;
         }
        
        }
        return bl.length;
    }
    public int Revisar(Jugador pj,Bloque [] bl){//Esta funcion se encarga de revisar que Bloque esta cerca del Personaje,la pos de ese bloque cercano en el arreglo es retornado(No importa si colisiona o no)
       for(int i=min_i;i<=max_i;i++){
        if(bl[i].existencia){
         if(bl[i] instanceof Bloque_Movil){
            if(pj.x+pj.getAncho()>=bl[i].x-80 && pj.x<=bl[i].x+80 && pj.y>=bl[i].y-80 && pj.y<=bl[i].y)
              return i;  
         }   
         if(i-1!=-1 && i+1!=bl.length){
           if(!bl[i-1].existencia && (!bl[i+1].existencia || bl[i+1] instanceof Bloque_Movil)){
             if(pj.x+pj.getAncho()>=bl[i].x-80 && pj.x<=bl[i].x+80 && pj.y>=bl[i].y-80 && pj.y<=bl[i].y){
               return i;
             }
           }
           else if(!bl[i+1].existencia || bl[i+1] instanceof Bloque_Movil ){
             if(pj.x+pj.getAncho()>=bl[i].x && pj.x<=bl[i].x+80 && pj.y>=bl[i].y-80 && pj.y<=bl[i].y+90)
                return i;   
           }
           else if(!bl[i-1].existencia || bl[i-1] instanceof Bloque_Movil){
             if(pj.x+pj.getAncho()>=bl[i].x-80 && pj.x<=bl[i].x && pj.y>=bl[i].y-80 && pj.y<=bl[i].y)
                return i; 
           }
         }
         if(pj.x+pj.getAncho()>=bl[i].x && pj.x<=bl[i].x+50 && ((pj.y<=bl[i].y-60 && pj.y>=bl[i].y-80) || (pj.y>=bl[i].y+20 && pj.y<=bl[i].y+60))){  
           return i;
         }
        }      
       }
        return bl.length;//Si sale del For entonces sera retornado el tamaño del arreglo(Significando que no hay nigun Bloque cercano)
    
    }
    public void Nivel(int lv){
     if(lv==2){
       for(i=0;i<rb1.length;i++)
          rb1[i].existencia=false; 
       for(i=0;i<bl.length;i++){
            if(i%2==0 || bl[i] instanceof Bloque_Movil)
             bl[i].existencia=true;
            else
            bl[i].existencia=false;
       }
       bl[61].existencia=true;
       bl[61].y+=20;
     }     
     else if(lv==3){
       for(i=0;i<bl.length;i++)
         bl[i].existencia=false;  
       for(i=0;i<=4;i++)
         bl[i].existencia=true;
       for(i=18;i<=24;i++){
         bl[i].existencia=true;
       }
       for(i=24;i<=27;i++){
         bl[i].existencia=true;
       }
       for(i=35;i<=40;i++){
         bl[i].existencia=true;
       }
       bl[41]=new Bloque_Movil(600,1000,handler);
       bl[42]=new Bloque_Movil(300,800,handler);
       bl[43]=new Bloque_Movil(700,700,handler);
       bl[61].existencia=true;
       bl[61].y=800;

         System.out.println(bl[60].y);
     }
    
    }
    
    //Pasa de nivel/cambia de mundo
    public void Passlevel(Jugador pj){
       if(pj.y==(int) bl[61].get_Bloque().getMinY()-(pj.getAlto()-10) && pj.x+pj.getAlto()>=bl[61].x && pj.x+pj.getAlto()<=bl[61].x+120){
        pj.y=1700;
        System.out.println(bl[60].x);
        Jugador.caida=1700;
        Jugador.presion=0;
        Jugador.seg=0;
        Jugador.dy=0;
        Jugador.band=0;
        Assets.background=Assets.backgroundSnow2;
        Assets.bloque=Assets.bloque2;
        level++;
        jugador.getPJ().setPuntos(20);
        System.out.println("Puntos: "+jugador.getPJ().getPuntos());

        if(level<4)
         Nivel(level);  
      }
      
    //Actualizar componentes en mapa
    }
    public void render(Graphics g) {   
      super.paintComponent(g);
      g.drawImage(Assets.background,0,0,null);
      g2=g;
      g3=g;
      Passlevel(jugador);
      if(pj2!=null)
        Passlevel(pj2);  
      for(int i=min_i;i<=max_i;i++){//Dibuja los Bloques tanto estaticos oomo moviles(Si es que su valor de existencia es true)
      if(bl[i].existencia && i!=61)
       g.drawImage(Assets.bloque,bl[i].x,bl[i].y,80,40,null);
      else if(bl[i].existencia)
       g.drawImage(Assets.bloqueganar,bl[i].x,bl[i].y,80,40,null);   
      if(bl[i] instanceof Bloque_Movil){
       if(i==59)  
        ((Bloque_Movil)bl[i]).MoverX_D(jugador,jugador.get_Personaje().intersects(bl[i].get_Bloque()));
       if(i==60)
        ((Bloque_Movil)bl[i]).MoverY(jugador,jugador.get_Personaje().intersects(bl[i].get_Bloque()));
       if(i==61)
         ((Bloque_Movil)bl[i]).MoverX_D(jugador,jugador.get_Personaje().intersects(bl[i].get_Bloque()));
       if(level==3){
         if(i==41)
         ((Bloque_Movil)bl[i]).MoverY(jugador,jugador.get_Personaje().intersects(bl[i].get_Bloque()));  
         if(i==42)
         ((Bloque_Movil)bl[i]).MoverX_D(jugador,jugador.get_Personaje().intersects(bl[i].get_Bloque()));
         if(i==43)
         ((Bloque_Movil)bl[i]).MoverX_D(jugador,jugador.get_Personaje().intersects(bl[i].get_Bloque()));   
       }
      }  
      }
      if((this.i=Revisar(jugador,bl))<=bl.length-1){
       bl[i].Colision(jugador,jugador.get_Personaje().intersects(bl[i].get_Bloque()));//Revisa la colision que tiene el Personaje con el Bloque cercano retornado
      }else
        jugador.caida=1700;

      for(i=0;i<rb1.length;i++){
       if(rb1[i].existencia){
        if(rb1[i].dx==4)   
        g.drawImage(Assets.robotder,rb1[i].x,rb1[i].y,this);
         else if(rb1[i].dx==-4)
          g.drawImage(Assets.robotizq,rb1[i].x,rb1[i].y,this);   
        rb1[i].Mover(bl,62);
        rb1[i].Atacar(jugador);
       } 
      }
       if(!jugador.existencia){
          timer++;
          if(timer==200){
           jugador.x=100;
           jugador.y=1700;
           jugador.existencia=true;
           timer=0;
           jugador.setHurt(false);
           jugador.vidas--;
           System.out.println("Le quedan: "+jugador.vidas+" vidas");
         } 
       }   
      if(jugador.existencia)
        entityManager.render(g);
      
      g2.setFont(new Font("Arial", Font.BOLD, 40));
      g2.setColor(Color.white);
      g2.drawString("Puntos: "+Integer.toString(jugador.getPJ().getPuntos()), 30, jugador.getYs()-360);
      g3.setFont(new Font("Arial", Font.BOLD, 40));
      g3.setColor(Color.white);
      g3.drawString("Vidas:"+Integer.toString(jugador.vidas), 700, jugador.getYs()-360);
    }
       
    
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public int getSpawnX() {
        return spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }
}
