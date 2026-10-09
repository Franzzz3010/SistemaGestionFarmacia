
package sistemagestionfarmacia.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemagestionfarmacia.modelo.CategoriaMedicamento;

public class CategoriaMedicamentoDatos {

    public boolean agregar(CategoriaMedicamento categoria) throws SQLException {
        String sql = "INSERT INTO CategoriaMedicamento (nombre, descripcion) VALUES (?, ?)";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());

            return ps.executeUpdate() > 0;
        }
    }

    public List<CategoriaMedicamento> listar() throws SQLException {
        List<CategoriaMedicamento> lista = new ArrayList<>();
        String sql = "SELECT idCategoria, nombre, descripcion FROM CategoriaMedicamento";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CategoriaMedicamento categoria = new CategoriaMedicamento(
                        rs.getInt("idCategoria"),
                        rs.getString("nombre"),
                        rs.getString("descripcion")
                );

                lista.add(categoria);
            }
        }

        return lista;
    }

    public CategoriaMedicamento buscar(int idCategoria) throws SQLException {
        String sql = "SELECT idCategoria, nombre, descripcion FROM CategoriaMedicamento WHERE idCategoria = ?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idCategoria);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new CategoriaMedicamento(
                            rs.getInt("idCategoria"),
                            rs.getString("nombre"),
                            rs.getString("descripcion")
                    );
                }
            }
        }

        return null;
    }

    public boolean modificar(CategoriaMedicamento categoria) throws SQLException {
        String sql = "UPDATE CategoriaMedicamento SET nombre = ?, descripcion = ? WHERE idCategoria = ?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());
            ps.setInt(3, categoria.getIdCategoria());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int idCategoria) throws SQLException {
        String sql = "DELETE FROM CategoriaMedicamento WHERE idCategoria = ?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idCategoria);

            return ps.executeUpdate() > 0;
        }
    }
}
