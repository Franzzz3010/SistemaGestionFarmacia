
package datos;

import sistemagestionfarmacia.datos.Conexion;
import modelo.RecetaMedica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecetaMedicaDAO {

    public boolean insertar(RecetaMedica receta) {

        String sql = "INSERT INTO RecetaMedica "
                + "(idCliente, medico, fecha, medicamentosAutorizados) "
                + "VALUES (?, ?, ?, ?)";    

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, receta.getIdCliente());
            ps.setString(2, receta.getMedico());
            ps.setDate(3, new java.sql.Date(
                    receta.getFecha().getTime()));
            ps.setString(4, receta.getMedicamentosAutorizados());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al insertar receta: "
                    + e.getMessage());
            return false;
        }
    }

    public List<RecetaMedica> listar() {

        List<RecetaMedica> recetas = new ArrayList<>();

        String sql = "SELECT * FROM RecetaMedica";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                RecetaMedica receta = new RecetaMedica();

                receta.setIdReceta(
                        rs.getInt("idReceta"));

                receta.setIdCliente(
                        rs.getInt("idCliente"));

                receta.setMedico(
                        rs.getString("medico"));

                receta.setFecha(
                        rs.getDate("fecha"));

                receta.setMedicamentosAutorizados(
                        rs.getString("medicamentosAutorizados"));

                recetas.add(receta);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar recetas: "
                    + e.getMessage());
        }

        return recetas;
    }

    public boolean actualizar(RecetaMedica receta) {

        String sql = "UPDATE RecetaMedica SET "
                + "idCliente = ?, "
                + "medico = ?, "
                + "fecha = ?, "
                + "medicamentosAutorizados = ? "
                + "WHERE idReceta = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, receta.getIdCliente());
            ps.setString(2, receta.getMedico());
            ps.setDate(3, new java.sql.Date(
                    receta.getFecha().getTime()));
            ps.setString(4, receta.getMedicamentosAutorizados());
            ps.setInt(5, receta.getIdReceta());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar receta: "
                    + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idReceta) {

        String sql = "DELETE FROM RecetaMedica "
                + "WHERE idReceta = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idReceta);

        return ps.executeUpdate() > 0;  

        } catch (SQLException e) {
            System.out.println("Error al eliminar receta: "
                    + e.getMessage());
            return false;
        }
    }
}