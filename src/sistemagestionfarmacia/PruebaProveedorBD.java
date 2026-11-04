
package sistemagestionfarmacia;

import java.sql.SQLException;
import java.util.List;
import sistemagestionfarmacia.datos.ProveedorDatos;
import sistemagestionfarmacia.modelo.Proveedor;

public class PruebaProveedorBD {

    public static void main(String[] args) {

        ProveedorDatos datos = new ProveedorDatos();

        try {
            Proveedor proveedor = new Proveedor(
                    0,
                    "Distribuidora Farmaceutica Central",
                    "55551234",
                    "ventas@farmaceuticacentral.com",
                    "Cobán, Alta Verapaz"
            );

            boolean guardado = datos.agregar(proveedor);

            if (guardado) {
                System.out.println("PROVEEDOR GUARDADO CORRECTAMENTE");
            } else {
                System.out.println("NO SE PUDO GUARDAR EL PROVEEDOR");
            }

            System.out.println("\nPROVEEDORES REGISTRADOS:");

            List<Proveedor> lista = datos.listar();

            for (Proveedor p : lista) {
                System.out.println(
                        "ID: " + p.getIdProveedor()
                        + " | Nombre: " + p.getNombre()
                        + " | Telefono: " + p.getTelefono()
                        + " | Correo: " + p.getCorreo()
                        + " | Direccion: " + p.getDireccion()
                );
            }

        } catch (SQLException e) {
            System.out.println("ERROR EN BASE DE DATOS:");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
