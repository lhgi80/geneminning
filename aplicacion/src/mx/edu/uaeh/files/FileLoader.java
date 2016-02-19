/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uaeh.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author Luis
 */
public class FileLoader {
    private String ruta;
    private String nombreArchivo;
    private String rutaArchivo;

    public FileLoader(String ruta, String nombreArchivo) {
        this.ruta = ruta;
        this.nombreArchivo = nombreArchivo;
        rutaArchivo=ruta + nombreArchivo;
    }
    
    

    @SuppressWarnings("empty-statement")
    public String getText(){
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      String textoArchivo="";
      
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (rutaArchivo);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
 
         // Lectura del fichero
         
         String linea;
         while((linea=br.readLine())!=null){
          textoArchivo+=linea;
        }
         
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
      return textoArchivo;
    }
}
