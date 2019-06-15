package com.rq.controllers;

import com.rq.models.security.Menu;
import com.rq.models.security.SubMenu;
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
public class SubMenuCtrl implements Serializable {
    private Session session;
    private List<SubMenu> subMenuList;
    private SubMenu subMenu;
    private SubMenu subMenuSeleccionado;
    private List<Menu> menuList;

    @PostConstruct
    public void init(){
        session= HibernateUtil.getSessionFactory().openSession();


        this.subMenuList=session
                .createQuery("FROM SubMenu sb")
                .getResultList();
        this.menuList=session.createQuery("from Menu m").getResultList();

        this.subMenu=new SubMenu();
        getSubMenu().setMenuPadre(new Menu());


    }
    /*------------------------------------------------------------------------
     * Metodos  de la clase
     *------------------------------------------------------------------------
     */
    public void saveSubMenu(){
        List<String> errors=validarSubMenu();

        if(errors.size() < 1){
            try {
                session.beginTransaction();
                session.save(this.getSubMenu());
                session.getTransaction().commit();

                addMessage("Menu almacenado correctamente",FacesMessage.SEVERITY_INFO);
            }catch (Exception e){
                this.subMenu=new SubMenu();
                addMessage("Error en la insersion del rol",FacesMessage.SEVERITY_ERROR);
            }
            PrimeFaces.current().executeScript("PF('crtMenu').hide()");
        }else{
            for(String msj : errors ){
                addMessage(msj,FacesMessage.SEVERITY_ERROR);
            }
        }
    }
    public void editSubMenu(){

    }
    public List<String> validarSubMenu(){
        List <String> errors=new ArrayList<String>();
        SubMenu smenu=getSubMenu();
        if(smenu.getNombre()==null || smenu.getNombre().length()<3){
            errors.add("El campo nombre debe poseer almenos 3 caracteres");
        }
        if(smenu.getDescripcion()==null || smenu.getDescripcion().length() < 3){
            errors.add("Debe proporcionar una descripcion mayor a 3 caracteres");
        }
        if(smenu.getIcono() == null){
            errors.add("Debes proporcionar un icono");
        }
        if(smenu.getUrl() ==null || smenu.getUrl().length() < 4){
            errors.add("Url debe poseer almenos 6 caracteres");
        }

        return errors;
    }

    /*------------------------------------------------------------------------
     * Metodos  de segundo grado de clase
     *------------------------------------------------------------------------
     */




    public SubMenu getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(SubMenu subMenu) {
        this.subMenu = subMenu;
    }

    public SubMenu getSubMenuSeleccionado() {
        return subMenuSeleccionado;
    }

    public void setSubMenuSeleccionado(SubMenu subMenuSeleccionado) {
        this.subMenuSeleccionado = subMenuSeleccionado;
    }

    public List<SubMenu> getSubMenuList() {
        return subMenuList;
    }

    public void setSubMenuList(List<SubMenu> subMenuList) {
        this.subMenuList = subMenuList;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public void addMessage(String summary, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
