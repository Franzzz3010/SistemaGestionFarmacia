
package negocio;


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