
package presentacion;

import datos.ClienteDAO;
import negocio.ClienteNegocio;
import modelo.Cliente;

public class PruebaCliente {

    public static void main(String[] args) {

        Cliente cliente = new Cliente();

        cliente.setNombre("Cliente Prueba");
        cliente.setTelefono("55551234");
        cliente.setCorreo("prueba@ejemplo.com");
        cliente.setDireccion("Guatemala");

        ClienteNegocio negocio = new ClienteNegocio();
        ClienteDAO datos = new ClienteDAO();

        if (negocio.validarCliente(cliente)) {

            System.out.println("DATOS DEL CLIENTE VALIDOS");

            if (datos.insertar(cliente)) {
                System.out.println("CLIENTE REGISTRADO CORRECTAMENTE");
            } else {
                System.out.println("ERROR AL REGISTRAR CLIENTE");
            }

        } else {
            System.out.println("DATOS DEL CLIENTE INVALIDOS");
        }
    }
}
