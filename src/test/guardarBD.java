/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import BD.Conexion;
import dnaMining.BD.Clases.Dimero;
import dnaMining.BD.Clases.Secuencia;
import dnaMining.BD.Clases.ProyectoSecuencia;
import dnaMining.BD.Dimeros;
import dnaMining.BD.Secuencias;
import dnaMining.BD.ProyectosSecuencia;
import java.util.ArrayList;
/**
 *
 * @author Luis
 */
public class guardarBD {
    public static void main(String... args){
        Conexion con= new Conexion("mysql", "localhost", "gemjava", "root", "");
        
        //Genes g new Genes();
        Secuencias g=new Secuencias();
        Secuencia secuencia=new Secuencia();
        secuencia.setNombre("PRUEBA DE GEN");
        secuencia.setFolio("FOLIO 1");
        secuencia.setSecuencia("AAAATTTTCCCCCGGG");
        secuencia.setFrecuencia(1);
        secuencia.setDistanciaMinima(1);
        secuencia.setDistanciaMaxima(100);
        secuencia.setDistanciaPromedio(50);
        secuencia.setFrecuenciaPurinas(1);
        secuencia.setFrecuenciaPirimidinas(1);
        secuencia.setFrecuenciaDurezaDebil(1);
        secuencia.setFrecuenciaDurezaFuerte(1);
        
        Dimero dimero=new Dimero();
        dimero.setIdSecuencia(1);
        dimero.setNombreDimero("AA");
        dimero.setFrecuencia(100);
        dimero.setDistanciaMinima(2);
        dimero.setDistanciaMaxima(100);
        dimero.setDistanciaPromedio(50);
        dimero.setFrecuenciaPurinas(5);
        dimero.setFrecuenciaPirimidinas(5);
        dimero.setFrecuenciaDurezaDebil(10);
        dimero.setFrecuenciaDurezaFuerte(10);
        
        ArrayList<Dimero>dimeros=new ArrayList();
        dimeros.add(dimero);
        
        secuencia.setDimeros(dimeros);
        ProyectoSecuencia pg=new ProyectoSecuencia();
        pg.setIdProyecto(1); // petroleo
        pg.setSecuencia(secuencia);
        
        ProyectosSecuencia pgen=new ProyectosSecuencia();
        pgen.proyectoSecuenciaAgregar(pg);

//        
//        d.dimeroAgregar(dimero);
                
        
    }
}
