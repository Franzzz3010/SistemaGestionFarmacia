package sistemagestionfarmacia.negocio;

import java.util.ArrayList;
import java.util.List;
import sistemagestionfarmacia.modelo.Compra;

public class CompraNegocio {

    private final List<Compra> compras;

    public CompraNegocio() {
        compras = new ArrayList<>();
    }

    public void agregar(Compra compra) {
        compras.add(compra);
    }

    public List<Compra> listar() {
        return compras;
    }

    public Compra buscar(int idCompra) {
        for (Compra compra : compras) {
            if (compra.getIdCompra() == idCompra) {
                return compra;
            }
        }
        return null;
    }

    public boolean modificar(Compra compraActualizada) {
        Compra compra = buscar(compraActualizada.getIdCompra());

        if (compra != null) {
            compra.setFecha(compraActualizada.getFecha());
            compra.setTotal(compraActualizada.getTotal());
            compra.setIdProveedor(compraActualizada.getIdProveedor());
            return true;
        }

        return false;
    }

    public boolean eliminar(int idCompra) {
        Compra compra = buscar(idCompra);

        if (compra != null) {
            compras.remove(compra);
            return true;
        }

        return false;
    }
}