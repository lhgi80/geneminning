/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dnaMining.BD.Clases.Dimero;
import dnaMining.FileProcessing.FileLoader;
import dnaMining.FileProcessing.TextProcessor;
import java.util.ArrayList;
/**
 *
 * @author Luis
 */
public class leerSecuencias {
    public static void main(String...args){
        FileLoader fl=new FileLoader("","dandolata.txt");
        String cadena=fl.getText();
        String[] cadenas=cadena.split(">gi");
        int i=0;
        
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
            
            
            String secuencia=paraSecuencias[1].replace("\n", "\"");
            System.out.print ((++i) + " ->id:" + id + " ->Folio:" + folio + "nombre:->" + nombre + "->secuencia:" + secuencia);
            
            System.out.println("\nListado de Dimeros");
            TextProcessor tp=new TextProcessor();
            tp.setTextoFormateado(secuencia);
            tp.ProcesaTextoFormateado();
            ArrayList<Dimero> dimeros=tp.getDimerosEstadistica();
            
            for(Dimero dimero:dimeros){
                System.out.println("dimero:" + dimero.getNombreDimero() 
                                 + ",frecuencia:" + dimero.getFrecuencia()
                                 + ",distanciaMinima:" + dimero.getDistanciaMinima() 
                                 + ",distanciaMaxima" + dimero.getDistanciaMaxima() 
                                 + ",distanciaPromedio=" + dimero.getDistanciaPromedio() 
                                 + ",frecuenciaPurinas" + dimero.getFrecuenciaPurinas()
                                 + ",frecuenciaPirimidinas" + dimero.getFrecuenciaPirimidinas()
                                 + ",frecuenciaFuerte" + dimero.getFrecuenciaDurezaFuerte()
                                 + ",frecuenciaDebil" + dimero.getFrecuenciaDurezaDebil());
            }
            }catch(Exception e){
                i=0;
            }
        }
    }
}
