
package sistemagestionfarmacia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sistemagestionfarmacia.datos.RegistroCompraDatos;
import sistemagestionfarmacia.modelo.Compra;
import sistemagestionfarmacia.modelo.DetalleCompra;

public class PruebaRegistroCompra {

    public static void main(String[] args) {

        try {
            Compra compra = new Compra(
                    0,
                    new Date(),
                    0,
                    1
            );

            List<DetalleCompra> detalles = new ArrayList<>();

            DetalleCompra detalle = new DetalleCompra(
                    0,
                    0,
                    1,
                    20,
                    10.00
            );

            detalles.add(detalle);

            RegistroCompraDatos registro = new RegistroCompraDatos();

            int idCompra = registro.registrarCompra(
                    compra, detalles);

            System.out.println("COMPRA REGISTRADA CORRECTAMENTE");
            System.out.println("ID Compra: " + idCompra);
            System.out.println("Proveedor: 1");
            System.out.println("Medicamento: Paracetamol");
            System.out.println("Cantidad comprada: 20");
            System.out.println("Total: Q" + compra.getTotal());

        } catch (Exception e) {
            System.out.println("ERROR AL REGISTRAR COMPRA");
            e.printStackTrace();
        }
    }
}
