/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dnaMining.BD.Clases.Dimero;
import dnaMining.FileProcessing.FileLoader;
import dnaMining.FileProcessing.TextProcessor;

/**
 *
 * @author Luis
 */
public class Geneminning {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      FileLoader fl=new FileLoader("","ejemplo1.txt");
      TextProcessor tp=new TextProcessor(fl.getText());
      String textoFormateado=tp.getTextoFormateado();
      int longitud=textoFormateado.length();
      String[] dimeros=tp.getListadoDimeros();
      int longitudDimeros=dimeros.length;
      
      System.out.println("\ntexto Formateado:longitud=" +longitud + ",dimeros=" + longitudDimeros + "\n" + tp.getTextoFormateado());
      System.out.println("dimeros:");
      int i=0;
      for(String dimero:dimeros){
          System.out.println((i++) + "->" + dimero);
      }
      System.out.println("\nFrecuencia de los dimeros:");
      
      
      
    }
}
