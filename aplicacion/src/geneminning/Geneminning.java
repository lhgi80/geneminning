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
      FileLoader fl=new FileLoader("","archivo.txt");
      TextProcessor tp=new TextProcessor(fl.getText());
      
      
      FileLoader fl1=new FileLoader("", "archivoFormateado.txt");
      fl1.setContenidoArchivo(tp.getTextoFormateado());
      fl1.saveText();
      
      String textoGuardar;
      textoGuardar="\ntexto Formateado:longitud=" + tp.getLongitud()+ ",dimeros=" + tp.getNumeroDimeros() + "\ndimeros:\n";
      
      for(String dimero:tp.getListadoDimeros()){
          textoGuardar+=dimero + ",\n";
      }
      textoGuardar=textoGuardar.substring(0, textoGuardar.length()-2);
      textoGuardar+="\nFrecuencia de los dimeros:\n";
      for(Dimero dimero:tp.getEstadisticasDimeros()){
          textoGuardar+=dimero.getDimero() + "\t" + dimero.getCantidad() + ",\n";
      }
      textoGuardar=textoGuardar.substring(0, textoGuardar.length()-2);
      FileLoader fl2=new FileLoader("", "estadisticas.txt");
      fl2.setContenidoArchivo(textoGuardar);
      fl2.saveText();
      
      
      System.out.println("Press any key to continue...");
      try{
            System.in.read();
       }catch(Exception e){}  
    }
}
