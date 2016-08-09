package test;

import mx.edu.uaeh.files.XMLLoader;

/**
 *
 * @author Luis
 */
public class ArchivoXML {
    public static void main(String... args){
        XMLLoader xmlLoader=new XMLLoader("secuencia.xml");
        xmlLoader.loadFile();
    }
}
