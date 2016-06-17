/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneminning.BD;

import BD.Conexion;
import geneminning.BD.Clases.Dimero;
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
public class Dimeros {
    private Dimero dimero;
    private long idGen;
    private long idDimero;
    private Connection conexion;

    public Dimeros() {
        this.dimero = new Dimero();
        this.idGen = 0;
        this.idDimero = 0;
        conexion=Conexion.getConexion();
    }

    
    public long getIdGen() {
        return idGen;
    }

    public void setIdGen(long idGen) {
        this.idGen = idGen;
    }

    public long getIdDimero() {
        return idDimero;
    }

    public void setIdDimero(long idDimero) {
        this.idDimero = idDimero;
    }

    public Dimero getDimero() {
        return dimero;
    }

    public void setDimero(Dimero dimero) {
        this.dimero = dimero;
    }
    private String getFiltro(){
        String salida="";
        
        if(idDimero!=0){
            if(salida.length()>0){
                salida+=" AND ";
            }
            salida+="idDimero=" + idDimero;
        }
        
        if(idGen!=0){
            if(salida.length()>0){
                salida+=" AND ";
            }
            salida+="idGen=" + idGen;
        }
        if(salida.length()>0){
            salida=" WHERE " + salida;
        }
        return salida;
    }
    public ArrayList<Dimero> getDatos(){
        ArrayList<Dimero> dimeros=new ArrayList();
        
        String query="SELECT\n" +
        "idGen,\n" +
        "idDimero,\n" +
        "nombreDimero,\n" +
        "frecuencia,\n" +
        "distanciaMinima,\n" +
        "distanciaMaxima,\n" +
        "distanciaPromedio,\n" +
        "frecuenciaPurinas,\n" +
        "frecuenciaPirimidinas,\n" +
        "frecuenciaDurezaDebil,\n" +
        "frecuenciaDurezaFuerte\n" +
        "FROM vtaC_Dimero\n"+
        getFiltro();
        
        
        Statement st;
        try {
            st = conexion.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Dimero dimero=new Dimero();
                dimero.setIdDimero(rs.getInt("idDimero"));
                dimero.setIdGen(rs.getInt("idGen"));
                dimero.setNombreDimero(rs.getString("nombreDimero"));
                dimero.setFrecuencia(rs.getDouble("frecuencia"));
                dimero.setDistanciaMinima(rs.getDouble("distanciaMinima"));
                dimero.setDistanciaMaxima(rs.getDouble("distanciaMaxima"));
                dimero.setDistanciaPromedio(rs.getDouble("distanciaPromedio"));
                dimero.setFrecuenciaPurinas(rs.getDouble("frecuenciaPurinas"));
                dimero.setFrecuenciaPirimidinas(rs.getDouble("frecuenciaPirimidinas"));
                dimero.setFrecuenciaDurezaDebil(rs.getDouble("frecuenciaDurezaDebil"));
                dimero.setFrecuenciaDurezaFuerte(rs.getDouble("frecuenciaDurezaFuerte"));
                
                dimeros.add(dimero);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dimeros.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return dimeros;
    }
    public boolean dimeroAgregar(Dimero dimero){
        boolean salida=true;
        try {
            CallableStatement cst = conexion.prepareCall("{call dimero_EstadisticaAgregar (?,?,?,?,?,?,?,?,?,?,?,?)}");
            cst.setLong(1,dimero.getIdGen());
            cst.setString(2,dimero.getNombreDimero());
            cst.setDouble(3,dimero.getFrecuencia());
            cst.setDouble(4,dimero.getDistanciaMinima());
            cst.setDouble(5,dimero.getDistanciaMaxima());
            cst.setDouble(6,dimero.getDistanciaPromedio());
            cst.setDouble(7,dimero.getFrecuenciaPurinas());
            cst.setDouble(8,dimero.getFrecuenciaPirimidinas());
            cst.setDouble(9,dimero.getFrecuenciaDurezaDebil());
            cst.setDouble(10,dimero.getFrecuenciaDurezaFuerte());
            
            cst.registerOutParameter(11, Types.INTEGER);
            cst.registerOutParameter(12, Types.VARCHAR);
            
            cst.execute();
            
            int noError=cst.getInt(11);
            String mensaje=cst.getString(12);
        } catch (SQLException ex) {
            salida=false;
            Logger.getLogger(Dimeros.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salida;
    }
}
