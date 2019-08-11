package Game;

import ClasesYObjetos.BotonAtras;
import ClasesYObjetos.ElMain;
import ClasesYObjetos.Terminada;
import ClasesYObjetos.Ventanas;
import Estados.Estado;
import Estados.GameState;
import Game.entidades.JugadorMP;
import Input.Teclado;
import Input.WindowHandler;
import Multijugador.Cliente;
import Multijugador.Servidor;
import Multijugador.opciones.Paquete00Login;
import Registros.Archivo;
import display.Display;
import display.Estadistica;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import juego.gfx.Assets;
import juego.gfx.SpriteLoader;

public class Game  implements Runnable{
    public static Game game;
    //Variables del socket
    private boolean esHost;
    private String ipAddress;
    private String mapaSelected;
    private String username;
    private String personajeSelec;
    Stage stage;
    //Ventana juego
    private Display display;
    public int ancho, alto;
    public String titulo;
    public WindowHandler windowHandler;
    //Hilo
    private Thread thread;
    private boolean running = false;
    Estadistica crear;
    //Paso de variables (Multijugador)
    public static Handler handler;
    public Estado estadoJuego;
    private JugadorMP jugador;
    //Renderizado
    private BufferStrategy bs;
    private Graphics g;
    //Herramientas para multijugador
    public Cliente socketCliente;
    public Servidor socketServidor;
    //Archivos
    public Archivo aplicacion ;
    //Camara
    public Camara cam;
    public int camY;
    Teclado teclado;
    
    //Constructor
     public Game(String titulo, int ancho, int alto, String personajeSelec, boolean esHost, String username,String ipAddress,String mapa,Stage stage) {
        this.ancho = ancho;
        this.alto = alto;
        this.titulo = titulo;
        this.personajeSelec = personajeSelec;
        this.esHost = esHost;
        this.username = username;
        this.ipAddress = ipAddress;
        this.mapaSelected = mapa;
        teclado = new Teclado();
        this.cam=new Camara();
        this.stage=stage;
        aplicacion = new Archivo();

        this.start();
        
    }
     //Inicializar variables
     private void init(){
        game = this;
        display = new Display(titulo, ancho, alto);
        display.getVentana().addKeyListener(teclado);
        Assets.init();
       
        handler = new Handler(this);
        estadoJuego = new GameState(handler);
        Estado.setEstado(estadoJuego);
        this.jugador = new JugadorMP(personajeSelec, handler, 100, 100, username, null, -1);
        handler.getMapa().jugador = this.jugador;
        handler.getMapa().addPlayer(this.jugador);
                if(aplicacion.abrirArchivoLectura(personajeSelec)==1){
                 aplicacion.abrirArchivoEscritura(personajeSelec);
                  handler.getGame().getJugador().getPJ().setPtsGanador("0");
                  handler.getGame().getJugador().getPJ().setPtsPerdedor("0");
                  handler.getGame().getJugador().getPJ().setPtsTotales("0");
                  aplicacion.agregarRegistros(handler.getGame().getJugador().getPJ());
                  aplicacion.cerrarArchivo();
              } 
        display.getVentana().add(handler.getMapa());
        windowHandler = new WindowHandler(this);       
        //Crear Servidor o cliente
        Paquete00Login loginPacket = new Paquete00Login(this.jugador.getUsername(), this.jugador.getPersonaje());
        if (socketServidor != null) {
            socketServidor.addConnection((JugadorMP) this.jugador, loginPacket);
        }
        loginPacket.writeData(socketCliente);
     }
     
     private void update(){
          teclado.update();
        if (Estado.getEstado() != null) {
            Estado.getEstado().update();
        }
      }
    
     //Renderizado
    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        if (Estado.getEstado() != null) {
            Estado.getEstado().render(g);
        }
        bs.show();
        g.dispose();
    }
    
     @Override
    public void run() {
        init();

        int fps = 70;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running && jugador.vidas!=0 && handler.getMapa().level!=4 && !handler.getTeclado().escape){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            if (delta >= 1) { //delta=1 significa que ha pasado 1s/60
                update();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) { //ha pasado 1s
                display.getVentana().setTitle("DumbyApp | fps: " + ticks);
                ticks = 0;
                timer = 0;
            }
         camY=(int) cam.get_Camara(0, (int) (jugador.y-400)).getMinY();
         handler.getMapa().min_i=cam.min_bl(handler.getMapa().bl);
         handler.getMapa().max_i=cam.max_bl(handler.getMapa().bl);
         display.getCanvas().setSize(900,1800);
         display.getCanvas().setLocation(0,-camY); 
         
         //Fin del juego
          if(jugador.vidas==0 || handler.getMapa().level==4 || handler.getTeclado().escape){
              running=false; 

              
              if(jugador.vidas==0 || handler.getTeclado().escape){
                if(aplicacion.abrirArchivoLectura(personajeSelec)==0){
                  aplicacion.abrirArchivoLectura(personajeSelec);
                  aplicacion.leerRegistros();
                  handler.getGame().getJugador().getPJ().setPtsGanador("0");
                  handler.getGame().getJugador().getPJ().setPtsPerdedor(Integer.toString(handler.getGame().getJugador().getPJ().getPuntos()));
                  handler.getGame().getJugador().getPJ().setPtsTotales(Integer.toString(handler.getGame().getJugador().getPJ().getPuntos()));
                  aplicacion.cerrarArchivo();
               }
          } else if(handler.getMapa().level==4){
                  if(aplicacion.abrirArchivoLectura(personajeSelec)==0){
                  aplicacion.abrirArchivoLectura(personajeSelec);
                  aplicacion.leerRegistros();
                  handler.getGame().getJugador().getPJ().setPtsGanador(Integer.toString(handler.getGame().getJugador().getPJ().getPuntos()));
                  handler.getGame().getJugador().getPJ().setPtsPerdedor("0");
                  handler.getGame().getJugador().getPJ().setPtsTotales(Integer.toString(handler.getGame().getJugador().getPJ().getPuntos()));
                  aplicacion.cerrarArchivo();
              } 
             }
              aplicacion.abrirArchivoEscritura(personajeSelec);
              aplicacion.agregarRegistros(handler.getGame().getJugador().getPJ());
              aplicacion.cerrarArchivo();
              display.getVentana().dispose();
              Estadistica est= new Estadistica("Estadistica",900,600,handler.getGame().getJugador().getPJ().getPuntos(),handler.getGame().getJugador().getPJ().getEnemigos(),aplicacion.getPtosGanadas(),aplicacion.getPtosPerdidas(),aplicacion.getPtosTotales());
          }    
        }
      stop();    
    }
    
    public synchronized void start(){
        if (running) {
            return;
        }
        running = true;
        if (esHost) {
            socketServidor = new Servidor(this);
            socketServidor.start();
        }
        socketCliente = new Cliente(this, ipAddress);//Ip del anfintrion
        socketCliente.start();
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop() {
        if (!running) {
            return;             
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    //Get y Set
    public Teclado getTeclado() {
        return teclado;
    }
    
    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
    
    public Display getDisplay() {
        return display;
    }
    
     public Handler getHandler() {
        return handler;
    }
     
    public JugadorMP getJugador() {
        return jugador;
    }
    

}
