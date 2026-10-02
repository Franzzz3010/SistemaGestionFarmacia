package sistemagestionfarmacia.modelo;

public class DetalleCompra {

    private int idDetalleCompra;
    private int idCompra;
    private int idMedicamento;
    private int cantidad;
    private double costo;

    public DetalleCompra() {
    }

    public DetalleCompra(int idDetalleCompra, int idCompra, int idMedicamento,
            int cantidad, double costo) {
        this.idDetalleCompra = idDetalleCompra;
        this.idCompra = idCompra;
        this.idMedicamento = idMedicamento;
        this.cantidad = cantidad;
        this.costo = costo;
    }

    public int getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(int idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Detalle de compra #" + idDetalleCompra;
    }
}