package com.rq.models.security;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
    @Table(name = "gt_roles", schema = "public",catalog = "sig_db")
public class Rol implements Serializable {

    @Id
    @Column(name = "pk_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ct_nombre",nullable = false,unique = true)
    private String nombre;

    @Column(name="cd_descripcion",nullable = false)
    private String descripcion;

    @Column(name = "cl_estado", nullable = false)
    private Boolean estado;

    @ManyToMany(
            cascade = {CascadeType.PERSIST,CascadeType.MERGE}
            )
    private List<Menu> menus;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private  List<SubMenu> subMenus;

    /*------------------------------------------------------------------------
     *  Metodos  de la clase
     *------------------------------------------------------------------------
     */

    public Rol() {
        this.estado=false;
        this.menus=new ArrayList<Menu>();
        this.subMenus=new ArrayList<SubMenu>();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<SubMenu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<SubMenu> subMenus) {
        this.subMenus = subMenus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
