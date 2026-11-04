
package sistemagestionfarmacia.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import sistemagestionfarmacia.modelo.Compra;
import sistemagestionfarmacia.modelo.DetalleCompra;

public class RegistroCompraDatos {

    public int registrarCompra(Compra compra,
            List<DetalleCompra> detalles) throws SQLException {

        if (compra == null || compra.getFecha() == null) {
            throw new IllegalArgumentException(
                    "Los datos de la compra son obligatorios.");
        }

        if (compra.getIdProveedor() <= 0) {
            throw new IllegalArgumentException(
                    "Selecciona un proveedor válido.");
        }

        if (detalles == null || detalles.isEmpty()) {
            throw new IllegalArgumentException(
                    "La compra debe tener al menos un medicamento.");
        }

        double total = 0;

        for (DetalleCompra detalle : detalles) {

            if (detalle == null
                    || detalle.getIdMedicamento() <= 0
                    || detalle.getCantidad() <= 0
                    || detalle.getCosto() < 0) {

                throw new IllegalArgumentException(
                        "Hay un detalle de compra inválido.");
            }

            total += detalle.getCantidad() * detalle.getCosto();
        }

        String sqlCompra =
                "INSERT INTO Compra (fecha, total, idProveedor) "
                + "VALUES (?, ?, ?)";

        String sqlDetalle =
                "INSERT INTO DetalleCompra "
                + "(idCompra, idMedicamento, cantidad, costo) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection cn = Conexion.conectar()) {

            cn.setAutoCommit(false);

            try {

                int idCompra;

                try (PreparedStatement ps = cn.prepareStatement(
                        sqlCompra, Statement.RETURN_GENERATED_KEYS)) {

                    ps.setDate(1, new java.sql.Date(
                            compra.getFecha().getTime()));
                    ps.setDouble(2, total);
                    ps.setInt(3, compra.getIdProveedor());

                    if (ps.executeUpdate() != 1) {
                        throw new SQLException(
                                "No se pudo registrar la compra.");
                    }

                    try (ResultSet rs = ps.getGeneratedKeys()) {

                        if (!rs.next()) {
                            throw new SQLException(
                                    "No se obtuvo el ID de la compra.");
                        }

                        idCompra = rs.getInt(1);
                    }
                }

                MedicamentoDatos medicamentoDatos =
                        new MedicamentoDatos();

                try (PreparedStatement ps = cn.prepareStatement(
                        sqlDetalle)) {

                    for (DetalleCompra detalle : detalles) {

                        ps.setInt(1, idCompra);
                        ps.setInt(2, detalle.getIdMedicamento());
                        ps.setInt(3, detalle.getCantidad());
                        ps.setDouble(4, detalle.getCosto());

                        if (ps.executeUpdate() != 1) {
                            throw new SQLException(
                                    "No se pudo registrar un detalle.");
                        }

                        boolean actualizado =
                                medicamentoDatos.aumentarExistencia(
                                        cn,
                                        detalle.getIdMedicamento(),
                                        detalle.getCantidad());

                        if (!actualizado) {
                            throw new SQLException(
                                    "No se pudo actualizar el inventario.");
                        }
                    }
                }

                cn.commit();

                compra.setIdCompra(idCompra);
                compra.setTotal(total);

                return idCompra;

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
