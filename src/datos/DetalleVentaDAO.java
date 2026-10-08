/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

/**
 *
 * @author ec745
 */
import modelo.DetalleVenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleVentaDAO {

    public boolean insertar(DetalleVenta detalle) {

        String sql = "INSERT INTO detalle_venta "
                + "(idVenta, idMedicamento, cantidad, precio) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, detalle.getIdVenta());
            ps.setInt(2, detalle.getIdMedicamento());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getPrecio());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al insertar detalle de venta: "
                    + e.getMessage());
            return false;
        }
    }

    public List<DetalleVenta> listar() {

        List<DetalleVenta> detalles = new ArrayList<>();

        String sql = "SELECT * FROM detalle_venta";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                DetalleVenta detalle = new DetalleVenta();

                detalle.setIdDetalleVenta(
                        rs.getInt("idDetalleVenta"));

                detalle.setIdVenta(
                        rs.getInt("idVenta"));

                detalle.setIdMedicamento(
                        rs.getInt("idMedicamento"));

                detalle.setCantidad(
                        rs.getInt("cantidad"));

                detalle.setPrecio(
                        rs.getDouble("precio"));

                detalles.add(detalle);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar detalles: "
                    + e.getMessage());
        }

        return detalles;
    }

    public boolean actualizar(DetalleVenta detalle) {

        String sql = "UPDATE detalle_venta SET "
                + "idVenta = ?, "
                + "idMedicamento = ?, "
                + "cantidad = ?, "
                + "precio = ? "
                + "WHERE idDetalleVenta = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, detalle.getIdVenta());
            ps.setInt(2, detalle.getIdMedicamento());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getPrecio());
            ps.setInt(5, detalle.getIdDetalleVenta());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar detalle: "
                    + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idDetalleVenta) {

        String sql = "DELETE FROM detalle_venta "
                + "WHERE idDetalleVenta = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idDetalleVenta);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar detalle: "
                    + e.getMessage());
            return false;
        }
    }
}