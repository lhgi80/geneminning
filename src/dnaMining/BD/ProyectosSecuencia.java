/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnaMining.BD;

import BD.Conexion;
import dnaMining.BD.Clases.Secuencia;
import dnaMining.BD.Clases.ProyectoSecuencia;
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
public class ProyectosSecuencia {
    private int idProyecto;
    private int idSecuencia;
    private ProyectoSecuencia proyectoGen;
    private Connection conexion;
    
    public ProyectosSecuencia(){
        conexion = Conexion.getConexion();
        idSecuencia=0;
        idProyecto=0;
        proyectoGen=new ProyectoSecuencia();
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getIdSecuencia() {
        return idSecuencia;
    }

    public void setIdSecuencia(int idSecuencia) {
        this.idSecuencia = idSecuencia;
    }
    private String getFiltro(){
        String salida="";
        if(idProyecto!=0){
            if(salida.length()>0){
                salida+=" AND ";
            }
            salida+="idProyecto=" + idProyecto;
        }
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
    public ArrayList<ProyectoSecuencia> getDatos(){
        ArrayList<ProyectoSecuencia> datos=new ArrayList();
        
        String query="SELECT\n" +
        "idProyecto,\n" +
        "nombreProyecto,\n" +
        "idSecuencia,\n" +
        "nombreSecuencia,\n" +
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
        "FROM vtaC_Proyecto_Gen\n"+
        getFiltro();
        
        Statement st;
        try {
            st = conexion.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                ProyectoSecuencia pg=new ProyectoSecuencia();
                pg.setIdProyecto(rs.getInt("idProyecto"));
                pg.setNombreProyecto(rs.getString("nombreProyecto"));
                
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
                
                pg.setSecuencia(secuencia);
                datos.add(pg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dimeros.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }  
    public boolean proyectoSecuenciaAgregar(ProyectoSecuencia pg){
        boolean salida=true;
        try {
            conexion.setAutoCommit(false);
            Secuencias g=new Secuencias();
            g.secuenciaAgregar(pg.getSecuencia());
            
            CallableStatement cst = conexion.prepareCall("{call proyecto_SecuenciaAgregar (?,?,?,?)}");
            
            cst.setInt(1,pg.getIdProyecto());
            cst.setInt(2,g.getIdSecuencia());
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
                Logger.getLogger(ProyectosSecuencia.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        return salida;
    }
}
