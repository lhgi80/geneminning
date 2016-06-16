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
import java.util.logging.Level;
import java.util.logging.Logger;
import geneminning.BD.Clases.Gen;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.ArrayList;
/**
 *
 * @author Luis
 */
public class Genes {
    private Gen gen;
    private int idGen;
    private int idTipoGen;
    Dimeros dimeros;
    private Connection conexion;

    public Genes() {
        conexion = Conexion.getConexion();
        idGen=0;
        idTipoGen=0;
        gen=new Gen();
    }

    public int getIdGen() {
        return idGen;
    }

    public void setIdGen(int idGen) {
        this.idGen = idGen;
    }

    public int getIdTipoGen() {
        return idTipoGen;
    }

    public void setIdTipoGen(int idTipoGen) {
        this.idTipoGen = idTipoGen;
    }
    
    private String getFiltro(){
        String salida="";
        if(idGen!=0){
            if(salida.length()>0){
                salida+=" AND ";
            }
            salida+="idGen=" + idGen;
        }
        if(idTipoGen!=0){
            if(salida.length()>0){
                salida+=" AND ";
            }
            salida+="idTipoGen=" + idTipoGen;
        }
        if(salida.length()>0){
            salida=" WHERE " + salida;
        }
        return salida;
    }
    public ArrayList<Gen> getDatos(){
        ArrayList<Gen> genes=new ArrayList();
        String query="SELECT\n" +
        "idGen,\n" +
        "nombre,\n" +
        "folio,\n" +
        "cadenaLimpia,\n" +
        "idTipoGen,\n" +
        "tipoGen,\n" +
        "frecuencia,\n" +
        "distanciaMinima,\n" +
        "distanciaMaxima,\n" +
        "distanciaPromedio,\n" +
        "frecuenciaPurinas,\n" +
        "frecuenciaPirimidinas,\n" +
        "frecuenciaDurezaDebil,\n" +
        "frecuenciaDurezaFuerte\n" +
        "FROM vtaC_Gen\n"+
        getFiltro();
        
        
        Statement st;
        try {
            st = conexion.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Gen gen=new Gen();
                gen.setIdGen(rs.getInt("idGen"));
                gen.setNombreGen(rs.getString("nombre"));
                gen.setFolio(rs.getString("folio"));
                gen.setCadena(rs.getString("cadenaLimpia"));
                gen.setIdTipoGen(rs.getInt("idTipoGen"));
                gen.setTipoGen(rs.getString("tipoGen"));
                gen.setFrecuencia(rs.getDouble("frecuencia"));
                gen.setDistanciaMinima(rs.getDouble("distanciaMinima"));
                gen.setDistanciaMaxima(rs.getDouble("distanciaMaxima"));
                gen.setDistanciaPromedio(rs.getDouble("distanciaPromedio"));
                gen.setFrecuenciaPurinas(rs.getDouble("frecuenciaPurinas"));
                gen.setFrecuenciaPirimidinas(rs.getDouble("frecuenciaPirimidinas"));
                gen.setFrecuenciaDurezaDebil(rs.getDouble("frecuenciaDurezaDebil"));
                gen.setFrecuenciaDurezaFuerte(rs.getDouble("frecuenciaDurezaFuerte"));
                
                dimeros=new Dimeros();
                dimeros.setIdGen(gen.getIdGen());
                ArrayList<Dimero> listadoDimeros=dimeros.getDatos();
                gen.setDimeros(listadoDimeros);
                genes.add(gen);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dimeros.class.getName()).log(Level.SEVERE, null, ex);
        }
        return genes;
    }
    public boolean GenAgregar(Gen gen){
        boolean salida=true;
        try {
            CallableStatement cst = conexion.prepareCall("{call gen_EstadisticaAgregar (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            
            cst.setString(1,gen.getNombreGen());
            cst.setString(2,gen.getFolio());
            cst.setString(3,gen.getCadena());
            cst.setInt(4,gen.getIdTipoGen());
            cst.setDouble(5,gen.getFrecuencia());
            cst.setDouble(6,gen.getDistanciaMinima());
            cst.setDouble(7,gen.getDistanciaMaxima());
            cst.setDouble(8,gen.getDistanciaPromedio());
            cst.setDouble(9,gen.getFrecuenciaPurinas());
            cst.setDouble(10,gen.getFrecuenciaPirimidinas());
            cst.setDouble(11,gen.getFrecuenciaDurezaDebil());
            cst.setDouble(12,gen.getFrecuenciaDurezaFuerte());
            
            cst.registerOutParameter(13, Types.INTEGER);
            cst.registerOutParameter(14, Types.INTEGER);
            cst.registerOutParameter(15, Types.VARCHAR);
            
            cst.execute();
            idGen=cst.getInt(13);
            int noError=cst.getInt(14);
            String mensaje=cst.getString(15);
            
            if(noError!=0){
                return false;
            }
            // Guarda los dimeros
            for(Dimero dimero:gen.getDimeros()){
                Dimeros d=new Dimeros();
                dimero.setIdGen(idGen);
                if(d.dimeroAgregar(dimero)==false){
                    return false;
                }
            }
            
        } catch (SQLException ex) {
            salida=false;
            Logger.getLogger(Dimeros.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salida;
    }
}
