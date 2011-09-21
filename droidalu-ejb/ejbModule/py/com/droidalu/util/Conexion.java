package py.com.droidalu.util;

import java.sql.*;
/**
 * 
 * @author mxbg
 *
 */
public class Conexion {
    /**
     * @param driver es el nombre del driver del gestor que se va utilizar en este caso, postgres
     */
    public boolean cargarDriver(String driver) {
        try {
            Class.forName(driver);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    /**
     * Establece una nueva conexion y la anhade al la lista de conexiones
     * establecidas
     * @param server la direccion del servidor
     * @param BD el nombre de la base de datos
     * @param user el usuario
     * @param pass la contrasenha 
     */
    public Connection conectar(String server, String BD, String user, String pass) {
        Connection con = null;
        try {
            DriverManager.setLoginTimeout(20);
            con = DriverManager.getConnection("jdbc:postgresql://" + server + ":5432/" + BD, user, pass);
        } catch (Exception e) {
            System.err.println(e);
        }
        return con;

    }
}
