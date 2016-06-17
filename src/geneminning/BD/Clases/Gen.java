/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneminning.BD.Clases;
import java.util.ArrayList;
/**
 *
 * @author Luis
 */
public class Gen extends Estadistica {
    private int idGen;
    private String nombreGen;
    private String folio;
    private String cadena;
    private int idTipoGen;
    private String tipoGen;
    ArrayList<Dimero> dimeros;

    public Gen() {
    }

    public int getIdGen() {
        return idGen;
    }

    public void setIdGen(int idGen) {
        this.idGen = idGen;
    }

    public String getNombreGen() {
        return nombreGen;
    }

    public void setNombreGen(String nombreGen) {
        this.nombreGen = nombreGen;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public int getIdTipoGen() {
        return idTipoGen;
    }

    public void setIdTipoGen(int idTipoGen) {
        this.idTipoGen = idTipoGen;
    }

    public String getTipoGen() {
        return tipoGen;
    }

    public void setTipoGen(String tipoGen) {
        this.tipoGen = tipoGen;
    }

    public ArrayList<Dimero> getDimeros() {
        return dimeros;
    }

    public void setDimeros(ArrayList<Dimero> dimeros) {
        this.dimeros = dimeros;
    }
    
    
}
