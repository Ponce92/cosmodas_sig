package com.rq.models.security;



import javax.persistence.*;

@Entity
@Table(name = "gt_menus",schema = "public",catalog = "sig_db")
public class Menu {

    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ct_nombre",nullable = false)
    private String nombre;


    @Column(name="ct_icon", nullable = false)
    private String icono;

    @Column(name = "cd_descripcion",nullable = false)
    private String descripcion;

    @Column(name = "cl_estado",nullable = false)
    private boolean is_activo;

    /*-------------------------------------------------------------------------------------------
     *  Metodos de las clases .
     *-------------------------------------------------------------------------------------------
     */
    public void Menu(){
        this.setIs_activo(false);
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

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isIs_activo() {
        return is_activo;
    }

    public void setIs_activo(boolean is_activo) {
        this.is_activo = is_activo;
    }

}
