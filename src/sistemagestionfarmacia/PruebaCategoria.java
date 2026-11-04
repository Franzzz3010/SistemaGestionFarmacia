
package sistemagestionfarmacia;

import java.sql.SQLException;
import java.util.List;
import sistemagestionfarmacia.datos.CategoriaMedicamentoDatos;
import sistemagestionfarmacia.modelo.CategoriaMedicamento;

public class PruebaCategoria {

    public static void main(String[] args) {

        CategoriaMedicamentoDatos datos =
                new CategoriaMedicamentoDatos();

        try {
            CategoriaMedicamento categoria =
                    new CategoriaMedicamento(
                            0,
                            "Analgésicos",
                            "Medicamentos para aliviar el dolor"
                    );

            boolean guardado = datos.agregar(categoria);

            if (guardado) {
                System.out.println("CATEGORIA GUARDADA CORRECTAMENTE");
            } else {
                System.out.println("NO SE PUDO GUARDAR LA CATEGORIA");
            }

            System.out.println("\nCATEGORIAS REGISTRADAS:");

            List<CategoriaMedicamento> lista = datos.listar();

            for (CategoriaMedicamento c : lista) {
                System.out.println(
                        "ID: " + c.getIdCategoria()
                        + " | Nombre: " + c.getNombre()
                        + " | Descripcion: " + c.getDescripcion()
                );
            }

        } catch (SQLException e) {
            System.out.println("ERROR EN BASE DE DATOS:");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
