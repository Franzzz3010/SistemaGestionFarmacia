/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

/**
 *
 * @author ec745
 */
import modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public boolean insertar(Cliente cliente) {

        String sql = "INSERT INTO cliente "
                + "(nombre, telefono, correo, direccion) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getTelefono());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getDireccion());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }

    public List<Cliente> listar() {

        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT * FROM cliente";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setDireccion(rs.getString("direccion"));

                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }

        return clientes;
    }

    public boolean actualizar(Cliente cliente) {

        String sql = "UPDATE cliente SET "
                + "nombre = ?, "
                + "telefono = ?, "
                + "correo = ?, "
                + "direccion = ? "
                + "WHERE idCliente = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getTelefono());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getDireccion());
            ps.setInt(5, cliente.getIdCliente());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idCliente) {

        String sql = "DELETE FROM cliente WHERE idCliente = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }
}