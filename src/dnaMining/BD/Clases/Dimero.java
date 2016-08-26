/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnaMining.BD.Clases;

/**
 *
 * @author Luis
 */
public class Dimero extends Estadistica{
    private long idDimero;
    private long idSecuencia;
    private String nombreDimero;
    
    public Dimero() {
        super();
        idSecuencia=0;
        idDimero=0;
        nombreDimero="";
    }

    public long getIdDimero() {
        return idDimero;
    }

    public void setIdDimero(long idDimero) {
        this.idDimero = idDimero;
    }

    public String getNombreDimero() {
        return nombreDimero;
    }

    public void setNombreDimero(String nombreDimero) {
        this.nombreDimero = nombreDimero;
    }    

    public long getIdSecuencia() {
        return idSecuencia;
    }

    public void setIdSecuencia(long idSecuencia) {
        this.idSecuencia = idSecuencia;
    }
}
