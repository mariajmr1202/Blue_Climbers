package Game;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import Mapa.Bloque;
import javax.swing.JPanel;


public class Camara {
    //Atributos
    int x,y,ancho=900,largo=500;
    
   //Metodos    
    public int min_bl(Bloque[] bl){
     int i=0;
     for(i=0;i<bl.length && !get_Camara(x,y).intersects(bl[i].get_Bloque());i++);
     return i;
    }
    public int max_bl(Bloque[] bl){
      int i; 
      for(i= min_bl(bl);i<bl.length && get_Camara(x,y).intersects(bl[i].get_Bloque());i++);
      

      if(i!=bl.length)
       return i;
      return i-1;
    }
    
    public Rectangle2D get_Camara(int x,int y){
       this.x=x; this.y=y;  
       return new Rectangle2D.Double(x,y,ancho,largo);
    }
   
}
