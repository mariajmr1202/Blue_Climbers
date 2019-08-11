package Registros;

import Game.entidades.Personaje;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Archivo{
    //Atributos
    private Scanner entrada;
    private Formatter salida; //Objeto usado para enviar texto al archivo
    public String ptosGanadas="0",ptosPerdidas="0",ptosTotales="0";
    public int ptosg,ptosp,ptost;
    //Metodos
     public void abrirArchivoEscritura(String personaje) {
         try {
                salida = new Formatter(personaje+"_datos.txt");
         } 
         catch (SecurityException securityException) {
                System.err.println("No tiene acceso de escritura a este archivo");
                System.exit(1);
          }
        catch (FileNotFoundException fileNotFoundException) {
                System.err.println("Error al crear archivo");
                System.exit(1);
          }
    }
     
     public void agregarRegistros(Personaje obj){
         ptosg= Integer.parseInt(this.getPtosGanadas())+Integer.parseInt(obj.getPtsGanador());   
         ptosGanadas=Integer.toString(ptosg);
         ptosp=Integer.parseInt(this.getPtosPerdidas())+Integer.parseInt(obj.getPtsPerdedor());
         ptosPerdidas=Integer.toString(ptosp);
         ptost=Integer.parseInt(this.getPtosTotales())+Integer.parseInt(obj.getPtsTotales());
         ptosTotales=Integer.toString(ptost);
         salida.format("%s %s %s\n",ptosGanadas,ptosPerdidas,ptosTotales);
    }
     
     public int abrirArchivoLectura(String personaje) {
        try {
            entrada = new Scanner(new File(personaje+"_datos.txt"));
            return 0;
        }catch (FileNotFoundException fileNotFoundException) {
                ptosGanadas="0";
                ptosPerdidas="0";
                ptosTotales="0";
                return 1;
          }
    }
     
    public void leerRegistros(){
        System.out.printf("%-15s%-15s%-10s\n", "Ptos Ganadas", "Ptos Perdidas", "Pts Totales");
        try{
        while (entrada.hasNext()){
            System.out.println("mari");
            ptosGanadas=entrada.next();
            ptosPerdidas=entrada.next();
            ptosTotales=entrada.next();
            
          //Muestra contenido de registro
          System.out.printf("%-15s%-15s%-10s\n",ptosGanadas,ptosPerdidas,ptosTotales);
          this.setPtosGanadas(ptosGanadas);
          this.setPtosPerdidas(ptosPerdidas);
          this.setPtosTotales(ptosTotales);
         }
        }catch (NoSuchElementException elementException) {
                System.err.println("El archivo no esta bien formado");
                entrada.close();
                System.exit(1);
          }
        catch (IllegalStateException stateException) {
                System.err.println("Error al leer archivo");
                System.exit(1);
          }
        
        
      }
    
    //Cierra Archivo
    public void cerrarArchivo(){
        if (salida != null)
            salida.close();
    }

    public String getPtosGanadas() {
        return ptosGanadas;
    }

    public String getPtosPerdidas() {
        return ptosPerdidas;
    }

    public String getPtosTotales() {
        return ptosTotales;
    }

    public void setPtosGanadas(String ptosGanadas) {
        this.ptosGanadas = ptosGanadas;
    }

    public void setPtosPerdidas(String ptosPerdidas) {
        this.ptosPerdidas = ptosPerdidas;
    }

    public void setPtosTotales(String ptosTotales) {
        this.ptosTotales = ptosTotales;
    }
    
    
}
