
package sistemagestionfarmacia.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import modelo.Venta;
import modelo.DetalleVenta;

public class RegistroVentaDatos {

    public int registrarVenta(Venta venta,
            List<DetalleVenta> detalles) throws SQLException {

        if (venta == null || venta.getFecha() == null
                || venta.getIdCliente() <= 0) {
            throw new IllegalArgumentException(
                    "Los datos de la venta son inválidos.");
        }

        if (detalles == null || detalles.isEmpty()) {
            throw new IllegalArgumentException(
                    "La venta debe tener al menos un medicamento.");
        }

        double total = 0;

        for (DetalleVenta detalle : detalles) {

            if (detalle == null
                    || detalle.getIdMedicamento() <= 0
                    || detalle.getCantidad() <= 0
                    || detalle.getPrecio() < 0) {

                throw new IllegalArgumentException(
                        "Hay un detalle de venta inválido.");
            }

            total += detalle.getCantidad() * detalle.getPrecio();
        }

        String sqlVenta =
                "INSERT INTO Venta (idCliente, fecha, total) "
                + "VALUES (?, ?, ?)";

        String sqlDetalle =
                "INSERT INTO dbo.DetalleVenta "
                + "(idVenta, idMedicamento, cantidad, precio) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection cn = Conexion.conectar()) {

            cn.setAutoCommit(false);

            try {

                int idVenta;

                try (PreparedStatement ps = cn.prepareStatement(
                        sqlVenta, Statement.RETURN_GENERATED_KEYS)) {

                    ps.setInt(1, venta.getIdCliente());
                    ps.setDate(2, new java.sql.Date(
                            venta.getFecha().getTime()));
                    ps.setDouble(3, total);

                    if (ps.executeUpdate() != 1) {
                        throw new SQLException(
                                "No se pudo registrar la venta.");
                    }

                    try (ResultSet rs = ps.getGeneratedKeys()) {

                        if (!rs.next()) {
                            throw new SQLException(
                                    "No se obtuvo el ID de la venta.");
                        }

                        idVenta = rs.getInt(1);
                    }
                }

                MedicamentoDatos medicamentoDatos =
                        new MedicamentoDatos();

                try (PreparedStatement ps = cn.prepareStatement(
                        sqlDetalle)) {

                    for (DetalleVenta detalle : detalles) {

                        boolean actualizado =
                                medicamentoDatos.disminuirExistencia(
                                        cn,
                                        detalle.getIdMedicamento(),
                                        detalle.getCantidad());

                        if (!actualizado) {
                            throw new SQLException(
                                    "Medicamento inexistente o "
                                    + "existencias insuficientes. ID: "
                                    + detalle.getIdMedicamento());
                        }

                        ps.setInt(1, idVenta);
                        ps.setInt(2, detalle.getIdMedicamento());
                        ps.setInt(3, detalle.getCantidad());
                        ps.setDouble(4, detalle.getPrecio());

                        if (ps.executeUpdate() != 1) {
                            throw new SQLException(
                                    "No se pudo registrar un detalle.");
                        }
                    }
                }

                cn.commit();

                venta.setIdVenta(idVenta);
                venta.setTotal(total);

                return idVenta;

            } catch (SQLException | RuntimeException e) {

                try {
                    cn.rollback();
                } catch (SQLException ex) {
                    e.addSuppressed(ex);
                }

                throw e;

            } finally {
                cn.setAutoCommit(true);
            }
        }
    }
}
