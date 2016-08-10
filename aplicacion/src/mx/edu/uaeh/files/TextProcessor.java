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
public class TextProcessor {
    private String texto;
    private String textoFormateado;
    private ArrayList<String> dimeros;
    private ArrayList<Dimero> listadoDimeros;
    

    public TextProcessor(String texto) {
        this.texto = texto;
        getProcessedText();
        dimeros=new ArrayList();
        listadoDimeros=new ArrayList();
        getDimeros();
    }
    public String getTexto() {
        return texto;
    }
    private void getProcessedText(){
        String salida="";
        
        char[] arrTexto;
        arrTexto=texto.toCharArray();
        
        for(char caracter:arrTexto){
            switch(caracter){
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case ' ':
                case '\n':
                case '\t':
                    break;
                default:
                    salida+=caracter;
            }
        }
        textoFormateado=salida;
    }
    private void getDimeros(){
        for(int i=0;i<textoFormateado.length()-1;i++){
            dimeros.add(textoFormateado.substring(i,i+2));
        }
        
        setListadoDimeros();
    }
    
    private void setListadoDimeros(){
        Dimero dimero;
        for(String dimeroEvaluar:dimeros){
            if(listadoDimeros.isEmpty()){
                dimero=new Dimero();
                dimero.setCantidad(1);
                
                dimero.setDimero(dimeroEvaluar);
                listadoDimeros.add(dimero);
                continue;
            }
            
            // busca el dimero en las frecuencias
            int i=0;
            boolean encontrado=false;
            for(Dimero dimeroBuscar:listadoDimeros){
                if(dimeroBuscar.getDimero().equals(dimeroEvaluar)){
                    listadoDimeros.get(i).setCantidad(dimeroBuscar.getCantidad()+1);
                    encontrado=true;
                    break;
                }
                i++;
            }
            if(!encontrado){
                dimero=new Dimero();
                dimero.setCantidad(1);
                dimero.setDimero(dimeroEvaluar);
                listadoDimeros.add(dimero);
            }
            
        }
        // busca las distancias entre cada dimero
        int[] posiciones;
        int[] distancias;
        int distancia;
        int indiceInicio=0;
        String cadenaDimero;
        for(Dimero dimeroBuscar:listadoDimeros){
            distancias=new int[dimeroBuscar.getCantidad()];
            distancias=new int[dimeroBuscar.getCantidad()-1];
            
            cadenaDimero=dimeroBuscar.getDimero();
                    
            
        }
    }
    
    public ArrayList<Dimero> getEstadisticasDimeros(){
        return listadoDimeros;
    }
    
    
    
    public int getLongitud(){
        return textoFormateado.length();
    }
    public ArrayList<String> getListadoDimeros(){
        return dimeros;
    }
    public int getNumeroDimeros(){
        return dimeros.size();
    }

    public String getTextoFormateado() {
        return textoFormateado;
    }
    
}
