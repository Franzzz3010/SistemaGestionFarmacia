
package sistemagestionfarmacia.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemagestionfarmacia.modelo.DetalleCompra;

public class DetalleCompraDatos {

    public boolean agregar(DetalleCompra detalle) throws SQLException {
        String sql = "INSERT INTO DetalleCompra "
                + "(idCompra, idMedicamento, cantidad, costo) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, detalle.getIdCompra());
            ps.setInt(2, detalle.getIdMedicamento());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getCosto());

            return ps.executeUpdate() > 0;
        }
    }

    public List<DetalleCompra> listar() throws SQLException {
        List<DetalleCompra> lista = new ArrayList<>();

        String sql = "SELECT idDetalleCompra, idCompra, "
                + "idMedicamento, cantidad, costo "
                + "FROM DetalleCompra";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DetalleCompra detalle = new DetalleCompra(
                        rs.getInt("idDetalleCompra"),
                        rs.getInt("idCompra"),
                        rs.getInt("idMedicamento"),
                        rs.getInt("cantidad"),
                        rs.getDouble("costo")
                );

                lista.add(detalle);
            }
        }

        return lista;
    }

    public DetalleCompra buscar(int idDetalleCompra) throws SQLException {
        String sql = "SELECT idDetalleCompra, idCompra, "
                + "idMedicamento, cantidad, costo "
                + "FROM DetalleCompra WHERE idDetalleCompra = ?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idDetalleCompra);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new DetalleCompra(
                            rs.getInt("idDetalleCompra"),
                            rs.getInt("idCompra"),
                            rs.getInt("idMedicamento"),
                            rs.getInt("cantidad"),
                            rs.getDouble("costo")
                    );
                }
            }
        }

        return null;
    }

    public List<DetalleCompra> listarPorCompra(int idCompra)
            throws SQLException {

        List<DetalleCompra> lista = new ArrayList<>();

        String sql = "SELECT idDetalleCompra, idCompra, "
                + "idMedicamento, cantidad, costo "
                + "FROM DetalleCompra WHERE idCompra = ?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idCompra);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DetalleCompra detalle = new DetalleCompra(
                            rs.getInt("idDetalleCompra"),
                            rs.getInt("idCompra"),
                            rs.getInt("idMedicamento"),
                            rs.getInt("cantidad"),
                            rs.getDouble("costo")
                    );

                    lista.add(detalle);
                }
            }
        }

        return lista;
    }

    public boolean modificar(DetalleCompra detalle) throws SQLException {
        String sql = "UPDATE DetalleCompra SET "
                + "idCompra = ?, idMedicamento = ?, "
                + "cantidad = ?, costo = ? "
                + "WHERE idDetalleCompra = ?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, detalle.getIdCompra());
            ps.setInt(2, detalle.getIdMedicamento());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getCosto());
            ps.setInt(5, detalle.getIdDetalleCompra());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int idDetalleCompra) throws SQLException {
        String sql = "DELETE FROM DetalleCompra "
                + "WHERE idDetalleCompra = ?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idDetalleCompra);

            return ps.executeUpdate() > 0;
        }
    }
}
