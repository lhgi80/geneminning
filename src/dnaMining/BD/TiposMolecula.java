/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnaMining.BD;
import BD.Conexion;
import dnaMining.BD.Clases.TipoMolecula;
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
public class TiposMolecula {
    private int idTipoMolecula;
    private String tipoMolecula;
    private Connection conexion;

    public TiposMolecula() {
        idTipoMolecula=0;
        tipoMolecula="";
        
        conexion=Conexion.getConexion();
    }

    public int getIdTipoMolecula() {
        return idTipoMolecula;
    }

    public void setIdTipoMolecula(int idTipoMolecula) {
        this.idTipoMolecula = idTipoMolecula;
    }

    public String getTipoMolecula() {
        return tipoMolecula;
    }

    public void setTipoMolecula(String tipoMolecula) {
        this.tipoMolecula = tipoMolecula;
    }
    private String getFiltro(){
        String salida="";
        
        if(idTipoMolecula!=0){
            if(salida.length()>0){
                salida+=" AND ";
            }
            salida+="idTipoMolecula=" + idTipoMolecula + " ";
        }
        
        if(tipoMolecula.length()!=0){
            if(salida.length()>0){
                salida+=" AND ";
            }
            salida+="tipoMolecula='" + tipoMolecula + "'";
        }
        if(salida.length()>0){
            salida=" WHERE " + salida;
        }
        return salida;
    }
    public ArrayList<TipoMolecula> getDatos(){
        ArrayList<TipoMolecula> tiposMolecula=new ArrayList();
        
        String query="SELECT\n" +
        "idTipoMolecula,\n" +
        "tipoMolecula\n" +
        
        "FROM TipoMolecula\n"+
        getFiltro();
        
        
        Statement st;
        try {
            st = conexion.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                TipoMolecula t1=new TipoMolecula();
                t1.setIdTipoMolecula(rs.getInt("idTipoMolecula"));
                t1.setTipoMolecula(rs.getString("tipoMolecula"));
                tiposMolecula.add(t1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TiposMolecula.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(query);
        }
        return tiposMolecula;
    }
    
    
    public int getTipoMoleculaID(){
        int salida=-1;
        
        if(tipoMolecula.length()!=0){
            ArrayList<TipoMolecula> tipos=getDatos();
            if(tipos.size()>0){
                TipoMolecula t1=tipos.get(0);
                salida=t1.getIdTipoMolecula();
            }
            
        }
        
        return salida;
    }
    
}
