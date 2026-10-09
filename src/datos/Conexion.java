
package sistemagestionfarmacia.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "";

    private static final String USUARIO = "TU_USUARIO";
    private static final String CLAVE = "TU_CONTRASEÑA";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}
