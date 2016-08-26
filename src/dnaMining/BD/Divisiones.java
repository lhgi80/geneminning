/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnaMining.BD;
import BD.Conexion;
import dnaMining.BD.Clases.Division;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class Divisiones {
    private int idDivision;
    private String division;
    private Connection conexion;

    public Divisiones() {
        idDivision=0;
        division="";
        
        conexion=Conexion.getConexion();
    }

    public int getIdDivision() {
        return idDivision;
    }

    public void setIdDivision(int idDivision) {
        this.idDivision = idDivision;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }
    
    private String getFiltro(){
        String salida="";
        
        if(idDivision!=0){
            if(salida.length()>0){
                salida+=" AND ";
            }
            salida+="idDivision=" + idDivision + " ";
        }
        
        if(division.length()!=0){
            if(salida.length()>0){
                salida+=" AND ";
            }
            salida+="trim(division)='" + division + "'";
        }
        if(salida.length()>0){
            salida=" WHERE " + salida;
        }
        return salida;
    }
    public ArrayList<Division> getDatos(){
        ArrayList<Division> divisiones=new ArrayList();
        
        String query="SELECT\n" +
        "iddivision,\n" +
        "division,\n" +
        "descripcion\n" +
                
        "FROM Division\n"+
        getFiltro();
        
        
        Statement st;
        try {
            st = conexion.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Division d1=new Division();
                d1.setIdDivision(rs.getInt("idDivision"));
                d1.setDivision(rs.getString("division"));
                d1.setDescripcion(rs.getString("descripcion"));
                divisiones.add(d1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Divisiones.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(query);
        }
        return divisiones;
    }
    public int getDivisionID(){
        int salida=-1;
        
        if(division.length()!=0){
            ArrayList<Division> divs=getDatos();
            if(divs.size()>0){
                Division d1=divs.get(0);
                salida=d1.getIdDivision();
            }
        }
        return salida;
    }
}