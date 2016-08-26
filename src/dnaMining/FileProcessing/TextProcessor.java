/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnaMining.FileProcessing;

import dnaMining.BD.Clases.Dimero;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class TextProcessor {
    private String texto;
    private String textoFormateado;
    private String[] listadoDimeros;
    private int[][]estadisticas;
    private ArrayList<String> losDimeros;
    private ArrayList<Dimero> DimerosEstadisticas;

    public TextProcessor(){
        this.texto="";
        DimerosEstadisticas=new ArrayList();
    }
    public TextProcessor(String texto) {
        this();
        this.texto = texto;
        getProcessedText();
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
                case 'A':
                case 'T':
                case 'C':
                case 'G':
                   salida+=caracter;
            }
        }
        textoFormateado=salida;
    }
    private void inicializaEstadisticas(){
        estadisticas=new int[16][8];
        //inicializa las estadisticas;
        for(int i=0;i<estadisticas.length;i++){
            for(int j=0;j<6;j++){
                estadisticas[i][j]=0;
            }
        }
        //llena las proporciones para lisura y dureza [indices 4 a 7]
        estadisticas[0][4]=2; // AA
        estadisticas[0][7]=2; // purina=2,pirimidina=0,fuerte=0,debil=2
        
        estadisticas[1][4]=1; // AT
        estadisticas[1][5]=1; // purina=1,pirimidina=1,fuerte=0,debil=2
        estadisticas[1][7]=2;
        
        estadisticas[2][4]=1; // AC
        estadisticas[2][5]=1; // purina=1,pirimidina=1,fuerte=1,debil=1
        estadisticas[2][6]=1;
        estadisticas[2][7]=1;
        
        estadisticas[3][4]=2; // AG
        estadisticas[3][6]=1; // purina=2,pirimidina=0,fuerte=1,debil=1
        estadisticas[3][7]=1;
        
        estadisticas[4][4]=1; // TA
        estadisticas[4][5]=1; // purina=1,pirimidina=1,fuerte=0,debil=2
        estadisticas[4][7]=2;
        
        estadisticas[5][5]=2; // TT
        estadisticas[5][7]=2; // purina=0,pirimidina=2,fuerte=0,debil=2
        
        estadisticas[6][5]=2; // TC
        estadisticas[6][6]=1; // purina=0,pirimidina=2,fuerte=1,debil=1
        estadisticas[6][7]=1;
        
        estadisticas[7][4]=1; // TG
        estadisticas[7][5]=1; // purina=1,pirimidina=1,fuerte=1,debil=1
        estadisticas[7][6]=1;
        estadisticas[7][7]=1;
        
        estadisticas[8][4]=1; // CA
        estadisticas[8][5]=1; // purina=1,pirimidina=1,fuerte=1,debil=1
        estadisticas[8][6]=1;
        estadisticas[8][7]=1;
        
        estadisticas[9][5]=2; // CT
        estadisticas[9][6]=1; // purina=0,pirimidina=2,fuerte=1,debil=1
        estadisticas[9][7]=1;
        
        estadisticas[10][5]=2; // CC
        estadisticas[10][6]=2; // purina=0,pirimidina=2,fuerte=2,debil=0
        
        estadisticas[11][4]=1; // CG
        estadisticas[11][5]=1; // purina=1,pirimidina=1,fuerte=2,debil=0
        estadisticas[11][6]=2;
        
        estadisticas[12][4]=2; // GA
        estadisticas[12][6]=1; // purina=2,pirimidina=0,fuerte=1,debil=1
        estadisticas[12][7]=1;
        
        estadisticas[13][4]=1; // GT
        estadisticas[13][5]=1; // purina=1,pirimidina=1,fuerte=1,debil=1
        estadisticas[13][6]=1;
        estadisticas[13][7]=1;
        
        estadisticas[14][4]=1; // GC
        estadisticas[14][5]=1; // purina=1,pirimidina=1,fuerte=2,debil=0
        estadisticas[14][6]=2;
        
        estadisticas[15][4]=2; // GG
        estadisticas[15][6]=2; // purina=2,pirimidina=0,fuerte=2,debil=0
        
        
        losDimeros=new ArrayList();
        losDimeros.add("AA");
        losDimeros.add("AT");
        losDimeros.add("AC");
        losDimeros.add("AG");
        losDimeros.add("TA");
        losDimeros.add("TT");
        losDimeros.add("TC");
        losDimeros.add("TG");
        losDimeros.add("CA");
        losDimeros.add("CT");
        losDimeros.add("CC");
        losDimeros.add("CG");
        losDimeros.add("GA");
        losDimeros.add("GT");
        losDimeros.add("GC");
        losDimeros.add("GG");
    }
    
    /***
     * Genera el proceso de obtener los dimeros y sus estadisticas
     */
    public void ProcesaTextoFormateado(){
        inicializaEstadisticas();
        ArrayList[] posiciones=new ArrayList[16];
        for(int i=0;i<16;i++){
            posiciones[i]=new ArrayList();
        }
        
        int longitudArray=textoFormateado.length()/2;
        if(longitudArray*2<textoFormateado.length()){
            longitudArray++;
        }
        listadoDimeros=new String[longitudArray];
        String dimero;
        int posicion;
        try{
            for(int i=0,j=0;j<longitudArray;i+=2,j++){
                if(i+2<textoFormateado.length()){
                    dimero=textoFormateado.substring(i, i+2);
                }
                else{
                    dimero=textoFormateado.substring(i);
                }
                listadoDimeros[j]=dimero;
                posicion=losDimeros.indexOf(dimero);
                if(posicion!=-1){
                    estadisticas[posicion][0]++; // frecuencia
                    posiciones[posicion].add(j);
                }
            }
        }
        catch(Exception e){
            int j=-1;
        }
        
        int distancia;
        int distanciaMinima;
        int distanciaMaxima;
        int distanciaAcumulada;
        int posicionAnterior;
        Object[] arrPosiciones;
        
        for(int i=0;i<16;i++){
            // Dureza y Lisura
            for(int j=4;j<=7;j++){
                estadisticas[i][j]*=estadisticas[i][0];
            }
            
            distanciaMinima=0;
            distanciaMaxima=posiciones[i].size();
            distanciaAcumulada=0;
            posicionAnterior=0;
            arrPosiciones=posiciones[i].toArray();
            
            for(Object posicionActual:arrPosiciones){
                distancia=(int)posicionActual-posicionAnterior;
                if(distanciaMinima==0){
                    distanciaMinima=distancia;
                }
                else{
                    if(distancia<distanciaMinima){
                        distanciaMinima=distancia;
                    }
                    if(distancia>distanciaMaxima){
                        distanciaMaxima=distancia;
                    }
                    distanciaAcumulada+=distancia;
                }
            }
            estadisticas[i][1]=distanciaMinima;
            estadisticas[i][2]=distanciaMaxima;
            estadisticas[i][3]=distanciaAcumulada;
        }
        for(int i=0;i<16;i++){
            Dimero dimeroAgregar=new Dimero();
            dimeroAgregar.setNombreDimero(losDimeros.get(i));
            dimeroAgregar.setFrecuencia(estadisticas[i][0]);
            dimeroAgregar.setDistanciaMinima(estadisticas[i][1]);
            dimeroAgregar.setDistanciaMaxima(estadisticas[i][2]);
            int divisor=1;
            if(estadisticas[i][0]>1){
                divisor=estadisticas[i][0]-1;
            }
            dimeroAgregar.setDistanciaPromedio(estadisticas[i][3]/(divisor));
            dimeroAgregar.setFrecuenciaPurinas(estadisticas[i][4]);
            dimeroAgregar.setFrecuenciaPirimidinas(estadisticas[i][5]);
            dimeroAgregar.setFrecuenciaDurezaFuerte(estadisticas[i][6]);
            dimeroAgregar.setFrecuenciaDurezaDebil(estadisticas[i][7]);
            
            DimerosEstadisticas.add(dimeroAgregar);
        }
    }
    public String[] getListadoDimeros(){
        return listadoDimeros;
    }
    
    public int getLongitud(){
        return textoFormateado.length();
    }
    
    public void setTextoFormateado(String textoFormateado){
        this.textoFormateado=textoFormateado;
    }
    public String getTextoFormateado() {
        getProcessedText();
        return textoFormateado;
    }
    public ArrayList<Dimero >getDimerosEstadistica(){
        return DimerosEstadisticas;
    }
}
