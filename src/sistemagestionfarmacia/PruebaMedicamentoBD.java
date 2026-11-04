
package sistemagestionfarmacia;

import java.sql.SQLException;
import java.util.List;
import sistemagestionfarmacia.datos.MedicamentoDatos;
import sistemagestionfarmacia.modelo.Medicamento;

public class PruebaMedicamentoBD {

    public static void main(String[] args) {

        MedicamentoDatos datos = new MedicamentoDatos();

        try {
            Medicamento medicamento = new Medicamento(
                    0,
                    "Paracetamol",
                    "Tabletas 500 mg",
                    15.50,
                    100,
                    false,
                    1
            );

            boolean guardado = datos.agregar(medicamento);

            if (guardado) {
                System.out.println("MEDICAMENTO GUARDADO CORRECTAMENTE");
            } else {
                System.out.println("NO SE PUDO GUARDAR EL MEDICAMENTO");
            }

            System.out.println("\nMEDICAMENTOS REGISTRADOS:");

            List<Medicamento> lista = datos.listar();

            for (Medicamento m : lista) {
                System.out.println(
                        "ID: " + m.getIdMedicamento()
                        + " | Nombre: " + m.getNombre()
                        + " | Presentacion: " + m.getPresentacion()
                        + " | Precio: Q" + m.getPrecio()
                        + " | Existencia: " + m.getExistencia()
                        + " | Requiere receta: " + m.isRequiereReceta()
                        + " | Categoria: " + m.getIdCategoria()
                );
            }

        } catch (SQLException e) {
            System.out.println("ERROR EN BASE DE DATOS:");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
