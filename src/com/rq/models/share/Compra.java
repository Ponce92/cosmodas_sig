package com.rq.models.share;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "gt_compras",schema = "public",catalog = "sig_db")
public class Compra implements Serializable {

    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ct_total")
    private Double total;

    @JoinColumn(name = "fk_proveedor_id",foreignKey = @ForeignKey(name = "COM_PROV_ID_FK"))
    private Proveedor proveedor;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
