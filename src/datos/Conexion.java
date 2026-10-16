
package sistemagestionfarmacia.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:sqlserver://localhost:1433;"
            + "databaseName=GestionFarmacia;"
            + "encrypt=true;"
            + "trustServerCertificate=true;"
            + "integratedSecurity=true;"
            + "authenticationScheme=NativeAuthentication;";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
