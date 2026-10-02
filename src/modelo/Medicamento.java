package sistemagestionfarmacia.modelo;

public class Medicamento {

    private int idMedicamento;
    private String nombre;
    private String presentacion;
    private double precio;
    private int existencia;
    private boolean requiereReceta;
    private int idCategoria;

    public Medicamento() {
    }

    public Medicamento(int idMedicamento, String nombre, String presentacion,
            double precio, int existencia, boolean requiereReceta, int idCategoria) {
        this.idMedicamento = idMedicamento;
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.precio = precio;
        this.existencia = existencia;
        this.requiereReceta = requiereReceta;
        this.idCategoria = idCategoria;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public boolean isRequiereReceta() {
        return requiereReceta;
    }

    public void setRequiereReceta(boolean requiereReceta) {
        this.requiereReceta = requiereReceta;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
        return nombre;
    }
}