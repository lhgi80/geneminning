/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import BD.Conexion;
import geneminning.BD.Clases.Gen;
import geneminning.BD.Clases.ProyectoGen;
import geneminning.BD.Genes;
import geneminning.BD.ProyectosGen;
import java.util.ArrayList;
/**
 *
 * @author Luis
 */
public class leerBD {
    public static void main(String... args){
        Conexion con= new Conexion("mysql", "localhost", "genemini_datos", "root", "");
        ProyectosGen pg = new ProyectosGen();
        pg.setIdProyecto(1);
        ArrayList<ProyectoGen> py=pg.getDatos();
        
    }
}
