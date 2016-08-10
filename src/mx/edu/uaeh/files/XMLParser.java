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
public class XMLParser {
    private String fileName;

    public XMLParser() {
        fileName="";
    }
    public XMLParser(String loader) {
        this.fileName = loader;
    }
    
    public void loadFile(){
        try {

           File fXmlFile = new File(fileName);
           DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
           Document doc = dBuilder.parse(fXmlFile);
           
           doc.getDocumentElement().normalize();
           
           // Lee cada elemento del listado
           NodeList listadoNodos=doc.getElementsByTagName("INSDSeq");
           for(int iNodo=0;iNodo<listadoNodos.getLength();iNodo++){
               Node nodo=listadoNodos.item(iNodo);
               
               Element elemento=(Element)nodo;
               String indice=elemento.getElementsByTagName("INSDSeq_locus").item(0).getTextContent();
               String nombre=elemento.getElementsByTagName("INSDSeq_definition").item(0).getTextContent();
               String topology=elemento.getElementsByTagName("INSDSeq_topology").item(0).getTextContent();
               String taxonomy=elemento.getElementsByTagName("INSDSeq_taxonomy").item(0).getTextContent();
               long longitud=Long.parseLong(elemento.getElementsByTagName("INSDSeq_length").item(0).getTextContent());
               String organism=elemento.getElementsByTagName("INSDSeq_organism").item(0).getTextContent();
               String molType=elemento.getElementsByTagName("INSDSeq_moltype").item(0).getTextContent();
               String secuencia=elemento.getElementsByTagName("INSDSeq_sequence").item(0).getTextContent();
               
               
               
               // revisa si tiene publicaciones que le respalden
               Node referencias=elemento.getElementsByTagName("INSDSeq_references").item(0);
               Element refs=(Element)referencias;
               NodeList listadoReferencias=refs.getElementsByTagName("INSDReference");
               boolean publicado=false;
               String journal="";
               for(int iPublicaciones=0;iPublicaciones<listadoReferencias.getLength();iPublicaciones++){
                   Node publi=listadoReferencias.item(iPublicaciones);
                   Element publicacion=(Element)publi;
                   journal=publicacion.getElementsByTagName("INSDReference_journal").item(0).getTextContent();
                   if(journal.indexOf("unpublished")!=-1 && journal.indexOf("submitted")!=-1){
                       publicado=true;
                   }
               }
               System.out.println("terminado");
           }
           
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
