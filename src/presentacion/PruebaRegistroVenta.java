
package presentacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Venta;
import modelo.DetalleVenta;
import sistemagestionfarmacia.datos.RegistroVentaDatos;

public class PruebaRegistroVenta {

    public static void main(String[] args) {

        try {

            Venta venta = new Venta();
            venta.setIdCliente(1);
            venta.setFecha(new Date());

            DetalleVenta detalle = new DetalleVenta();
            detalle.setIdMedicamento(1);
            detalle.setCantidad(3);
            detalle.setPrecio(15.50);

            List<DetalleVenta> detalles = new ArrayList<>();
            detalles.add(detalle);

            RegistroVentaDatos registro = new RegistroVentaDatos();

            int idVenta = registro.registrarVenta(venta, detalles);

            System.out.println("VENTA REGISTRADA CORRECTAMENTE");
            System.out.println("ID Venta: " + idVenta);
            System.out.println("ID Cliente: " + venta.getIdCliente());
            System.out.println("Medicamento: Paracetamol");
            System.out.println("Cantidad vendida: 3");
            System.out.println("Total: Q" + venta.getTotal());

        } catch (Exception e) {

            System.out.println("ERROR AL REGISTRAR VENTA");
            System.out.println(e.getMessage());
        }
    }
}
