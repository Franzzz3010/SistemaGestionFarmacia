/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemagestionfarmacia;

/**
 *
 * @author fabian
 */

import java.util.Date;
import modelo.Cliente;
import modelo.Venta;
import modelo.DetalleVenta;
import modelo.RecetaMedica;
import modelo.Empleado;

public class SistemaGestionFarmacia {

    public static void main(String[] args) {

    
        // PRUEBA CLIENTE
     

        Cliente cliente = new Cliente();

        cliente.setIdCliente(1);
        cliente.setNombre("Juan Perez");
        cliente.setTelefono("5555-5555");
        cliente.setCorreo("juan@gmail.com");
        cliente.setDireccion("Guatemala");

        System.out.println("\n CLIENTE ");
        System.out.println("ID: " + cliente.getIdCliente());
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Telefono: " + cliente.getTelefono());
        System.out.println("Correo: " + cliente.getCorreo());
        System.out.println("Direccion: " + cliente.getDireccion());


     
        // PRUEBA VENTA
       

        Venta venta = new Venta();

        venta.setIdVenta(1);
        venta.setIdCliente(1);
        venta.setFecha(new Date());
        venta.setTotal(150.50);

        System.out.println("\n VENTA ");
        System.out.println("ID Venta: " + venta.getIdVenta());
        System.out.println("ID Cliente: " + venta.getIdCliente());
        System.out.println("Fecha: " + venta.getFecha());
        System.out.println("Total: Q" + venta.getTotal());


   
        // PRUEBA DETALLE VENTA


        DetalleVenta detalle = new DetalleVenta();

        detalle.setIdDetalleVenta(1);
        detalle.setIdVenta(1);
        detalle.setIdMedicamento(5);
        detalle.setCantidad(2);
        detalle.setPrecio(25.00);

        System.out.println("\n DETALLE DE VENTA ");
        System.out.println("ID Detalle: " + detalle.getIdDetalleVenta());
        System.out.println("ID Venta: " + detalle.getIdVenta());
        System.out.println("ID Medicamento: " + detalle.getIdMedicamento());
        System.out.println("Cantidad: " + detalle.getCantidad());
        System.out.println("Precio: Q" + detalle.getPrecio());



        // PRUEBA RECETA MEDICA
 

        RecetaMedica receta = new RecetaMedica();

        receta.setIdReceta(1);
        receta.setIdCliente(1);
        receta.setMedico("Dr. Carlos Lopez");
        receta.setFecha(new Date());
        receta.setMedicamentosAutorizados("Paracetamol");

        System.out.println("\n RECETA MEDICA ");
        System.out.println("ID Receta: " + receta.getIdReceta());
        System.out.println("ID Cliente: " + receta.getIdCliente());
        System.out.println("Medico: " + receta.getMedico());
        System.out.println("Fecha: " + receta.getFecha());
        System.out.println("Medicamentos: "
                + receta.getMedicamentosAutorizados());



        // PRUEBA EMPLEADO

        Empleado empleado = new Empleado();

        empleado.setIdEmpleado(1);
        empleado.setNombre("Carlos Lopez");
        empleado.setTelefono("4444-4444");
        empleado.setCorreo("carlos@gmail.com");
        empleado.setPuesto("Vendedor");

        System.out.println("\n EMPLEADO ");
        System.out.println("ID: " + empleado.getIdEmpleado());
        System.out.println("Nombre: " + empleado.getNombre());
        System.out.println("Telefono: " + empleado.getTelefono());
        System.out.println("Correo: " + empleado.getCorreo());
        System.out.println("Puesto: " + empleado.getPuesto());


        // FIN DE LA PRUEBA


       
        System.out.println("TODAS LAS CLASES FUNCIONAN");
      
    }
}