
package presentacion;

import datos.VentaDAO;
import negocio.VentaNegocio;
import modelo.Venta;
import java.util.Date;

public class PruebaVenta {

    public static void main(String[] args) {

        Venta venta = new Venta();

        venta.setIdCliente(1);
        venta.setFecha(new Date());
        venta.setTotal(150.00);

        VentaNegocio negocio = new VentaNegocio();
        VentaDAO datos = new VentaDAO();

        if (negocio.validarVenta(venta)) {

            System.out.println("DATOS DE LA VENTA VALIDOS");

            if (datos.insertar(venta)) {
                System.out.println("VENTA REGISTRADA CORRECTAMENTE");
                System.out.println("Cliente ID: " + venta.getIdCliente());
                System.out.println("Total: Q" + venta.getTotal());
            } else {
                System.out.println("ERROR AL REGISTRAR VENTA");
            }

        } else {
            System.out.println("DATOS DE LA VENTA INVALIDOS");
        }
    }
}
