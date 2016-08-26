/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import BD.Conexion;
import dnaMining.BD.Clases.Dimero;
import dnaMining.BD.Dimeros;
import java.util.ArrayList;
/**
 *
 * @author Luis
 */
public class test {
    public static void main(String[] args){
        Conexion con=new Conexion("mysql", "localhost", "genemini_datos", "root", "");
        Dimeros dimeros=new Dimeros();
        ArrayList<Dimero> arDim=dimeros.getDatos();
        for(Dimero dimero:arDim){
            System.out.print(dimero.getNombreDimero());
        }
    }
}
