/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import BD.Conexion;
import geneminning.BD.Clases.Dimero;
import geneminning.BD.Clases.Gen;
import geneminning.BD.Clases.ProyectoGen;
import geneminning.BD.Dimeros;
import geneminning.BD.Genes;
import geneminning.BD.ProyectosGen;
import java.util.ArrayList;
/**
 *
 * @author Luis
 */
public class guardarBD {
    public static void main(String... args){
        Conexion con= new Conexion("mysql", "localhost", "gemjava", "root", "");
        
        //Genes g new Genes();
        Genes g=new Genes();
        Gen gen=new Gen();
        gen.setNombreGen("PRUEBA DE GEN");
        gen.setFolio("FOLIO 1");
        gen.setCadena("AAAATTTTCCCCCGGG");
        gen.setIdTipoGen(3);
        gen.setFrecuencia(1);
        gen.setDistanciaMinima(1);
        gen.setDistanciaMaxima(100);
        gen.setDistanciaPromedio(50);
        gen.setFrecuenciaPurinas(1);
        gen.setFrecuenciaPirimidinas(1);
        gen.setFrecuenciaDurezaDebil(1);
        gen.setFrecuenciaDurezaFuerte(1);
        
        Dimero dimero=new Dimero();
        dimero.setIdGen(1);
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
        
        gen.setDimeros(dimeros);
        ProyectoGen pg=new ProyectoGen();
        pg.setIdProyecto(1); // petroleo
        pg.setGen(gen);
        
        ProyectosGen pgen=new ProyectosGen();
        pgen.proyectoGenAgregar(pg);

//        
//        d.dimeroAgregar(dimero);
                
        
    }
}
