package Mapa;


import Game.Handler;
import Game.entidades.Jugador;
import java.awt.geom.Rectangle2D;
import javafx.scene.media.AudioClip;

import javax.swing.ImageIcon;


public class Bloque {
    public int x,y; 
    public Boolean existencia;
    Handler handler;
    public Bloque(int x,int y,Handler handler){
      this.x=x;
      this.y=y;
      this.existencia=true;
    
      this.handler=handler;
    }
    public Rectangle2D get_Bloque(){//Funcion IMPORTANTE para la colision del bloque con otros objetos interctuables(Como personajes o enemigos)
      return new Rectangle2D.Double(x,y,80,40);
    }
    
    public void Colision(Jugador pj,Boolean colision){
       //Acontinuacion se veran un monton de condiciones,que en de forma simplificada lo que hacen es si: -colisionas con el Bloque -En que parte colsionas con el Bloque
       if(pj.y==get_Bloque().getMaxY() && (pj.x+pj.getAncho()>=x && pj.x+pj.getAncho()<=x+120)){//Si colisionas con el Bloque en su lado inferior entonces sera rompido
         pj.dy=4;
         pj.seg=pj.ds=0;
         if((pj.x+pj.getAncho()>=x+20 && pj.x+pj.getAncho()<=x+80)){
           AudioClip clicked = new AudioClip(this.getClass().getResource("/Sonidos/romper.m4a").toString());
           clicked.play();
           existencia=false;
         }
         if(this instanceof Bloque_Movil)
            existencia=true; 
          
       }  
      if(colision && pj.y==(int) get_Bloque().getMinY()-(pj.getAlto()-10) && pj.x+pj.getAlto()>=x && pj.x+pj.getAlto()<=x+120){ //Si colisionas con el Bloque en su parte superior,estaras posicionado encima de el,como si fuese un piso
           pj.caida=(int) get_Bloque().getMinY()-(pj.getAlto()-10);
           pj.band=0;
           pj.presion=0;
      }     
      else if(!colision || (pj.x+pj.getAncho()<x || pj.x+pj.getAncho()>x+60)) //Sino descenderas 
         pj.caida=1700; 
       if((colision && pj.y!=get_Bloque().getMinY()-(pj.getAlto()-10)) && pj.x+pj.getAncho()>=x && handler.getTeclado().right) //Si colisionas por la izquierda no podras avanzar hacia delante
         pj.x-=4;   
       else if((colision && pj.y!=get_Bloque().getMinY()-(pj.getAlto()-10)) && pj.x+pj.getAncho()<=x+120 && handler.getTeclado().left)//Si colisionas por la derecha no podras avanzar hacia atras
         pj.x+=4;
    }
    
 
}
