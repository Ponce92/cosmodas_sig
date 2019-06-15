package com.rq.controllers;

import com.rq.models.security.Rol;
import com.rq.models.security.Usuario;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.List;
import java.util.Random;

import com.rq.security.HibernateUtil;
import com.sun.org.apache.xml.internal.security.algorithms.MessageDigestAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.primefaces.PrimeFaces;

@Named
@ConversationScoped
public class UserCtrl implements Serializable {

    private Session session;
    private Usuario usuario;
    private Usuario usuarioSeleccionado;
    private List<Usuario> userList;
    private List<Rol> rolesList;
    private String password;
    private String psw1;
    private String psw2;

    /*------------------------------------------------------------------------
     * Metodos  de 2do orden de la clase
     *------------------------------------------------------------------------
     */
    @PostConstruct
    public void init(){
        session= HibernateUtil.getSessionFactory().openSession();

        userList=session.createQuery("FROM Usuario r").getResultList();
        rolesList=session.createQuery("FROM Rol r").getResultList();

        this.usuarioSeleccionado=new Usuario();

        setUsuario(new Usuario());
        this.getUsuario().setEstado("INA");

    }

    public void guardarUsuario(){
        if(!this.password.equals(this.usuario.getPassword())){
            addMessage(FacesMessage.SEVERITY_WARN, "Error","Las contrasenias no coniciden ");
            return;
        }else{

            try{
               //Encriptamos la contrasenia . . .
                String pass= DigestUtils.sha1Hex(this.password);

                this.usuario.setPassword(pass);

                //set codigo
//                byte[] array = new byte[12];
//                new Random().nextBytes(array);
//
//                this.usuario.setCodigo(new String(array, Charset.forName("UTF-8")));

                session.beginTransaction();
                session.save(this.getUsuario());
                session.getTransaction().commit();

                this.userList.add(usuario);
                this.usuario=new Usuario();
            }catch (Exception e){
                addMessage(FacesMessage.SEVERITY_ERROR,"Error","Error en la insersion del Usuario");
                e.printStackTrace();
            }
            PrimeFaces.current().executeScript("PF('crtDlg').hide();");
        }
    }

    public void actualizar(){
        Usuario us;
        try{
            us=(Usuario) session.get(Usuario.class,this.usuarioSeleccionado.getId());
            us.setEstado(this.usuarioSeleccionado.getEstado());
            us.setUsername(this.usuarioSeleccionado.getUsername());
            us.setRol(this.usuarioSeleccionado.getRol());

            if(this.psw1 != null){
                if(!this.psw1.equals(this.psw2)){
                    addMessage(FacesMessage.SEVERITY_ERROR,"Error","Los password no concuerdan");
                    return;
                }else{
                    String pass= DigestUtils.sha1Hex(this.psw1);
                    us.setPassword(pass);
                }
            }

            session.beginTransaction();
            session.update(us);
            session.getTransaction().commit();
            addMessage(FacesMessage.SEVERITY_INFO,"Exito","El usuario a sido actualizado correctamente.");

        }catch (Exception e){
            addMessage(FacesMessage.SEVERITY_ERROR,"Error de transaccion","La acualizacion del usuario a fallado");
            e.printStackTrace();
        }
        PrimeFaces.current().executeScript("PF('edit_dialgo').hide();");
    }

    public void eliminar(){
        try{
            session.beginTransaction();
            session.createQuery("DELETE FROM Usuario us WHERE us.id= :id ")
                    .setParameter("id",this.usuarioSeleccionado.getId())
                    .executeUpdate();
            session.getTransaction().commit();

            this.userList.remove(this.getUsuarioSeleccionado());
            addMessage(FacesMessage.SEVERITY_INFO,"Transaccion completa","El usuario ha sido eliminado correctamente");


        }catch (Exception e){
            addMessage(FacesMessage.SEVERITY_ERROR,"Error de transaccion","El usuario no se ha podido eliminar del sistema");
            e.printStackTrace();

        }

        PrimeFaces.current().executeScript("PF('edit_dialgo').hide();");
    }

    /*------------------------------------------------------------------------
     * Metodos  de 2do orden de la clase
     *------------------------------------------------------------------------
     */

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUserList() {
        return userList;
    }

    public void setUserList(List<Usuario> userList) {
        this.userList = userList;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public List<Rol> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Rol> rolesList) {
        this.rolesList = rolesList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPsw1() {
        return psw1;
    }

    public void setPsw1(String psw1) {
        this.psw1 = psw1;
    }

    public String getPsw2() {
        return psw2;
    }

    public void setPsw2(String psw2) {
        this.psw2 = psw2;
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext context= FacesContext.getCurrentInstance();
        context.addMessage(null,new FacesMessage(severity,summary,detail));

    }


}
