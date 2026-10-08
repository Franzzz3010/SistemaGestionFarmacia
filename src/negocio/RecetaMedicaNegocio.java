/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

/**
 *
 * @author ec745
 */
import modelo.RecetaMedica;

public class RecetaMedicaNegocio {

    public boolean validarReceta(RecetaMedica receta) {

        if (receta == null) {
            return false;
        }

        if (receta.getIdCliente() <= 0) {
            return false;
        }

        if (receta.getMedico() == null || receta.getMedico().trim().isEmpty()) {
            return false;
        }

        if (receta.getFecha() == null) {
            return false;
        }

        if (receta.getMedicamentosAutorizados() == null
                || receta.getMedicamentosAutorizados().trim().isEmpty()) {
            return false;
        }

        return true;
    }
}