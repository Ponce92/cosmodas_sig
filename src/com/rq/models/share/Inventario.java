package com.rq.models.share;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "gt_inventarios",schema = "public",catalog = "sig_db")
public class Inventario implements Serializable {

    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cl_estado")
    private Boolean estado;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_sucursal_id")
    private Sucursal sucursal;


    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "fk_producto_id")
    private Producto producto;

    @Column(name = "cn_existencia")
    private  Integer existencia;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Inventario)) {
            return false;
        }
        Inventario other = (Inventario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }

        return true;
    }
}
