/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

/**
 *
 * @author ec745
 */
import modelo.Cliente;

public class ClienteNegocio {

    public boolean validarCliente(Cliente cliente) {

        if (cliente == null) {
            return false;
        }

        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            return false;
        }

        if (cliente.getTelefono() == null || cliente.getTelefono().trim().isEmpty()) {
            return false;
        }

        if (cliente.getCorreo() == null || cliente.getCorreo().trim().isEmpty()) {
            return false;
        }

        if (cliente.getDireccion() == null || cliente.getDireccion().trim().isEmpty()) {
            return false;
        }

        return true;
    }
}