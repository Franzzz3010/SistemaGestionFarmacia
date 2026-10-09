
package sistemagestionfarmacia.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemagestionfarmacia.modelo.Proveedor;

public class ProveedorDatos {

    public boolean agregar(Proveedor proveedor) throws SQLException {
        String sql = "INSERT INTO Proveedor "
                + "(nombre, telefono, correo, direccion) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getTelefono());
            ps.setString(3, proveedor.getCorreo());
            ps.setString(4, proveedor.getDireccion());

            return ps.executeUpdate() > 0;
        }
    }

    public List<Proveedor> listar() throws SQLException {
        List<Proveedor> lista = new ArrayList<>();

        String sql = "SELECT idProveedor, nombre, telefono, "
                + "correo, direccion FROM Proveedor";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Proveedor proveedor = new Proveedor(
                        rs.getInt("idProveedor"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        rs.getString("direccion")
                );

                lista.add(proveedor);
            }
        }

        return lista;
    }

    public Proveedor buscar(int idProveedor) throws SQLException {
        String sql = "SELECT idProveedor, nombre, telefono, "
                + "correo, direccion FROM Proveedor "
                + "WHERE idProveedor = ?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idProveedor);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Proveedor(
                            rs.getInt("idProveedor"),
                            rs.getString("nombre"),
                            rs.getString("telefono"),
                            rs.getString("correo"),
                            rs.getString("direccion")
                    );
                }
            }
        }

        return null;
    }

    public boolean modificar(Proveedor proveedor) throws SQLException {
        String sql = "UPDATE Proveedor SET nombre = ?, "
                + "telefono = ?, correo = ?, direccion = ? "
                + "WHERE idProveedor = ?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getTelefono());
            ps.setString(3, proveedor.getCorreo());
            ps.setString(4, proveedor.getDireccion());
            ps.setInt(5, proveedor.getIdProveedor());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int idProveedor) throws SQLException {
        String sql = "DELETE FROM Proveedor WHERE idProveedor = ?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idProveedor);

            return ps.executeUpdate() > 0;
        }
    }
}
