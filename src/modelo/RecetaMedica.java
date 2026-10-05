/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ec745
 */
import java.util.Date;

public class RecetaMedica {

    private int idReceta;
    private int idCliente;
    private String medico;
    private Date fecha;
    private String medicamentosAutorizados;

    public RecetaMedica() {
    }

    public RecetaMedica(int idReceta, int idCliente, String medico, Date fecha, String medicamentosAutorizados) {
        this.idReceta = idReceta;
        this.idCliente = idCliente;
        this.medico = medico;
        this.fecha = fecha;
        this.medicamentosAutorizados = medicamentosAutorizados;
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMedicamentosAutorizados() {
        return medicamentosAutorizados;
    }

    public void setMedicamentosAutorizados(String medicamentosAutorizados) {
        this.medicamentosAutorizados = medicamentosAutorizados;
    }
}