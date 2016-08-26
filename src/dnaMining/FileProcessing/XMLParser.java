package dnaMining.FileProcessing;
import dnaMining.BD.Clases.Dimero;
import dnaMining.BD.Clases.ProyectoSecuencia;
import dnaMining.BD.Clases.Secuencia;
import dnaMining.BD.Dimeros;
import dnaMining.BD.Divisiones;
import dnaMining.BD.ProyectosSecuencia;
import dnaMining.BD.Secuencias;
import dnaMining.BD.Strands;
import dnaMining.BD.TiposMolecula;
import dnaMining.BD.Topologias;
import java.util.ArrayList;
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
                String folio=elemento.getElementsByTagName("INSDSeq_locus").item(0).getTextContent();
                String nombre=elemento.getElementsByTagName("INSDSeq_definition").item(0).getTextContent();
                String topology=elemento.getElementsByTagName("INSDSeq_topology").item(0).getTextContent();
                String division=elemento.getElementsByTagName("INSDSeq_division").item(0).getTextContent();
                String taxonomy=elemento.getElementsByTagName("INSDSeq_taxonomy").item(0).getTextContent();
                String strand=elemento.getElementsByTagName("INSDSeq_strandedness").item(0).getTextContent();
                int longitud=Integer.parseInt(elemento.getElementsByTagName("INSDSeq_length").item(0).getTextContent());
                String organism=elemento.getElementsByTagName("INSDSeq_organism").item(0).getTextContent();
                String molType=elemento.getElementsByTagName("INSDSeq_moltype").item(0).getTextContent();
                String secuencia=elemento.getElementsByTagName("INSDSeq_sequence").item(0).getTextContent();

                // Revisa la tabla de features
                Node featureTable=elemento.getElementsByTagName("INSDSeq_feature-table").item(0);
                Element feats=(Element)featureTable;

                NodeList features=feats.getElementsByTagName("INSDFeature");
                Node feat=features.item(0);
                Element featureList=(Element)feat;

                NodeList Quals=featureList.getElementsByTagName("INSDFeature_quals");
                Node feature=Quals.item(0);
                Element eFeat=(Element)feature;

                NodeList nlFeatures=eFeat.getElementsByTagName("INSDQualifier");
                String tipoMolecula="";
                String numTaxonomia="";
                for(int iFeats=0;iFeats<nlFeatures.getLength();iFeats++){
                    Node itemFeature=nlFeatures.item(iFeats);
                    Element eItem=(Element)itemFeature;
                    String sItem=eItem.getElementsByTagName("INSDQualifier_name").item(0).getTextContent();
                    String sValor="";
                    if(eItem.getElementsByTagName("INSDQualifier_value").getLength()>0){
                        sValor=eItem.getElementsByTagName("INSDQualifier_value").item(0).getTextContent();
                    }
                    switch(sItem){
                        case "organism":
                            break;
                        case "mol_type":
                            break;
                        case "db_xref":
                            numTaxonomia=sValor;
                            break;
                    }
                }

                TiposMolecula t1=new TiposMolecula();
                t1.setTipoMolecula(molType);
                int idTipoMolecula=t1.getTipoMoleculaID();

                Divisiones d1=new Divisiones();
                d1.setDivision(division);
                int idDivision=d1.getDivisionID();

                Topologias top1=new Topologias();
                top1.setTopologia(topology);
                int idTopologia=top1.getTopologiaID();

                Strands str1=new Strands();
                str1.setStrand(strand);
                int idStrand=str1.getStrandID();

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
                String publicaciones="N";
                if(publicado){
                    publicaciones="S";
                }
                Secuencia sec=new Secuencia();
                sec.setNombre(nombre);
                sec.setFolio(folio);
                sec.setSecuencia(secuencia);
                sec.setOrganismo(organism);
                sec.setLongitud(longitud);
                sec.setNumTaxonomia(numTaxonomia);
                sec.setTaxonomia(taxonomy);
                sec.setPublicaciones(publicaciones);
                sec.setIdTipoMolecula(idTipoMolecula);
                sec.setIdDivision(idDivision);
                sec.setIdTopologia(idTopologia);
                sec.setIdStrand(idStrand);

                TextProcessor tp1=new TextProcessor();
                tp1.setTextoFormateado(secuencia.toUpperCase());
                tp1.ProcesaTextoFormateado();
                ArrayList<Dimero> dimeros=tp1.getDimerosEstadistica();
               
                
                double frecuenciaGen=0;
                double distanciaMinimaGen=0;
                double distanciaMaximaGen=0;
                double distanciaPromedioGen=0;
                double frecuenciaPurinasGen=0;
                double frecuenciaPirimidinasGen=0;
                double frecuenciaDurezaDebilGen=0;
                double frecuenciaDurezaFuerteGen=0;

                for(Dimero dimero:dimeros){
                    frecuenciaGen+=dimero.getFrecuencia();
                    distanciaMinimaGen+=dimero.getDistanciaMinima();
                    distanciaMaximaGen+=dimero.getDistanciaMaxima();
                    distanciaPromedioGen+=dimero.getDistanciaPromedio();
                    frecuenciaPurinasGen+=dimero.getFrecuenciaPurinas();
                    frecuenciaPirimidinasGen+=dimero.getFrecuenciaPirimidinas();
                    frecuenciaDurezaDebilGen+=dimero.getFrecuenciaDurezaDebil();
                    frecuenciaDurezaFuerteGen+=dimero.getFrecuenciaDurezaFuerte();
                }
                sec.setFrecuencia(frecuenciaGen);
                sec.setDistanciaMinima(distanciaMinimaGen/dimeros.size());
                sec.setDistanciaMaxima(distanciaMaximaGen/dimeros.size());
                sec.setDistanciaPromedio(distanciaPromedioGen/dimeros.size());
                sec.setFrecuenciaPurinas(frecuenciaPurinasGen);
                sec.setFrecuenciaPirimidinas(frecuenciaPirimidinasGen);
                sec.setFrecuenciaDurezaDebil(frecuenciaDurezaDebilGen);
                sec.setFrecuenciaDurezaFuerte(frecuenciaDurezaFuerteGen);
                sec.setDimeros(dimeros);
                
                ProyectoSecuencia proyectoSecuencia=new ProyectoSecuencia();
                proyectoSecuencia.setIdProyecto(2); //zika
                proyectoSecuencia.setSecuencia(sec);
                
                ProyectosSecuencia ps1=new ProyectosSecuencia();
                ps1.proyectoSecuenciaAgregar(proyectoSecuencia);
           }
           
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
