package com.rq.models.security;

import com.rq.models.security.Menu;

import javax.persistence.*;

@Entity
@Table(name = "gt_sub_menus",schema = "public",catalog = "sig_db")
public class SubMenu {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ct_nombre",nullable = false)
    private String nombre;

    @Column(name = "ct_url",nullable = false)
    private String url;

    @Column(name = "cd_descripcion")
    private String descripcion;

    @Column(name = "ct_icon",nullable = false)
    private String icono;

    @Column(name = "cl_estado",nullable = false)
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "fk_menu_id", nullable = false, foreignKey = @ForeignKey(name = "MENU_ID_FK"))
    private Menu menuPadre;


    /*-------------------------------------------------------------------------------------------
     *  Metodos de las clases .
     *-------------------------------------------------------------------------------------------
     */

    public SubMenu() {
        this.menuPadre=new Menu();
        this.estado=false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Menu getMenuPadre() {
        return menuPadre;
    }

    public void setMenuPadre(Menu menuPadre) {
        this.menuPadre = menuPadre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
