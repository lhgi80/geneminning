/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnaMining.BD.Clases;
import java.util.ArrayList;
/**
 *
 * @author Luis
 */
public class Secuencia extends Estadistica {
    private int idSecuencia;
    private String nombre;
    private String folio;
    private String secuencia;
    private String organismo;
    private int longitud;
    private String numTaxonomia;
    private String taxonomia;
    private String publicaciones;
    private int idTipoMolecula;
    private String tipoMolecula;
    private int idDivision;
    private String division;
    private int idTopologia;
    private String topologia;
    private int idStrand;
    private String strand;
    
    ArrayList<Dimero> dimeros;

    public Secuencia() {
    }

    public int getIdSecuencia() {
        return idSecuencia;
    }

    public void setIdSecuencia(int idSecuencia) {
        this.idSecuencia = idSecuencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public String getOrganismo() {
        return organismo;
    }

    public void setOrganismo(String organismo) {
        this.organismo = organismo;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public String getNumTaxonomia() {
        return numTaxonomia;
    }

    public void setNumTaxonomia(String numTaxonomia) {
        this.numTaxonomia = numTaxonomia;
    }

    public String getTaxonomia() {
        return taxonomia;
    }

    public void setTaxonomia(String taxonomia) {
        this.taxonomia = taxonomia;
    }

    public String getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(String publicaciones) {
        this.publicaciones = publicaciones;
    }
    
    public int getIdTipoMolecula() {
        return idTipoMolecula;
    }

    public void setIdTipoMolecula(int idTipoMolecula) {
        this.idTipoMolecula = idTipoMolecula;
    }

    public String getTipoMolecula() {
        return tipoMolecula;
    }

    public void setTipoMolecula(String tipoMolecula) {
        this.tipoMolecula = tipoMolecula;
    }

    public int getIdDivision() {
        return idDivision;
    }

    public void setIdDivision(int idDivision) {
        this.idDivision = idDivision;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getIdTopologia() {
        return idTopologia;
    }

    public void setIdTopologia(int idTopologia) {
        this.idTopologia = idTopologia;
    }

    public String getTopologia() {
        return topologia;
    }

    public void setTopologia(String topologia) {
        this.topologia = topologia;
    }

    public int getIdStrand() {
        return idStrand;
    }

    public void setIdStrand(int idStrand) {
        this.idStrand = idStrand;
    }

    public String getStrand() {
        return strand;
    }

    public void setStrand(String strand) {
        this.strand = strand;
    }
    
    public ArrayList<Dimero> getDimeros() {
        return dimeros;
    }

    public void setDimeros(ArrayList<Dimero> dimeros) {
        this.dimeros = dimeros;
    }
    
    
}
