package sistemagestionfarmacia;

import sistemagestionfarmacia.modelo.Medicamento;
import sistemagestionfarmacia.negocio.MedicamentoNegocio;

public class PruebaMedicamento {

    public static void main(String[] args) {

        MedicamentoNegocio negocio = new MedicamentoNegocio();

        Medicamento medicamento1 = new Medicamento(
                1,
                "Paracetamol",
                "Tabletas 500mg",
                25.50,
                100,
                false,
                1
        );

        negocio.agregar(medicamento1);

        System.out.println("=== MEDICAMENTOS ===");

        for (Medicamento medicamento : negocio.listar()) {
            System.out.println(medicamento.getNombre());
        }

        System.out.println("\n=== BUSCAR ===");

        Medicamento encontrado = negocio.buscar(1);

        if (encontrado != null) {
            System.out.println("Encontrado: " + encontrado.getNombre());
        }

        System.out.println("\n=== MODIFICAR ===");

        Medicamento actualizado = new Medicamento(
                1,
                "Paracetamol",
                "Tabletas 500mg",
                30.00,
                150,
                false,
                1
        );

        if (negocio.modificar(actualizado)) {
            System.out.println("Medicamento modificado correctamente.");
        }

        System.out.println("Nuevo precio: "
                + negocio.buscar(1).getPrecio());

        System.out.println("\n=== ELIMINAR ===");

        if (negocio.eliminar(1)) {
            System.out.println("Medicamento eliminado correctamente.");
        }

        System.out.println("\nCantidad de medicamentos: "
                + negocio.listar().size());
    }
}