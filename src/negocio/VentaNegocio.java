/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

/**
 *
 * @author ec745
 */
import modelo.Venta;

public class VentaNegocio {

    public boolean validarVenta(Venta venta) {

        if (venta == null) {
            return false;
        }

        if (venta.getIdCliente() <= 0) {
            return false;
        }

        if (venta.getFecha() == null) {
            return false;
        }

        if (venta.getTotal() < 0) {
            return false;
        }

        return true;
    }

    public double calcularTotal(double subtotal) {

        if (subtotal < 0) {
            return 0;
        }

        return subtotal;
    }
}