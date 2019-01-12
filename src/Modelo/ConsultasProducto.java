package Modelo;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Cris
 */
// Aplicamos la Herencia (heredamos la conexion).
public class ConsultasProducto extends Conexion {
    
    //Metodo para registrar, el cual recibe como parametro el modelo.

    public boolean registrar(Producto pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        //hacemos nuestra consulta, los values los hacemos con signos de interrogacion para que se prepare
        //la consulta.
        String sql = "INSERT INTO producto (codigo, nombre, precio, cantidad)VALUES(?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setDouble(3, pro.getPrecio());
            ps.setInt(4, pro.getCantidad());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

    }
    
    //Metodo para modificar, el cual recibe como parametro el modelo.
    
    public boolean modificar(Producto pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        //hacemos nuestra consulta.
        String sql = "UPDATE producto SET codigo =?, nombre=?, precio=?, cantidad=? WHERE id =?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setDouble(3, pro.getPrecio());
            ps.setInt(4, pro.getCantidad());
            ps.setInt(5, pro.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

    }
    
    //Metodo para eliminar, el cual recibe como parametro el modelo.
    
    public boolean eliminar(Producto pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        //hacemos nuestra consulta.
        String sql = "DELETE FROM producto WHERE id =?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pro.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

    }
    
    //Metodo para buscar, el cual recibe como parametro el modelo.
    
     public boolean buscar(Producto pro) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        //hacemos nuestra consulta.
        String sql = "SELECT * FROM producto WHERE codigo =?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            rs = ps.executeQuery();
            if(rs.next())
            {
                pro.setId(Integer.parseInt(rs.getString("id")));
                pro.setCodigo(rs.getString("codigo"));
                pro.setNombre(rs.getString("nombre"));
                pro.setPrecio(Double.parseDouble(rs.getString("precio")));
                pro.setCantidad(Integer.parseInt(rs.getString("cantidad")));
                
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

    }
      public DefaultTableModel mostrar() {
        DefaultTableModel modelo = new DefaultTableModel();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        //hacemos nuestra consulta.
        String sql = "SELECT * FROM producto";
        modelo.setColumnIdentifiers(new Object[]{"Codigo","Producto","Valor","Cantidad"});
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
             modelo.addRow(new Object[]{ rs.getString("codigo"),rs.getString("nombre"),rs.getString("precio"),rs.getString("cantidad")});
            
            }
            
          return modelo;
          
            
        } catch (SQLException e) {
            System.err.println(e);
           
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
       // modelo.fireTableDataChanged();
      return modelo;
      
      }
      
}