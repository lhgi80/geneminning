package test;

import mx.edu.uaeh.files.XMLParser;

/**
 *
 * @author Luis
 */
public class ArchivoXML {
    public static void main(String... args){
        XMLParser xmlLoader=new XMLParser("secuencia.xml");
        xmlLoader.loadFile();
    }
}
