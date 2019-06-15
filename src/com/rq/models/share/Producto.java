package com.rq.models.share;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "gt_productos",schema = "public",catalog = "sig_db")
public class Producto implements Serializable {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "fk_tipo_producto_id")
    private TipoProducto tipoProducto;

    @Column(name = "ct_codigo",nullable = false)
    private String codigo;

    @Column(name = "ct_ombre",nullable = false )
    private String nombre;

    @Column(name = "cn_precio_compra")
    private float  precionCompra;

    @Column(name = "cn_precio_venta")
    private float precioVenta;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecionCompra() {
        return precionCompra;
    }

    public void setPrecionCompra(float precionCompra) {
        this.precionCompra = precionCompra;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }

        return true;
    }
}
