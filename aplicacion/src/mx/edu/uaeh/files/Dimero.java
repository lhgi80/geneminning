/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uaeh.files;

import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class Dimero {
    private String dimero;
    private int cantidad;
    private int[] posiciones;
    private int[] distancias;

    public Dimero() {
    }

    public String getDimero() {
        return dimero;
    }

    public void setDimero(String dimero) {
        this.dimero = dimero;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int[] getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(int[] posiciones) {
        this.posiciones = posiciones;
    }
    
    
    
    public int[] getDistancias() {
        return distancias;
    }

    public void setDistancias(int[] distancias) {
        this.distancias = distancias;
    }
    
    
}
