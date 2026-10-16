
package sistemagestionfarmacia.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemagestionfarmacia.modelo.Medicamento;

public class MedicamentoDatos {

    public boolean agregar(Medicamento medicamento) throws SQLException {
        String sql = "INSERT INTO Medicamento "
                + "(nombre, presentacion, precio, existencia, requiereReceta, idCategoria) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, medicamento.getNombre());
            ps.setString(2, medicamento.getPresentacion());
            ps.setDouble(3, medicamento.getPrecio());
            ps.setInt(4, medicamento.getExistencia());
            ps.setBoolean(5, medicamento.isRequiereReceta());
            ps.setInt(6, medicamento.getIdCategoria());

            return ps.executeUpdate() > 0;
        }
    }

    public List<Medicamento> listar() throws SQLException {
        List<Medicamento> lista = new ArrayList<>();

        String sql = "SELECT idMedicamento, nombre, presentacion, "
                + "precio, existencia, requiereReceta, idCategoria "
                + "FROM Medicamento";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Medicamento medicamento = new Medicamento(
                        rs.getInt("idMedicamento"),
                        rs.getString("nombre"),
                        rs.getString("presentacion"),
                        rs.getDouble("precio"),
                        rs.getInt("existencia"),
                        rs.getBoolean("requiereReceta"),
                        rs.getInt("idCategoria")
                );

                lista.add(medicamento);
            }
        }

        return lista;
    }

    public Medicamento buscar(int idMedicamento) throws SQLException {
        String sql = "SELECT idMedicamento, nombre, presentacion, "
                + "precio, existencia, requiereReceta, idCategoria "
                + "FROM Medicamento WHERE idMedicamento = ?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idMedicamento);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new Medicamento(
                            rs.getInt("idMedicamento"),
                            rs.getString("nombre"),
                            rs.getString("presentacion"),
                            rs.getDouble("precio"),
                            rs.getInt("existencia"),
                            rs.getBoolean("requiereReceta"),
                            rs.getInt("idCategoria")
                    );
                }
            }
        }

        return null;
    }

    public boolean modificar(Medicamento medicamento) throws SQLException {
        String sql = "UPDATE Medicamento SET "
                + "nombre = ?, presentacion = ?, precio = ?, "
                + "existencia = ?, requiereReceta = ?, idCategoria = ? "
                + "WHERE idMedicamento = ?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, medicamento.getNombre());
            ps.setString(2, medicamento.getPresentacion());
            ps.setDouble(3, medicamento.getPrecio());
            ps.setInt(4, medicamento.getExistencia());
            ps.setBoolean(5, medicamento.isRequiereReceta());
            ps.setInt(6, medicamento.getIdCategoria());
            ps.setInt(7, medicamento.getIdMedicamento());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int idMedicamento) throws SQLException {
        String sql = "DELETE FROM Medicamento "
                + "WHERE idMedicamento = ?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idMedicamento);

            return ps.executeUpdate() > 0;
        }
    }
}
