
package negocio;


import modelo.DetalleVenta;

public class DetalleVentaNegocio {

    public boolean validarDetalleVenta(DetalleVenta detalleVenta) {

        if (detalleVenta == null) {
            return false;
        }

        if (detalleVenta.getIdVenta() <= 0) {
            return false;
        }

        if (detalleVenta.getIdMedicamento() <= 0) {
            return false;
        }

        if (detalleVenta.getCantidad() <= 0) {
            return false;
        }

        if (detalleVenta.getPrecio() < 0) {
            return false;
        }

        return true;
    }

    public double calcularSubtotal(DetalleVenta detalleVenta) {

        if (detalleVenta == null) {
            return 0;
        }

        return detalleVenta.getCantidad() * detalleVenta.getPrecio();
    }
}