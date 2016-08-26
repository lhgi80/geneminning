/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnaMining.BD;
import BD.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

import dnaMining.BD.Clases.Topologia;
/**
 *
 * @author Luis
 */
public class Topologias {
    private int idTopologia;
    private String topologia;
    private Connection conexion;

    public Topologias() {
        idTopologia=0;
        topologia="";
        
        conexion=Conexion.getConexion();
    }

    public Topologias(int idTopologia, String topologia) {
        this.idTopologia = idTopologia;
        this.topologia = topologia;
    }

    public int getIdTopologia() {
        return idTopologia;
    }

    public void setIdTopologia(int idTopologia) {
        this.idTopologia = idTopologia;
    }

    public String getTopologia() {
        return topologia;
    }

    public void setTopologia(String topologia) {
        this.topologia = topologia;
    }
    private String getFiltro(){
        String salida="";
        
        if(idTopologia!=0){
            if(salida.length()>0){
                salida+=" AND ";
            }
            salida+="idTopologia=" + idTopologia + " ";
        }
        
        if(topologia.length()!=0){
            if(salida.length()>0){
                salida+=" AND ";
            }
            salida+="topologia='" + topologia + "'";
        }
        if(salida.length()>0){
            salida=" WHERE " + salida;
        }
        return salida;
    }
    public ArrayList<Topologia> getDatos(){
        ArrayList<Topologia> topologias=new ArrayList();
        
        String query="SELECT\n" +
        "idTopologia,\n" +
        "topologia\n" +
        
        "FROM Topologia\n"+
        getFiltro();
        
        
        Statement st;
        try {
            st = conexion.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Topologia t1=new Topologia();
                t1.setIdTopologia(rs.getInt("idTopologia"));
                t1.setTopologia(rs.getString("topologia"));
                topologias.add(t1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Topologias.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(query);
        }
        return topologias;
    }
    
    
    public int getTopologiaID(){
        int salida=-1;
        
        if(topologia.length()!=0){
            ArrayList<Topologia> tipos=getDatos();
            if(tipos.size()>0){
                Topologia t1=tipos.get(0);
                salida=t1.getIdTopologia();
            }   
        }
        return salida;
    }
    
    
}
