/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneminning.BD.Clases;

/**
 *
 * @author Luis
 */
public class ProyectoGen {
    private int idProyecto;
    private String nombreProyecto;
    private int idGen;
    private String folio;
    private int idTipoGen;
    private Gen gen;

    public ProyectoGen() {
        
    }
    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public int getIdGen() {
        return idGen;
    }

    public void setIdGen(int idGen) {
        this.idGen = idGen;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public int getIdTipoGen() {
        return idTipoGen;
    }

    public void setIdTipoGen(int idTipoGen) {
        this.idTipoGen = idTipoGen;
    }

    public Gen getGen() {
        return gen;
    }

    public void setGen(Gen gen) {
        this.gen = gen;
    }
}
