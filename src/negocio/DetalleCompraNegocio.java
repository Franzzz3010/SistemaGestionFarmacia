package sistemagestionfarmacia.negocio;

import java.util.ArrayList;
import java.util.List;
import sistemagestionfarmacia.modelo.DetalleCompra;

public class DetalleCompraNegocio {

    private final List<DetalleCompra> detallesCompra;

    public DetalleCompraNegocio() {
        detallesCompra = new ArrayList<>();
    }

    public void agregar(DetalleCompra detalle) {
        detallesCompra.add(detalle);
    }

    public List<DetalleCompra> listar() {
        return detallesCompra;
    }

    public DetalleCompra buscar(int idDetalleCompra) {
        for (DetalleCompra detalle : detallesCompra) {
            if (detalle.getIdDetalleCompra() == idDetalleCompra) {
                return detalle;
            }
        }
        return null;
    }

    public boolean modificar(DetalleCompra detalleActualizado) {
        DetalleCompra detalle = buscar(detalleActualizado.getIdDetalleCompra());

        if (detalle != null) {
            detalle.setIdCompra(detalleActualizado.getIdCompra());
            detalle.setIdMedicamento(detalleActualizado.getIdMedicamento());
            detalle.setCantidad(detalleActualizado.getCantidad());
            detalle.setCosto(detalleActualizado.getCosto());
            return true;
        }

        return false;
    }

    public boolean eliminar(int idDetalleCompra) {
        DetalleCompra detalle = buscar(idDetalleCompra);

        if (detalle != null) {
            detallesCompra.remove(detalle);
            return true;
        }

        return false;
    }
}