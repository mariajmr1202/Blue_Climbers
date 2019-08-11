package ClasesYObjetos;

import java.awt.Color;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Inicio{
    JFrame ventana;
    JButton boton;
    public Inicio(){
        CrearVentana();
    }
  
    public void CrearVentana(){
       ImageIcon icon=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/join-button.png")).getImage());
       boton= new JButton();
       boton.setSize(150, 29);
       boton.setIcon(icon);
       boton.setLocation(200, 200);
       ventana=new JFrame("DumbyApp");
       ventana.setSize(600,600);
       ventana.setLocationRelativeTo(null);//ventana centrada en pantalla
       ventana.setDefaultCloseOperation(ventana.EXIT_ON_CLOSE);//para que se cierre al salir
       ventana.setResizable(false);//no se puede cambiar de tamaño la ventana
       ventana.setLayout(null);
       ventana.getContentPane().setBackground(Color.black);//color del fondo de la ventana
       ventana.setVisible(true);//hace que la ventana sea visible
       ventana.add(boton);
       
   
        
    }

 
}
