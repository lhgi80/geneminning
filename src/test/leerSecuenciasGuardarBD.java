/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import BD.Conexion;
import dnaMining.BD.Clases.Dimero;
import dnaMining.BD.Clases.Secuencia;
import dnaMining.BD.Clases.ProyectoSecuencia;
import dnaMining.BD.ProyectosSecuencia;
import dnaMining.FileProcessing.FileLoader;
import dnaMining.FileProcessing.TextProcessor;
import java.util.ArrayList;
import java.util.Calendar;
/**
 *
 * @author Luis
 */
public class leerSecuenciasGuardarBD {
    public static void main(String...args){
        
        int hora,minutos;
        Calendar calendario=Calendar.getInstance();
        hora=calendario.get(Calendar.HOUR);
        minutos=calendario.get(Calendar.MINUTE);
        System.out.println("Iniciando:"+ hora + ":" + minutos);
        FileLoader fl=new FileLoader("","zikamin.txt");
        String cadena=fl.getText();
        String[] cadenas=cadena.split(">gi");
        int i=0;
        Conexion con= new Conexion("mysql", "localhost", "gemjava", "root", "");
        
        hora=calendario.get(Calendar.HOUR);
        minutos=calendario.get(Calendar.MINUTE);
        System.out.println("procesando cadenas leidas:"+ hora + ":" + minutos);
        
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

                Secuencia sec=new Secuencia();
                sec.setFolio(folio);
                sec.setSecuencia(secuencia);
                sec.setNombre(nombre);
                sec.setDimeros(dimeros);

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
                sec.setFrecuencia(frecuenciaGen);
                sec.setDistanciaMinima(distanciaMinimaGen/dimeros.size());
                sec.setDistanciaMaxima(distanciaMaximaGen/dimeros.size());
                sec.setDistanciaPromedio(distanciaPromedioGen/dimeros.size());
                sec.setFrecuenciaPurinas(frecuenciaPurinasGen);
                sec.setFrecuenciaPirimidinas(frecuenciaPirimidinasGen);
                sec.setFrecuenciaDurezaDebil(frecuenciaDurezaDebilGen);
                sec.setFrecuenciaDurezaFuerte(frecuenciaDurezaFuerteGen);
                
                ProyectoSecuencia proyectoSecuencia=new ProyectoSecuencia();
                proyectoSecuencia.setIdProyecto(2);// petroleo
                proyectoSecuencia.setSecuencia(sec);
                
                ProyectosSecuencia pg=new ProyectosSecuencia();
                pg.proyectoSecuenciaAgregar(proyectoSecuencia);
            }catch(Exception e){
                i=0;
            }
            
            
            hora=calendario.get(Calendar.HOUR);
            minutos=calendario.get(Calendar.MINUTE);
            System.out.println("cadena leida:"+ hora + ":" + minutos);
        }
    }
}
