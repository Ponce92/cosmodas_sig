package com.rq.controllers;

import com.rq.models.security.Menu;
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
public class MenuCtrl implements Serializable {
    private Session session;
    private Menu menu;
    private Menu menuSeleccionado;
    private List<Menu> menuList;
    private List<Rol> rolesList;

    @PostConstruct
    public void init(){
        session= HibernateUtil.getSessionFactory().openSession();

        menuList=session.createQuery("FROM Menu m").getResultList();
        this.menu=new Menu();
        this.menuSeleccionado=new Menu();
    }
    /*================================================================================
     *      Funciones primordiales del controlador . . .
     * ===============================================================================
     */

    public void saveMenu(){
        List<String> errors=validarMenu(this.menu);
        if(errors.size() < 1){
            try{
                session.beginTransaction();
                session.save(this.getMenu());
                session.getTransaction().commit();

                addMessage("Menu Almacenado correctamente",FacesMessage.SEVERITY_INFO);

            }catch (Exception e){

                addMessage("Error en la insersion del rol",FacesMessage.SEVERITY_ERROR);

            }
            PrimeFaces.current().executeScript("PF('crtMenu').hide()");
        }else{
            for(String msj : errors ){
                addMessage(msj,FacesMessage.SEVERITY_ERROR);
            }
        }
    }

    public void updateMenu(){
        List<String> errors=validarMenu(this.menuSeleccionado);
        if(errors.size() < 1){
            try{
                session.beginTransaction();
                session.merge(this.getMenuSeleccionado());
                session.getTransaction().commit();

                addMessage("Menu Almacenado correctamente",FacesMessage.SEVERITY_INFO);

            }catch (Exception e){

                addMessage("Error en la insersion del rol",FacesMessage.SEVERITY_ERROR);

            }
            PrimeFaces.current().executeScript("PF('menu_edit').hide()");
        }else{
            for(String msj : errors ){
                addMessage(msj,FacesMessage.SEVERITY_ERROR);
            }
        }
    }

    public List<String> validarMenu(Menu menu){

        List<String> errors=new ArrayList<String>();

        if(menu.getNombre() ==null || menu.getNombre().length() < 4){ errors.add("El nombre debe poseer almenos 4 caracteres."); }
        if(menu.getIcono()==null){ menu.setIcono("pi pi-caret-right");}
        if(menu.getDescripcion()==null || menu.getDescripcion().length()<6){errors.add("Debe especificar una descripcion mayor a 4 caractere");}

        return errors;
    }


    /*================================================================================
     *  Funciones de 2do grado de la clase
     * ===============================================================================
     */

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Menu getMenuSeleccionado() {
        return menuSeleccionado;
    }

    public void setMenuSeleccionado(Menu menuSeleccionado) {
        System.out.println("_____________________________________________________________-");
        this.menuSeleccionado = menuSeleccionado;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public List<Rol> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Rol> rolesList) {
        this.rolesList = rolesList;
    }

    public void addMessage(String summary, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
