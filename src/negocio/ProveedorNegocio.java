package sistemagestionfarmacia.negocio;

import java.util.ArrayList;
import java.util.List;
import sistemagestionfarmacia.modelo.Proveedor;

public class ProveedorNegocio {

    private final List<Proveedor> proveedores;

    public ProveedorNegocio() {
        proveedores = new ArrayList<>();
    }

    public void agregar(Proveedor proveedor) {
        proveedores.add(proveedor);
    }

    public List<Proveedor> listar() {
        return proveedores;
    }

    public Proveedor buscar(int idProveedor) {
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getIdProveedor() == idProveedor) {
                return proveedor;
            }
        }
        return null;
    }

    public boolean modificar(Proveedor proveedorActualizado) {
        Proveedor proveedor = buscar(proveedorActualizado.getIdProveedor());

        if (proveedor != null) {
            proveedor.setNombre(proveedorActualizado.getNombre());
            proveedor.setTelefono(proveedorActualizado.getTelefono());
            proveedor.setCorreo(proveedorActualizado.getCorreo());
            proveedor.setDireccion(proveedorActualizado.getDireccion());
            return true;
        }

        return false;
    }

    public boolean eliminar(int idProveedor) {
        Proveedor proveedor = buscar(idProveedor);

        if (proveedor != null) {
            proveedores.remove(proveedor);
            return true;
        }

        return false;
    }
}