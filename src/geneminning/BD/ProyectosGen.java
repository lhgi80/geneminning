/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneminning.BD;

import BD.Conexion;
import geneminning.BD.Clases.Gen;
import geneminning.BD.Clases.ProyectoGen;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Luis
 */
public class ProyectosGen {
    private int idProyecto;
    private int idGen;
    private ProyectoGen proyectoGen;
    private Connection conexion;
    
    public ProyectosGen(){
        conexion = Conexion.getConexion();
        idGen=0;
        idProyecto=0;
        proyectoGen=new ProyectoGen();
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getIdGen() {
        return idGen;
    }

    public void setIdGen(int idGen) {
        this.idGen = idGen;
    }
    private String getFiltro(){
        String salida="";
        if(idProyecto!=0){
            if(salida.length()>0){
                salida+=" AND ";
            }
            salida+="idProyecto=" + idProyecto;
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
    public ArrayList<ProyectoGen> getDatos(){
        ArrayList<ProyectoGen> datos=new ArrayList();
        
        String query="SELECT\n" +
        "idProyecto,\n" +
        "nombreProyecto,\n" +
        "idGen,\n" +
        "gen,\n" +
        "folio,\n" +
        "cadena,\n" +
        "idTipoGen,\n" +
        "tipoGen\n" +
        "FROM vtaC_Proyecto_Gen\n"+
        getFiltro();
        
        Statement st;
        try {
            st = conexion.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                ProyectoGen pg=new ProyectoGen();
                pg.setIdProyecto(rs.getInt("idProyecto"));
                pg.setNombreProyecto(rs.getString("nombreProyecto"));
                
                Gen gen=new Gen();
                gen.setIdGen(rs.getInt("idGen"));
                gen.setNombreGen(rs.getString("gen"));
                gen.setFolio(rs.getString("folio"));
                gen.setCadena(rs.getString("cadena"));
                gen.setIdTipoGen(rs.getInt("idTipoGen"));
                gen.setTipoGen(rs.getString("tipoGen"));
                
                pg.setGen(gen);
                datos.add(pg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dimeros.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }  
    public boolean proyectoGenAgregar(ProyectoGen pg){
        boolean salida=true;
        try {
            conexion.setAutoCommit(false);
            Genes g=new Genes();
            g.GenAgregar(pg.getGen());
            
            CallableStatement cst = conexion.prepareCall("{call proyecto_genAgregar (?,?,?,?)}");
            
            cst.setInt(1,pg.getIdProyecto());
            cst.setInt(2,g.getIdGen());
            cst.registerOutParameter(3, Types.INTEGER);
            cst.registerOutParameter(4, Types.VARCHAR);
            cst.execute();
            conexion.commit();
            int noError=cst.getInt(3);
            String mensaje=cst.getString(4);
            
            conexion.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                salida=false;
                Logger.getLogger(Dimeros.class.getName()).log(Level.SEVERE, null, ex);
                conexion.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ProyectosGen.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        return salida;
    }
}
