package display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Ventana finalizar partida
public class Estadistica {
    public JFrame ventana;
    public String titulo;
    public int ancho, alto;
    JLabel etiqueta;
    ImageIcon imagen;
    JLabel puntos;
    JLabel enemigos;
    JPanel panel;
    int pts,ene;
    String ptsG,ptsP,ptsT;
     public Estadistica(String titulo, int ancho, int alto,int puntos,int enemigos,String ptsG,String ptsP,String ptsT) {
        this.titulo = titulo;
        this.ancho = ancho;
        this.alto = alto;
        this.pts=puntos;
        this.ene=enemigos;
        this.ptsG=ptsG;
        this.ptsP=ptsP;
        this.ptsT=ptsT;
        crearDisplay();
    }
     
     public void crearDisplay(){
        ventana = new JFrame(titulo);
        ventana.setSize(ancho, alto);
        ventana.setTitle(titulo);
        ventana.setLocationRelativeTo(null);
        panel=new JPanel();
        panel.setLayout(null);
        ventana.getContentPane().add(panel);
        
        
        puntos= new JLabel(Integer.toString(pts));
        puntos.setBounds(440, 290, 50, 30); 
        puntos.setFont(new Font("Arial", Font.BOLD, 40));
        panel.add(puntos);
        
        enemigos= new JLabel(Integer.toString(ene));
        enemigos.setBounds(440, 470, 50, 30);
        enemigos.setFont(new Font("Arial", Font.BOLD, 40));
        panel.add(enemigos);
        etiqueta= new JLabel(new ImageIcon("final2.jpg"));
        etiqueta.setBounds(0, 0, 900, 600);
        panel.add(etiqueta);
 
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
     }
 
}
