/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ec745
 */
public class DetalleVenta {

    private int idDetalleVenta;
    private int idVenta;
    private int idMedicamento;
    private int cantidad;
    private double precio;

    public DetalleVenta() {
    }

    public DetalleVenta(int idDetalleVenta, int idVenta, int idMedicamento, int cantidad, double precio) {
        this.idDetalleVenta = idDetalleVenta;
        this.idVenta = idVenta;
        this.idMedicamento = idMedicamento;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}