

package Modelo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Cris
 */
public class Conexion {
    
    //Definicion de variables (constantes) de conexion
    
    private final String base = "Tienda";
    private final String user = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3306/" + base;
    
    //Variable que guardara nuestra conexion
    
    private Connection con =null;
    
    //Metodo en cual crearemos nuestra conexion
    
    public Connection getConexion()
    {
      try
      {
          //Controlador para realizar la conexion
          Class.forName("com.mysql.jdbc.Driver");
          con = (Connection) DriverManager.getConnection(this.url, this.user, this.password);          
      }
      catch(SQLException e)
      {
          System.err.println(e);
      
      } 
      catch (ClassNotFoundException ex) 
      {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      return con;
    }
    
}
