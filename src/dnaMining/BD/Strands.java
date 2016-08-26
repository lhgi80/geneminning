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

import dnaMining.BD.Clases.Strand;
/**
 *
 * @author Luis
 */
public class Strands {
    private int idStrand;
    private String strand;
    private Connection conexion;

    public Strands() {
        idStrand=0;
        strand="";
        conexion=Conexion.getConexion();
    }

    public int getIdStrand() {
        return idStrand;
    }

    public void setIdStrand(int idStrand) {
        this.idStrand = idStrand;
    }

    public String getStrand() {
        return strand;
    }

    public void setStrand(String strand) {
        this.strand = strand;
    }
    private String getFiltro(){
        String salida="";
        
        if(idStrand!=0){
            if(salida.length()>0){
                salida+=" AND ";
            }
            salida+="idStrand=" + idStrand + " ";
        }
        
        if(strand.length()!=0){
            if(salida.length()>0){
                salida+=" AND ";
            }
            salida+="strand='" + strand + "'";
        }
        if(salida.length()>0){
            salida=" WHERE " + salida;
        }
        return salida;
    }
    public ArrayList<Strand> getDatos(){
        ArrayList<Strand> strands=new ArrayList();
        
        String query="SELECT\n" +
        "idStrand,\n" +
        "strand\n" +
        
        "FROM Strand\n"+
        getFiltro();
        
        
        Statement st;
        try {
            st = conexion.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Strand s1=new Strand();
                s1.setIdStrand(rs.getInt("idStrand"));
                s1.setStrand(rs.getString("strand"));
                strands.add(s1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Strands.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(query);
        }
        return strands;
    }
    
    
    public int getStrandID(){
        int salida=-1;
        
        if(strand.length()!=0){
            ArrayList<Strand> tipos=getDatos();
            if(tipos.size()>0){
                Strand t1=tipos.get(0);
                salida=t1.getIdStrand();
            }   
        }
        return salida;
    }
    
}
