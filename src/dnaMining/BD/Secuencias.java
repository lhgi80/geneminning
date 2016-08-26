/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnaMining.BD;

import BD.Conexion;
import dnaMining.BD.Clases.Dimero;
import dnaMining.BD.Clases.Secuencia;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
/**
 *
 * @author Luis
 */
public class Secuencias {
    private int idSecuencia;
    Dimeros dimeros;
    private Connection conexion;

    public Secuencias() {
        conexion = Conexion.getConexion();
        idSecuencia=0;
        //secuencia=new Secuencia();
    }

    public int getIdSecuencia() {
        return idSecuencia;
    }

    public void setIdSecuencia(int idSecuencia) {
        this.idSecuencia = idSecuencia;
    }
    
    private String getFiltro(){
        String salida="";
        if(idSecuencia!=0){
            if(salida.length()>0){
                salida+=" AND ";
            }
            salida+="idSecuencia=" + idSecuencia;
        }
        
        if(salida.length()>0){
            salida=" WHERE " + salida;
        }
        return salida;
    }
    public ArrayList<Secuencia> getDatos(){
        ArrayList<Secuencia> secuencias=new ArrayList();
        String query="SELECT\n" +
        "idSecuencia,\n" +
        "nombre,\n" +
        "folio,\n" +
        "secuencia,\n" +
        "organismo,\n" +
        "longitud,\n" +
        "numTaxonomia,\n" +
        "taxonomia,\n" +
        "publicaciones,\n" +
        "idTipoMolecula,\n" +
        "tipoMolecula,\n" +
        "idDivision,\n" +
        "division,\n" +
        "idTopologia\n" +
        "topologia,\n" +
        "idStrand,\n" +
        "strand,\n" +
        "frecuencia,\n" +
        "distanciaMinima,\n" +
        "distanciaMaxima,\n" +
        "distanciaPromedio,\n" +
        "frecuenciaPurinas,\n" +
        "frecuenciaPirimidinas,\n" +
        "frecuenciaDurezaDebil,\n" +
        "frecuenciaDurezaFuerte\n" +
        "FROM vtaC_Secuencia\n"+
        getFiltro();
        
        
        Statement st;
        try {
            st = conexion.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Secuencia secuencia=new Secuencia();
                secuencia.setIdSecuencia(rs.getInt("idSecuencia"));
                secuencia.setNombre(rs.getString("nombre"));
                secuencia.setFolio(rs.getString("folio"));
                secuencia.setSecuencia(rs.getString("secuencia"));
                secuencia.setOrganismo(rs.getString("organismo"));
                secuencia.setLongitud(rs.getInt("longitud"));
                secuencia.setOrganismo(rs.getString("organismo"));
                secuencia.setNumTaxonomia(rs.getString("numTaxonomia"));
                secuencia.setTaxonomia(rs.getString("taxonomia"));
                secuencia.setPublicaciones(rs.getString("publicaciones"));
                secuencia.setIdTipoMolecula(rs.getInt("idTipoMolecula"));
                secuencia.setTipoMolecula(rs.getString("tipoMolecula"));
                secuencia.setIdDivision(rs.getInt("idDivision"));
                secuencia.setDivision(rs.getString("division"));
                secuencia.setIdTopologia(rs.getInt("idTopologia"));
                secuencia.setTopologia(rs.getString("topologia"));
                secuencia.setIdStrand(rs.getInt("idStrand"));
                secuencia.setStrand(rs.getString("strand"));
                
                
                secuencia.setFrecuencia(rs.getDouble("frecuencia"));
                secuencia.setDistanciaMinima(rs.getDouble("distanciaMinima"));
                secuencia.setDistanciaMaxima(rs.getDouble("distanciaMaxima"));
                secuencia.setDistanciaPromedio(rs.getDouble("distanciaPromedio"));
                secuencia.setFrecuenciaPurinas(rs.getDouble("frecuenciaPurinas"));
                secuencia.setFrecuenciaPirimidinas(rs.getDouble("frecuenciaPirimidinas"));
                secuencia.setFrecuenciaDurezaDebil(rs.getDouble("frecuenciaDurezaDebil"));
                secuencia.setFrecuenciaDurezaFuerte(rs.getDouble("frecuenciaDurezaFuerte"));
                
                dimeros=new Dimeros();
                dimeros.setIdSecuencia(secuencia.getIdSecuencia());
                ArrayList<Dimero> listadoDimeros=dimeros.getDatos();
                secuencia.setDimeros(listadoDimeros);
                secuencias.add(secuencia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dimeros.class.getName()).log(Level.SEVERE, null, ex);
        }
        return secuencias;
    }
    public boolean secuenciaAgregar(Secuencia secuencia){
        boolean salida=false;
        try {
            CallableStatement cst = conexion.prepareCall("{call Secuencia_EstadisticaAgregar (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            
            cst.setString(1,secuencia.getNombre());
            cst.setString(2,secuencia.getFolio());
            cst.setString(3,secuencia.getSecuencia());
            cst.setString(4,secuencia.getOrganismo());
            cst.setInt(5, secuencia.getLongitud());
            cst.setString(6, secuencia.getNumTaxonomia());
            cst.setString(7, secuencia.getTaxonomia());
            cst.setString(8, secuencia.getPublicaciones());
            cst.setInt(9,secuencia.getIdTipoMolecula());
            cst.setInt(10, secuencia.getIdDivision());
            cst.setInt(11,secuencia.getIdTopologia());
            cst.setInt(12,secuencia.getIdStrand());
            
            
            cst.setDouble(13,secuencia.getFrecuencia());
            cst.setDouble(14,secuencia.getDistanciaMinima());
            cst.setDouble(15,secuencia.getDistanciaMaxima());
            cst.setDouble(16,secuencia.getDistanciaPromedio());
            cst.setDouble(17,secuencia.getFrecuenciaPurinas());
            cst.setDouble(18,secuencia.getFrecuenciaPirimidinas());
            cst.setDouble(19,secuencia.getFrecuenciaDurezaDebil());
            cst.setDouble(20,secuencia.getFrecuenciaDurezaFuerte());
            
            cst.registerOutParameter(21, Types.INTEGER);
            cst.registerOutParameter(22, Types.INTEGER);
            cst.registerOutParameter(23, Types.VARCHAR);
            
            cst.execute();
            idSecuencia=cst.getInt(21);
            int noError=cst.getInt(22);
            String mensaje=cst.getString(23);
            
            if(noError!=0){
                return false;
            }
            // Guarda los dimeros
            for(Dimero dimero:secuencia.getDimeros()){
                Dimeros d=new Dimeros();
                dimero.setIdSecuencia(idSecuencia);
                if(d.dimeroAgregar(dimero)==false){
                    return false;
                }
            }
            return true;
        } catch (SQLException ex) {
            salida=false;
            Logger.getLogger(Dimeros.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
