
package sistemagestionfarmacia.modelo;

import java.util.Date;

public class Compra {

    private int idCompra;
    private Date fecha;
    private double total;
    private int idProveedor;

    public Compra() {
    }

    public Compra(int idCompra, Date fecha, double total, int idProveedor) {
        this.idCompra = idCompra;
        this.fecha = fecha;
        this.total = total;
        this.idProveedor = idProveedor;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "idCompra=" + idCompra +
                ", fecha=" + fecha +
                ", total=" + total +
                ", idProveedor=" + idProveedor +
                '}';
    }
}
