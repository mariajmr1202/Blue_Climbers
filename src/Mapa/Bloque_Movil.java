package Mapa;

import Game.Handler;
import Game.entidades.Jugador;

public class Bloque_Movil extends Bloque{
    int lx,ly,timer=0;
    Handler handler;
    double dx=0,dy=0;
    public Bloque_Movil(int x, int y,Handler handler) {
        super(x, y,handler);
        lx=x;
        ly=y;
    }
    public void Mover_Pj(Jugador pj,Boolean colision){//Mueve el personaje junto con la plataforma
      if(pj.x+40>=x && colision){
        if(timer%2==0){  
         pj.x+=dx;
         pj.y+=dy;
        } 
      }   
    }
    //Las demas funciones se encargan de hacer lo mismo,mover la plataforma pero cada una en un sentido diferente
    public void MoverX_D(Jugador pj,Boolean colision){
        if(timer==200)
           timer=0; 
        timer++;
        Mover_Pj(pj,colision); 
        if(timer%2==0)
          x+=dx;
       if(x==lx+120)
         dx=-4;
       else if(x==lx)
                dx=4;  
    }
    public void MoverX_I(Jugador pj,Boolean colision){
        if(timer==200)
         timer=0; 
        timer++;
        Mover_Pj(pj,colision);
        if(timer%2==0)
          x+=dx;
       if(x==lx-120)
         dx=4;
       else if(x==lx)
                dx=-4;  
    }
    public void MoverY(Jugador pj,Boolean colision){
       if(timer==200)
        timer=0;  
       timer++;
       Mover_Pj(pj,colision);
       if(timer%2==0)
        y+=dy;
       if(y==ly)
        dy=-4;
       else if(y==ly-120)
        dy=4;   
             
    }
}
