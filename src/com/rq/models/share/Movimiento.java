package com.rq.models.share;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "gt_movimientos",schema = "public",catalog = "sig_db")
public class Movimiento implements Serializable {

    @Id
    @Column(name = "pk_id")
    private Long id;

    @JoinColumn(name = "fk_factura_id",foreignKey = @ForeignKey(name = "MOV_FAC_ID_FK"))
    private Factura factura;

    @JoinColumn(name = "fk_proveedor_id",foreignKey = @ForeignKey(name = "MOV_PROV_ID_FK"))
    private Proveedor proveedor;

    @JoinColumn(name = "fk_sucursal_id",foreignKey = @ForeignKey(name = "MOV_SUC_ID_FK"))
    private Sucursal sucursal;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
}
