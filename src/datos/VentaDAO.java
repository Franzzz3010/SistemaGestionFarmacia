
package datos;

import sistemagestionfarmacia.datos.Conexion;
import modelo.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.DetalleVenta;
import java.sql.Statement;
import java.math.BigDecimal;
import sistemagestionfarmacia.datos.MedicamentoDatos;

public class VentaDAO {

    public boolean insertar(Venta venta) {

        String sql = "INSERT INTO venta "
                + "(idCliente, fecha, total) "
                + "VALUES (?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, venta.getIdCliente());
            ps.setDate(2, new java.sql.Date(venta.getFecha().getTime()));
            ps.setDouble(3, venta.getTotal());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al insertar venta: " + e.getMessage());
            return false;
        }
    }

    public List<Venta> listar() {

        List<Venta> ventas = new ArrayList<>();

        String sql = "SELECT * FROM venta";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Venta venta = new Venta();

                venta.setIdVenta(rs.getInt("idVenta"));
                venta.setIdCliente(rs.getInt("idCliente"));
                venta.setFecha(rs.getDate("fecha"));
                venta.setTotal(rs.getDouble("total"));

                ventas.add(venta);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar ventas: " + e.getMessage());
        }

        return ventas;
    }

    public boolean actualizar(Venta venta) {

        String sql = "UPDATE venta SET "
                + "idCliente = ?, "
                + "fecha = ?, "
                + "total = ? "
                + "WHERE idVenta = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, venta.getIdCliente());
            ps.setDate(2, new java.sql.Date(venta.getFecha().getTime()));
            ps.setDouble(3, venta.getTotal());
            ps.setInt(4, venta.getIdVenta());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar venta: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idVenta) {

        String sql = "DELETE FROM venta WHERE idVenta = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idVenta);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar venta: " + e.getMessage());
            return false;
        }
    }
    
    
public int registrarVentaCompleta(
        Venta venta, List<DetalleVenta> detalles) throws SQLException {

    if (venta == null || venta.getIdCliente() <= 0
            || venta.getFecha() == null
            || detalles == null || detalles.isEmpty()) {

        throw new IllegalArgumentException(
                "Los datos de la venta están incompletos.");
    }

    try (Connection con = Conexion.conectar()) {

        con.setAutoCommit(false);

        try {

            int idVenta;

            String sqlVenta =
                    "INSERT INTO dbo.Venta (idCliente, fecha, total) "
                    + "VALUES (?, ?, ?)";

            try (PreparedStatement ps = con.prepareStatement(
                    sqlVenta, Statement.RETURN_GENERATED_KEYS)) {

                ps.setInt(1, venta.getIdCliente());
                ps.setDate(2, new java.sql.Date(
                        venta.getFecha().getTime()));
                ps.setBigDecimal(3, BigDecimal.ZERO);

                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {

                    if (!rs.next()) {
                        throw new SQLException(
                                "No se pudo obtener el ID de la venta.");
                    }

                    idVenta = rs.getInt(1);
                }
            }

            MedicamentoDatos medicamentoDatos =
                    new MedicamentoDatos();

            BigDecimal total = BigDecimal.ZERO;

            String sqlMedicamento =
                    "SELECT precio, requiereReceta "
                    + "FROM dbo.Medicamento "
                    + "WHERE idMedicamento = ?";

            String sqlReceta =
                    "SELECT idReceta, medicamentosAutorizados "
                    + "FROM dbo.RecetaMedica "
                    + "WHERE idReceta = ? AND idCliente = ?";

            String sqlDetalle =
                    "INSERT INTO dbo.DetalleVenta "
                    + "(idVenta, idMedicamento, cantidad, precio, idReceta) "
                    + "VALUES (?, ?, ?, ?, ?)";

            for (DetalleVenta detalle : detalles) {

                if (detalle == null
                        || detalle.getIdMedicamento() <= 0
                        || detalle.getCantidad() <= 0) {

                    throw new SQLException(
                            "Datos del medicamento inválidos.");
                }

                int idMedicamento = detalle.getIdMedicamento();
                int cantidad = detalle.getCantidad();

                BigDecimal precio;
                boolean requiereReceta;

                try (PreparedStatement ps =
                        con.prepareStatement(sqlMedicamento)) {

                    ps.setInt(1, idMedicamento);

                    try (ResultSet rs = ps.executeQuery()) {

                        if (!rs.next()) {
                            throw new SQLException(
                                    "Medicamento no encontrado: "
                                    + idMedicamento);
                        }

                        precio = rs.getBigDecimal("precio");
                        requiereReceta =
                                rs.getBoolean("requiereReceta");
                    }
                }

                Integer idReceta = detalle.getIdReceta();

                if (requiereReceta) {

                    if (idReceta == null
                            || detalle.getCantidadVerificada() != cantidad) {

                        throw new SQLException(
                                "El medicamento " + idMedicamento
                                + " requiere una receta revisada "
                                + "para la cantidad total.");
                    }

                    try (PreparedStatement ps =
                            con.prepareStatement(sqlReceta)) {

                        ps.setInt(1, idReceta);
                        ps.setInt(2, venta.getIdCliente());

                        try (ResultSet rs = ps.executeQuery()) {

                            if (!rs.next()) {
                                throw new SQLException(
                                        "La receta no pertenece al cliente "
                                        + "o no existe.");
                            }

                            String autorizados =
                                    rs.getString("medicamentosAutorizados");

                            if (autorizados == null
                                    || autorizados.trim().isEmpty()) {

                                throw new SQLException(
                                        "La receta no contiene "
                                        + "información de medicamentos.");
                            }
                        }
                    }

                } else {

                    idReceta = null;
                }

                if (!medicamentoDatos.disminuirExistencia(
                        con, idMedicamento, cantidad)) {

                    throw new SQLException(
                            "Existencia insuficiente para el medicamento: "
                            + idMedicamento);
                }

                try (PreparedStatement ps =
                        con.prepareStatement(sqlDetalle)) {

                    ps.setInt(1, idVenta);
                    ps.setInt(2, idMedicamento);
                    ps.setInt(3, cantidad);
                    ps.setBigDecimal(4, precio);

                    if (idReceta == null) {
                        ps.setNull(5, java.sql.Types.INTEGER);
                    } else {
                        ps.setInt(5, idReceta);
                    }

                    if (ps.executeUpdate() != 1) {
                        throw new SQLException(
                                "No se pudo guardar el detalle.");
                    }
                }

                total = total.add(
                        precio.multiply(BigDecimal.valueOf(cantidad)));
            }

            String sqlTotal =
                    "UPDATE dbo.Venta SET total = ? "
                    + "WHERE idVenta = ?";

            try (PreparedStatement ps =
                    con.prepareStatement(sqlTotal)) {

                ps.setBigDecimal(1, total);
                ps.setInt(2, idVenta);

                if (ps.executeUpdate() != 1) {
                    throw new SQLException(
                            "No se pudo actualizar el total.");
                }
            }

            con.commit();

            return idVenta;

        } catch (Exception e) {

            try {
                con.rollback();
            } catch (SQLException rollbackError) {
                e.addSuppressed(rollbackError);
            }

            if (e instanceof SQLException) {
                throw (SQLException) e;
            }

            throw new SQLException(
                    "Error al registrar la venta.", e);

        } finally {

            con.setAutoCommit(true);
        }
    }
}

}
