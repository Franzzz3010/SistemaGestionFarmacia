
package sistemagestionfarmacia;

import java.sql.Connection;
import java.sql.SQLException;
import sistemagestionfarmacia.datos.Conexion;

public class PruebaConexion {

    public static void main(String[] args) {

        try (Connection cn = Conexion.conectar()) {

            if (cn != null && !cn.isClosed()) {
                System.out.println("CONEXION EXITOSA");
                System.out.println("Conectado a SQL Server");
                System.out.println("Base de datos: GestionFarmacia");
            }

        } catch (SQLException e) {
            System.out.println("ERROR DE CONEXION");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
