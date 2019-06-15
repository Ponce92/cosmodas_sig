package com.rq.controllers.security;


import com.rq.models.security.Rol;
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

@Named(value = "submenuCtrl")
@ConversationScoped
public class SubmenuCtrl implements Serializable {
    private static final long serialVersionUID = 1L;
    private Session con;

    //Atributos de la clase
    private SubMenu subMenu;
    private List<SubMenu>  subMenuList;
    private Rol rol;
    private List<Rol> rolesList;

    @PostConstruct
    public void init(){
        this.con= HibernateUtil.getSessionFactory().openSession();
        this.subMenuList=new ArrayList<>();

        this.rolesList=con.createQuery("FROM Rol  rol WHERE rol.nombre != :nom")
                .setParameter("nom","Admin IT")
                .getResultList();

        this.subMenuList=new ArrayList<>();
    }


    /*------------------------------------------------------------------------------------------------
     *  Metodos de la del manage beans
     * -----------------------------------------------------------------------------------------------
     */
    public void bsqRol(){
        if(this.rol == null){
            addMessage(FacesMessage.SEVERITY_ERROR,"Datos erroneos","Debe seleccionar un rol");
            return;
        }
    }


    public void getSubmenuss(){
        System.out.println("Nombre de rol :"+this.rol.getNombre());
        if(this.rol.getNombre() == null){
            addMessage(FacesMessage.SEVERITY_WARN,"Atencion","Debes seleccionar un rol");
            return;
        }
        int count=this.rol.getSubMenus().size();
        if(count == 0 ){
            this.subMenuList=con.createQuery("FROM SubMenu sm").getResultList();
        }
        if(count == 1 ){
            this.subMenuList=con.createQuery("FROM SubMenu sm WHERE sm.id != :id")
                    .setParameter("id",this.rol.getSubMenus().get(0).getId())
                    .getResultList();
        }
        if(count > 1){
            this.subMenuList=con.createQuery("FROM SubMenu sm WHERE sm NOT IN (:list)")
                    .setParameterList("list",this.rol.getSubMenus())
                    .getResultList();
        }
        System.out.println("===================>>>"+ count);
        PrimeFaces current=PrimeFaces.current();
        current.executeScript("PF('add').show();");
    }

    public void add(SubMenu subMenu){
        try{
            this.rol.getSubMenus().add(subMenu);
            addMessage(FacesMessage.SEVERITY_INFO,"Systema","Se agregegado el submenu");
        }catch (Exception e){
            addMessage(FacesMessage.SEVERITY_ERROR,"Error en el sistema","El menus no ha podido ser agregado");
            System.out.println("=================>>>>>>> :" + e);
        }
        PrimeFaces.current().executeScript("PF('add').hide();");

    }

    public void save(){
        try{
            con.beginTransaction();
            con.merge(this.rol);
            con.getTransaction().commit();
            addMessage(FacesMessage.SEVERITY_INFO,"Systema","El menu se agrego al rol satisfactoriamente");
        }catch (Exception e){
            addMessage(FacesMessage.SEVERITY_ERROR,"Error de sistema", "El sistema no ha podido completar la trasaccion");
        }
    }

    /*------------------------------------------------------------------------------------------------
     *  Metodos de segundo orden
     * -----------------------------------------------------------------------------------------------
     */

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Session getCon() {
        return con;
    }

    public void setCon(Session con) {
        this.con = con;
    }

    public SubMenu getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(SubMenu subMenu) {
        this.subMenu = subMenu;
    }

    public List<SubMenu> getSubMenuList() {
        return subMenuList;
    }

    public void setSubMenuList(List<SubMenu> subMenuList) {
        this.subMenuList = subMenuList;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Rol> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Rol> rolesList) {
        this.rolesList = rolesList;
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext context= FacesContext.getCurrentInstance();
        context.addMessage(null,new FacesMessage(severity,summary,detail));

    }
}
