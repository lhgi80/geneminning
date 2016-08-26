package test;

import BD.Conexion;
import dnaMining.FileProcessing.XMLParser;

/**
 *
 * @author Luis
 */
public class ArchivoXML {
    public static void main(String... args){
        Conexion con= new Conexion("mysql", "localhost", "dnaseq", "root", "");
        XMLParser xmlLoader=new XMLParser("zika.xml");
        
        xmlLoader.loadFile();
    }
}
