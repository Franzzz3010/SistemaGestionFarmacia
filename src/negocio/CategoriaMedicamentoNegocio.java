package sistemagestionfarmacia.negocio;

import java.util.ArrayList;
import java.util.List;
import sistemagestionfarmacia.modelo.CategoriaMedicamento;

public class CategoriaMedicamentoNegocio {

    private final List<CategoriaMedicamento> categorias;

    public CategoriaMedicamentoNegocio() {
        categorias = new ArrayList<>();
    }

    public void agregar(CategoriaMedicamento categoria) {
        categorias.add(categoria);
    }

    public List<CategoriaMedicamento> listar() {
        return categorias;
    }

    public CategoriaMedicamento buscar(int idCategoria) {
        for (CategoriaMedicamento categoria : categorias) {
            if (categoria.getIdCategoria() == idCategoria) {
                return categoria;
            }
        }
        return null;
    }

    public boolean modificar(CategoriaMedicamento categoriaActualizada) {
        CategoriaMedicamento categoria = buscar(categoriaActualizada.getIdCategoria());

        if (categoria != null) {
            categoria.setNombre(categoriaActualizada.getNombre());
            categoria.setDescripcion(categoriaActualizada.getDescripcion());
            return true;
        }

        return false;
    }

    public boolean eliminar(int idCategoria) {
        CategoriaMedicamento categoria = buscar(idCategoria);

        if (categoria != null) {
            categorias.remove(categoria);
            return true;
        }

        return false;
    }
}