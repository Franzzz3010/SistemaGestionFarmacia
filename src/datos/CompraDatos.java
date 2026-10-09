
package sistemagestionfarmacia.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemagestionfarmacia.modelo.Compra;

public class CompraDatos {

    public boolean agregar(Compra compra) throws SQLException {
        String sql = "INSERT INTO Compra "
                + "(fecha, total, idProveedor) "
                + "VALUES (?, ?, ?)";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(compra.getFecha().getTime()));
            ps.setDouble(2, compra.getTotal());
            ps.setInt(3, compra.getIdProveedor());

            return ps.executeUpdate() > 0;
        }
    }

    public List<Compra> listar() throws SQLException {
        List<Compra> lista = new ArrayList<>();

        String sql = "SELECT idCompra, fecha, total, idProveedor "
                + "FROM Compra";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Compra compra = new Compra(
                        rs.getInt("idCompra"),
                        rs.getDate("fecha"),
                        rs.getDouble("total"),
                        rs.getInt("idProveedor")
                );

                lista.add(compra);
            }
        }

        return lista;
    }

    public Compra buscar(int idCompra) throws SQLException {
        String sql = "SELECT idCompra, fecha, total, idProveedor "
                + "FROM Compra WHERE idCompra = ?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idCompra);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Compra(
                            rs.getInt("idCompra"),
                            rs.getDate("fecha"),
                            rs.getDouble("total"),
                            rs.getInt("idProveedor")
                    );
                }
            }
        }

        return null;
    }

    public boolean modificar(Compra compra) throws SQLException {
        String sql = "UPDATE Compra SET fecha = ?, "
                + "total = ?, idProveedor = ? "
                + "WHERE idCompra = ?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(compra.getFecha().getTime()));
            ps.setDouble(2, compra.getTotal());
            ps.setInt(3, compra.getIdProveedor());
            ps.setInt(4, compra.getIdCompra());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int idCompra) throws SQLException {
        String sql = "DELETE FROM Compra WHERE idCompra = ?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idCompra);

            return ps.executeUpdate() > 0;
        }
    }
}
