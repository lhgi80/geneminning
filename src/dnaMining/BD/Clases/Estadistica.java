/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnaMining.BD.Clases;

/**
 *
 * @author Luis
 */
public class Estadistica {
    private double frecuencia;
    private double distanciaMinima;
    private double distanciaMaxima;
    private double distanciaPromedio;
    private double frecuenciaPurinas;
    private double frecuenciaPirimidinas;
    private double frecuenciaDurezaDebil;
    private double frecuenciaDurezaFuerte;

    public Estadistica() {
        this.frecuencia = 0;
        this.distanciaMinima = 0;
        this.distanciaMaxima = 0;
        this.distanciaPromedio = 0;
        this.frecuenciaPurinas = 0;
        this.frecuenciaPirimidinas = 0;
        this.frecuenciaDurezaDebil = 0;
        this.frecuenciaDurezaFuerte = 0;
    }

    public double getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(double frecuencia) {
        this.frecuencia = frecuencia;
    }

    public double getDistanciaMinima() {
        return distanciaMinima;
    }

    public void setDistanciaMinima(double distanciaMinima) {
        this.distanciaMinima = distanciaMinima;
    }

    public double getDistanciaMaxima() {
        return distanciaMaxima;
    }

    public void setDistanciaMaxima(double distanciaMaxima) {
        this.distanciaMaxima = distanciaMaxima;
    }

    public double getDistanciaPromedio() {
        return distanciaPromedio;
    }

    public void setDistanciaPromedio(double distanciaPromedio) {
        this.distanciaPromedio = distanciaPromedio;
    }

    public double getFrecuenciaPurinas() {
        return frecuenciaPurinas;
    }

    public void setFrecuenciaPurinas(double frecuenciaPurinas) {
        this.frecuenciaPurinas = frecuenciaPurinas;
    }

    public double getFrecuenciaPirimidinas() {
        return frecuenciaPirimidinas;
    }

    public void setFrecuenciaPirimidinas(double frecuenciaPirimidinas) {
        this.frecuenciaPirimidinas = frecuenciaPirimidinas;
    }

    public double getFrecuenciaDurezaDebil() {
        return frecuenciaDurezaDebil;
    }

    public void setFrecuenciaDurezaDebil(double frecuenciaPurezaDebil) {
        this.frecuenciaDurezaDebil = frecuenciaPurezaDebil;
    }

    public double getFrecuenciaDurezaFuerte() {
        return frecuenciaDurezaFuerte;
    }

    public void setFrecuenciaDurezaFuerte(double frecuenciaPurezaFuerte) {
        this.frecuenciaDurezaFuerte = frecuenciaPurezaFuerte;
    }
}
