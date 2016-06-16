package BD;
/**
 * Clase para conexion de bases de datos
 *
 * @author Luis Heriberto Garcia Islas
 * @version 20150901
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Conexion {
    private String host="";
    static String bd="";
    private String usrBd="";
    private String pswBd="";
    private String conector="mysql";
    private String driver="com.mysql.jdbc.Driver";
    private String puerto="";
    static Connection conexion = null;

    public Conexion() {
      
    }

    public Conexion(String driver,String host,String bd,String usrBd,String pswBd) {
        setDatosDefault(driver, host, bd, usrBd, pswBd);
    }

    public static void setBd(String bd) {
        Conexion.bd = bd;
    }
    
    public void setDriver(String conector) {
        switch(driver.toUpperCase()){
            case "MYSQL":
                this.driver="com.mysql.jdbc.Driver";
                this.puerto="";
                break;
            case "SQLSERVER":
                this.driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                this.puerto=":1433";
                break;
        }
        
    }
    public void setDatosDefault(String driver,String host,String bd,String usrBd,String pswBd) {
        this.host = host;
        setBd(bd);
        this.usrBd = usrBd;
        this.pswBd = pswBd;
        setDriver(driver);
        setConexion();
    }
    private void setConexion() {
        try {
            
            Class.forName(driver);
            String servidor = "jdbc:" + conector + "://" + host + puerto + "/" + bd;

            conexion = DriverManager.getConnection(servidor, usrBd, pswBd);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error1 en la Conexión con la BD " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error2 en la Conexión con la BD " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error3 en la Conexión con la BD " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } finally {
        }
    }

    public static Connection getConexion() {
        
        return conexion;
    }

    public static String getBd() {
        return bd;
    }
}
