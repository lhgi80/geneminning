package mx.edu.uaeh.files;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
/**
 *
 * @author Luis
 */
public class XMLLoader {
    private String fileName;

    public XMLLoader() {
        fileName="";
    }
    public XMLLoader(String loader) {
        this.fileName = loader;
    }
    
    public void loadFile(){
        try {

           File fXmlFile = new File(fileName);
           DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
           Document doc = dBuilder.parse(fXmlFile);

           //optional, but recommended
           //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
           doc.getDocumentElement().normalize();
           
           // Lee cada elemento del listado
           NodeList listadoNodos=doc.getElementsByTagName("INSDSeq");
           for(int iNodo=0;iNodo<listadoNodos.getLength();iNodo++){
               Node nodo=listadoNodos.item(iNodo);
               
               Element elemento=(Element)nodo;
               String indice=elemento.getElementsByTagName("INSDSeq_locus").item(0).getTextContent();
           }
           
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
