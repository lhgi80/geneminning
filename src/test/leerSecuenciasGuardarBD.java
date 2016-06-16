/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import BD.Conexion;
import geneminning.BD.Clases.Dimero;
import geneminning.BD.Clases.Gen;
import geneminning.BD.Clases.ProyectoGen;
import geneminning.BD.ProyectosGen;
import mx.edu.uaeh.files.FileLoader;
import mx.edu.uaeh.files.TextProcessor;
import java.util.ArrayList;
/**
 *
 * @author Luis
 */
public class leerSecuenciasGuardarBD {
    public static void main(String...args){
        FileLoader fl=new FileLoader("","secuencias.txt");
        String cadena=fl.getText();
        String[] cadenas=cadena.split(">gi");
        int i=0;
        Conexion con= new Conexion("mysql", "localhost", "gemjava", "root", "");
        
        for(String paso:cadenas){
            if(paso.length()==0){
                continue;
            }
            try{
                String[] paraSecuencias=paso.split("\n",2);
                String[] identificacion=paraSecuencias[0].split("\\|");
                String id=identificacion[1];
                String folio=identificacion[3];
                String nombre=identificacion[4];


                String secuencia=paraSecuencias[1].replace("\n", "");
                //System.out.print ((++i) + " ->id:" + id + " ->Folio:" + folio + "nombre:->" + nombre + "->secuencia:" + secuencia);
                //System.out.println("\nListado de Dimeros");

                TextProcessor tp=new TextProcessor();
                tp.setTextoFormateado(secuencia);
                tp.ProcesaTextoFormateado();
                ArrayList<Dimero> dimeros=tp.getDimerosEstadistica();

                Gen gen=new Gen();
                gen.setFolio(folio);
                gen.setCadena(secuencia);
                gen.setNombreGen(nombre);
                gen.setIdTipoGen(3); // nucleotido
                gen.setDimeros(dimeros);

                double frecuenciaGen=0;
                double distanciaMinimaGen=0;
                double distanciaMaximaGen=0;
                double distanciaPromedioGen=0;
                double frecuenciaPurinasGen=0;
                double frecuenciaPirimidinasGen=0;
                double frecuenciaDurezaDebilGen=0;
                double frecuenciaDurezaFuerteGen=0;

                for(Dimero dimero:dimeros){
                    frecuenciaGen+=dimero.getFrecuencia();
                    distanciaMinimaGen+=dimero.getDistanciaMinima();
                    distanciaMaximaGen+=dimero.getDistanciaMaxima();
                    distanciaPromedioGen+=dimero.getDistanciaPromedio();
                    frecuenciaPurinasGen+=dimero.getFrecuenciaPurinas();
                    frecuenciaPirimidinasGen+=dimero.getFrecuenciaPirimidinas();
                    frecuenciaDurezaDebilGen+=dimero.getFrecuenciaDurezaDebil();
                    frecuenciaDurezaFuerteGen+=dimero.getFrecuenciaDurezaFuerte();
                }
                gen.setFrecuencia(frecuenciaGen);
                gen.setDistanciaMinima(distanciaMinimaGen/dimeros.size());
                gen.setDistanciaMaxima(distanciaMaximaGen/dimeros.size());
                gen.setDistanciaPromedio(distanciaPromedioGen/dimeros.size());
                gen.setFrecuenciaPurinas(frecuenciaPurinasGen);
                gen.setFrecuenciaPirimidinas(frecuenciaPirimidinasGen);
                gen.setFrecuenciaDurezaDebil(frecuenciaDurezaDebilGen);
                gen.setFrecuenciaDurezaFuerte(frecuenciaDurezaFuerteGen);
                
                ProyectoGen proyectoGen=new ProyectoGen();
                proyectoGen.setIdProyecto(1);// petroleo
                proyectoGen.setGen(gen);
                
                ProyectosGen pg=new ProyectosGen();
                pg.proyectoGenAgregar(proyectoGen);
            }catch(Exception e){
                i=0;
            }
        }
    }
}
