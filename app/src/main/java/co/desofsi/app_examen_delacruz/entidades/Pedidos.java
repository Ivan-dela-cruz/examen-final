package co.desofsi.app_examen_delacruz.entidades;

import java.io.Serializable;

public class Pedidos implements Serializable {
    private int id;
    private String codigo;
    private String detalle;
    private double total;
    private int tipo;

    public Pedidos(String codigo, String detalle, double total, int tipo) {
        this.codigo = codigo;
        this.detalle = detalle;
        this.total = total;
        this.tipo = tipo;
    }

    public Pedidos(int id, String codigo, String detalle, double total, int tipo) {
        this.id = id;
        this.codigo = codigo;
        this.detalle = detalle;
        this.total = total;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
