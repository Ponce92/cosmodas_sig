package com.rq.models.share;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "gt_facturas",schema = "public",catalog = "sig_db")
public class Factura implements Serializable {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cf_emision")
    private Date fecha;

    @Column(name = "cn_idenficacion",unique = true)
    private Long numeroFactura;

    @Column(name = "cn_total")
    private Double total;

    @Column(name = "cn_sub_total")
    private Double subTotal;

    @JoinColumn(name = "fk_cliente_id",foreignKey = @ForeignKey(name = "CLIENTE_ID_FK"))
    private Cliente cliente;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<Producto> productos;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
