package Mapa;

import Game.Handler;
import Game.entidades.Jugador;
import java.awt.geom.Rectangle2D;


public class Enemigo {
    int x,y,minX,maxX=0,ly,dx=0,dy=0,timer=0,ANCHO,LARGO,band=0,i=0,min_i=0,px,py,dr=0,m=1,cont=0;
    
    Boolean existencia=true;
    public Enemigo(int x, int y) {
        this.minX=this.x = x;
        this.ly=this.y = y;
        this.ANCHO=80;
        this.LARGO=90;
    }
    
    public Rectangle2D get_Enemigo(){//Funcion IMPORTANTE para la colison del enemigos con otros objetos Interactuables(como Personjaes o Bloques)
      
      return new Rectangle2D.Double(x,y,ANCHO,LARGO);
    }
    public int Posicion(Bloque [] bl,int max){//Funcion que retorna la posicion inicial de la ruta del enemigo
      int i=0;
      for(i=0;i<=max && !get_Enemigo().intersects(bl[i].get_Bloque());i++); 
    
      return i;
    }
    public int Ruta(Bloque [] bl,int max){
       if(band!=1){//Bandera que impide que se actualize la posicion inicial
         i=min_i=Posicion(bl,max);
    
         if(i!=62){
          band=1;
          dx=4*(m);
         } 
       }
      //Si m==1 significa que el enemigo se mueve de izquierda a derecha
      //Si m==-1 significa que el enemigo se mueve de derecha a izquierda
      if(m==1){
          
        for(i=min_i;i<=max && !get_Enemigo().intersects(bl[i].get_Bloque());i++){    /*ojo error al romper bloque*/   
           if(!bl[i].existencia){
               if(i+1!=105)
                minX=bl[min_i=i+1].x;
           }    
        }
      }else if(m==-1){
        if(min_i==105){
           min_i-=1;
           x-=20;
        }
        for(i=min_i;i>=0 && !get_Enemigo().intersects(bl[i].get_Bloque());i--){
           if(!bl[i].existencia){
               if(i-1!=-1)
                minX=bl[min_i=i-1].x;
           }    
        }
  
      }
        if(i==min_i && min_i-1!=-1 && !bl[min_i-1].existencia && min_i+1!=105 && !bl[min_i+1].existencia){
          dx=0;  
        }
        if(i!=-1 && i!=105 && !bl[i].existencia){
          band=0;
          dx=0;
        }  
        if(band==0)
          existencia=false;  
        if(m==1){
         int band=0;   
         for(i=min_i;i<=max && band!=1 && bl[i].existencia;i++){
           if(i!=0 && i%11==0){
              band=1;
           }
         }
         
          
          this.maxX=(int) bl[i-1].get_Bloque().getMaxX()-120;
        }else if(m==-1){
         for(i=min_i;i>=0 && bl[i].existencia;i--);
         this.maxX=(int) bl[i+1].get_Bloque().getMaxX()+40;
              
        }

       return maxX;
    }
    
    public void Atacar(Jugador pj){
        if(pj.attacking==true){
           if(pj.get_Attack().intersects(get_Enemigo())){
                existencia=false;
                pj.attacking=false;
                pj.getPJ().setPuntos(10);
                System.out.println("Puntos: "+pj.getPJ().getPuntos());
                pj.getPJ().setEnemigos(1);
              } 
        }
        

        
      if(get_Enemigo().intersects(pj.get_Personaje()) && pj.attacking==false){
          pj.setHurt(true);
      }
      if(pj.getHurt()){
       pj.getPJ().animation=pj.getPJ().getHurt();
       pj.setHurt(false);
       pj.existencia=false;
      }  
    }
    
    public void Mover(Bloque [] bl,int max){
        maxX=0;
        Ruta(bl,max);
      if(timer==200)
         timer=0;
      timer++;
      if(timer%2==0){
        x+=dx;  
      }
      if(x==maxX){
        dx=-4*m;     
      }  
      else if(x==minX){
         dx=4*m;
      } 
    }
}

