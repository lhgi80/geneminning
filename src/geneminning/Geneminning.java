/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneminning;

import mx.edu.uaeh.files.Dimero;
import mx.edu.uaeh.files.FileLoader;
import mx.edu.uaeh.files.TextProcessor;
import mx.edu.uaeh.web.Web;

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
      //System.out.println("\ntexto Formateado:longitud=" + tp.getLongitud()+ ",dimeros=" + tp.getNumeroDimeros() + "\n" + tp.getTextoFormateado());
      System.out.println("dimeros:");
      for(String dimero:tp.getListadoDimeros()){
          System.out.println(dimero);
      }
      System.out.println("\nFrecuencia de los dimeros:");
//      for(Dimero dimero:tp.getFrecuenciaDimeros()){
//          System.out.println(dimero.getDimero() + "->" + dimero.getCantidad());
//      }
    }
}
