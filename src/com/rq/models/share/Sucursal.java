package com.rq.models.share;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "gt_sucursales",schema = "public",catalog = "sig_db")
public class Sucursal implements Serializable {
    @Id
    @Column(name = "pk_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ct_codigo", nullable = false, unique = true)
    private String codigo;

    @Column(name = "ct_nombre",nullable = false,unique = true)
    private String nombre;

    @Column(name = "cd_descripcion")
    private String descripcion;

    @Column(name = "cl_estado" )
    private Boolean estado;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Sucursal)) {
            return false;
        }
        Sucursal other = (Sucursal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }

        return true;
    }
}
