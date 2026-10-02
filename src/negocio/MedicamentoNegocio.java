package sistemagestionfarmacia.negocio;

import java.util.ArrayList;
import java.util.List;
import sistemagestionfarmacia.modelo.Medicamento;

public class MedicamentoNegocio {

    private final List<Medicamento> medicamentos;

    public MedicamentoNegocio() {
        medicamentos = new ArrayList<>();
    }

    public void agregar(Medicamento medicamento) {
        medicamentos.add(medicamento);
    }

    public List<Medicamento> listar() {
        return medicamentos;
    }

    public Medicamento buscar(int idMedicamento) {
        for (Medicamento medicamento : medicamentos) {
            if (medicamento.getIdMedicamento() == idMedicamento) {
                return medicamento;
            }
        }
        return null;
    }

    public boolean modificar(Medicamento medicamentoActualizado) {
        Medicamento medicamento = buscar(medicamentoActualizado.getIdMedicamento());

        if (medicamento != null) {
            medicamento.setNombre(medicamentoActualizado.getNombre());
            medicamento.setPresentacion(medicamentoActualizado.getPresentacion());
            medicamento.setPrecio(medicamentoActualizado.getPrecio());
            medicamento.setExistencia(medicamentoActualizado.getExistencia());
            medicamento.setRequiereReceta(medicamentoActualizado.isRequiereReceta());
            medicamento.setIdCategoria(medicamentoActualizado.getIdCategoria());
            return true;
        }

        return false;
    }

    public boolean eliminar(int idMedicamento) {
        Medicamento medicamento = buscar(idMedicamento);

        if (medicamento != null) {
            medicamentos.remove(medicamento);
            return true;
        }

        return false;
    }
}