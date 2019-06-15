package com.rq.models.security;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table( name = "gt_usuarios",
        schema = "public",
        catalog = "sig_db"
)
public class Usuario implements Serializable {



    @Id
    @Column(name = "pk_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ct_username", nullable = false, length = 100,unique = true)
    private String username;

    @Column(name = "ct_password",nullable = false, length = 255)
    private String password;

    @Column(name = "ct_codigo")
    private String codigo;

    @Column(name = "ct_estado", nullable = false,length = 3)
    private String estado;//INA->inactivo,ACT->activo

    @ManyToOne
    @JoinColumn(name="fk_rol_id",nullable = false,foreignKey = @ForeignKey(name = "ROL_ID_FK"))
    private Rol rol;

    /*------------------------------------------------------------------------
     * Constructor
     *------------------------------------------------------------------------
     */
    public Usuario(){ }
    public Usuario(Long id,String username,String estado, Rol rol){
        this.id=id;
        this.username=username;
        this.estado=estado;
        this.rol=rol;
    }
    public Usuario(Long id,String username,String estado){
        this.id=id;
        this.username=username;
        this.estado=estado;
    }


    /*------------------------------------------------------------------------
     * Metodos de la clase
     *------------------------------------------------------------------------
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
