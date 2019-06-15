package com.rq.controllers;

import com.rq.models.security.Rol;
import com.rq.security.HibernateUtil;
import org.hibernate.Session;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named
@ConversationScoped
public class RolCtrl implements Serializable {
    private Session session;

    private Rol rol;
    private Rol rolSeleccionado;
    private List<Rol> rolsList;


    /*------------------------------------------------------------------------
     * Metodos  de la clase
     *------------------------------------------------------------------------
     */
    @PostConstruct
    public void init(){
        session= HibernateUtil.getSessionFactory().openSession();

        rolsList=session.createQuery("FROM Rol r").getResultList();

        this.rolSeleccionado=new Rol();
        setRolSeleccionado(new Rol());

    }

    public void guardarRol(){
        try{
            session.beginTransaction();
            session.save(this.getRol());
            session.getTransaction().commit();

            this.rolsList.add(this.getRol());
            addMessage(FacesMessage.SEVERITY_INFO,"Mensaje","Rol almacenado correctamente");
        }catch (Exception e){
            addMessage(FacesMessage.SEVERITY_ERROR,"Error","Error en la insersion del rol");
        }
        PrimeFaces.current().executeScript("PF('dlg01').hide();");
    }

    public void editar(){
        try{
            Rol rl=session.get(Rol.class,this.rolSeleccionado.getId());

            rl.setNombre(this.rolSeleccionado.getNombre());
            rl.setDescripcion(this.rolSeleccionado.getDescripcion());
            rl.setEstado(this.rolSeleccionado.getEstado());

            session.beginTransaction();
            session.update(rl);
            session.getTransaction().commit();

            addMessage(FacesMessage.SEVERITY_INFO,"Exito","El rol se ha actualizado correctamente");
        }catch (Exception e){
            addMessage(FacesMessage.SEVERITY_ERROR,"Error","El servidor ha fallado al completar la trasaccion.");
            e.printStackTrace();
        }
    }

    public void eliminar(){
        try{
            Rol rl=session.get(Rol.class,this.rolSeleccionado.getId());



        }catch (Exception e ){
            addMessage(FacesMessage.SEVERITY_ERROR,"Error","El servidor ha fallado al completar la trasaccion.");
            e.printStackTrace();

        }

    }


    /*------------------------------------------------------------------------
     * Metodos  de 2do orden de la clase
     *------------------------------------------------------------------------
     */
    public void resetRol(){
        this.getRol().setNombre(null);
        this.getRol().setDescripcion(null);
        return;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Rol> getRolsList() {
        return rolsList;
    }

    public void setRolsList(List<Rol> rolsList) {
        this.rolsList = rolsList;
    }

    public Rol getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(Rol rolSeleccionado) {
        System.out.println("Rol Seleccionado set : "+rolSeleccionado.getNombre());
        this.rolSeleccionado = rolSeleccionado;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext context= FacesContext.getCurrentInstance();
        context.addMessage(null,new FacesMessage(severity,summary,detail));

    }

}
