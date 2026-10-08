/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

/**
 *
 * @author ec745
 */
import modelo.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}