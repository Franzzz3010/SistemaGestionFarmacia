/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

/**
 *
 * @author ec745
 */
import modelo.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    public boolean insertar(Empleado empleado) {

        String sql = "INSERT INTO empleado "
                + "(nombre, telefono, correo, puesto) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getTelefono());
            ps.setString(3, empleado.getCorreo());
            ps.setString(4, empleado.getPuesto());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al insertar empleado: "
                    + e.getMessage());
            return false;
        }
    }

    public List<Empleado> listar() {

        List<Empleado> empleados = new ArrayList<>();

        String sql = "SELECT * FROM empleado";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Empleado empleado = new Empleado();

                empleado.setIdEmpleado(
                        rs.getInt("idEmpleado"));

                empleado.setNombre(
                        rs.getString("nombre"));

                empleado.setTelefono(
                        rs.getString("telefono"));

                empleado.setCorreo(
                        rs.getString("correo"));

                empleado.setPuesto(
                        rs.getString("puesto"));

                empleados.add(empleado);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar empleados: "
                    + e.getMessage());
        }

        return empleados;
    }

    public boolean actualizar(Empleado empleado) {

        String sql = "UPDATE empleado SET "
                + "nombre = ?, "
                + "telefono = ?, "
                + "correo = ?, "
                + "puesto = ? "
                + "WHERE idEmpleado = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getTelefono());
            ps.setString(3, empleado.getCorreo());
            ps.setString(4, empleado.getPuesto());
            ps.setInt(5, empleado.getIdEmpleado());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar empleado: "
                    + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idEmpleado) {

        String sql = "DELETE FROM empleado "
                + "WHERE idEmpleado = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEmpleado);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar empleado: "
                    + e.getMessage());
            return false;
        }
    }
}