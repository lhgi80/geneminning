/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import BD.Conexion;
import dnaMining.BD.Clases.Secuencia;
import dnaMining.BD.Clases.ProyectoSecuencia;
import dnaMining.BD.Secuencias;
import dnaMining.BD.ProyectosSecuencia;
import java.util.ArrayList;
/**
 *
 * @author Luis
 */
public class leerBD {
    public static void main(String... args){
        Conexion con= new Conexion("mysql", "localhost", "genemini_datos", "root", "");
        ProyectosSecuencia pg = new ProyectosSecuencia();
        pg.setIdProyecto(1);
        ArrayList<ProyectoSecuencia> py=pg.getDatos();
        
    }
}
