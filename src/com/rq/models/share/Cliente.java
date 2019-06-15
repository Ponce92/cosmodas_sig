package com.rq.models.share;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "gt_clientes",schema = "public",catalog = "sig_db")
public class Cliente implements Serializable {

    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ct_codigo",unique = true)
    private String codigo;

    @Column(name = "ct_nombre")
    private String nombre;


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
}
