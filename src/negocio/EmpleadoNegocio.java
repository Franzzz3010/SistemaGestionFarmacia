/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

/**
 *
 * @author ec745
 */
import modelo.Empleado;

public class EmpleadoNegocio {

    public boolean validarEmpleado(Empleado empleado) {

        if (empleado == null) {
            return false;
        }

        if (empleado.getNombre() == null || empleado.getNombre().trim().isEmpty()) {
            return false;
        }

        if (empleado.getTelefono() == null || empleado.getTelefono().trim().isEmpty()) {
            return false;
        }

        if (empleado.getCorreo() == null || empleado.getCorreo().trim().isEmpty()) {
            return false;
        }

        if (empleado.getPuesto() == null || empleado.getPuesto().trim().isEmpty()) {
            return false;
        }

        return true;
    }
}