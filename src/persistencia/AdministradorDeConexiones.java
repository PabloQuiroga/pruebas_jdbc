package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Pablo Daniel Quiroga
 */
public class AdministradorDeConexiones {

    public static Connection conexion;
	
    /**
     * Establece la conexion en base a datos predeterminados
     * @return Connection
     */
    public static Connection getConexion(){
	if(conexion == null){
            try{
                String dbDriver = "com.mysql.jdbc.Driver";
		String dbConnString = "jdbc:mysql://localhost/test";
		String dbUser = "root";
		String dbPassword = "";
		Class.forName(dbDriver);
		conexion = DriverManager.getConnection(dbConnString, dbUser, dbPassword);
            }catch(SQLException sqlEx){
                JOptionPane.showMessageDialog(null, "Error al establecer la conexion:\n" 
                        + sqlEx.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }catch(ClassNotFoundException cnfEx){
                JOptionPane.showMessageDialog(null, "Error en la configuracion del driver:\n"
                        + cnfEx.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
	}
        return conexion;
    }
	
    /**
     * Comprueba la conexion a la base de datos
     */
    public void probar(){
        conexion = getConexion();
        if(conexion == null){
            JOptionPane.showMessageDialog(null, "sin conexion");
        }else{
            JOptionPane.showMessageDialog(null, "conectado");
        }
    }
    /**
     * Cierra la conexion
     * solo para llamar al finalizar el programa
     */
    public void cerrarConnection(){
        if(conexion != null){
            try{
                conexion.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexion:\n"
                        + ex.getMessage(), "Error al cerrar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
