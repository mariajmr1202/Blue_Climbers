package display;

import Game.Game;
import Mapa.Mapa;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

//Ventana Modo juego
public class Display {
    public JFrame ventana;
    public String titulo;
    public int ancho, alto;
    public Canvas canvas;
    public Mapa map;
    public Display(String titulo, int ancho, int alto) {
        this.titulo = titulo;
        this.ancho = ancho;
        this.alto = alto;
        
        crearDisplay();
    }
    
    public void crearDisplay() {
        ventana = new JFrame(titulo);
        ventana.setSize(ancho, alto);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        canvas = new Canvas();
        ventana.add(canvas);
       
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getVentana() {
        return ventana;
    }
}
