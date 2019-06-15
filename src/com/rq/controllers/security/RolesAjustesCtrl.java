package com.rq.controllers.security;

import com.rq.models.security.Menu;
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

@Named
@ConversationScoped
public class RolesAjustesCtrl implements Serializable {
    private Session con;
    private Rol     rolSeleccionado;
    private int     idRol;
    private int     idMenu;
    private Menu    menuSeleccionado;
    private SubMenu subMenu;

    private List<Rol>       rolesList;
    private List<Menu>      menuList;
    private List<SubMenu>   subMenuList;

    @PostConstruct()
    public void init(){
        con= HibernateUtil.getSessionFactory().openSession();
        this.rolesList=con.createQuery("FROM Rol  rol WHERE rol.estado = :est")
                .setParameter("est",true)
                .getResultList();
        this.subMenuList=new ArrayList<>();
        this.menuList=new ArrayList<>();

        this.subMenu=new SubMenu();

    }

    /*-----------------------------------------------------------------------------------------
     *  Desc: Busca el rol en la base de datos, luego obtiene la lista de menus que
     *        aun no estan asociados a dicho rol para que pueda ser agregados
     * --------------------------------------------------------------------------------------------
     */

    public void bsqRol(){

        if(this.idRol < 1){
            addMessage(FacesMessage.SEVERITY_ERROR,"Datos erroneos","Debe seleccionar un rol");
            return;
        }

        this.rolSeleccionado=con.get(Rol.class,this.idRol);
        //Buscamos el los menus asociados a este rol y buscamos los menus que aun no estan ligados
        if(this.rolSeleccionado.getMenus().size() == 0){
            this.menuList=con.createQuery("FROM Menu menu")
                    .getResultList();
        }else{
            this.menuList=con.createQuery("FROM Menu menu WHERE menu NOT IN (:list)")
                    .setParameterList("list",this.rolSeleccionado.getMenus())
                    .getResultList();
        }

        //Recuperamos los submemus que aun pueden agregarse al rol seleccionado
        if(this.rolSeleccionado.getSubMenus().size() < 1){
            this.subMenuList=con.createQuery("FROM SubMenu sm")
                    .getResultList();
        }else{
            if(this.rolSeleccionado.getSubMenus().size()==1){
                this.subMenuList=con.createQuery("FROM SubMenu sm WHERE sm != :id")
                        .setParameter("id",this.rolSeleccionado.getSubMenus().get(1).getId())
                        .getResultList();
            }else{
                this.subMenuList=con.createQuery("FROM SubMenu sm WHERE sm  NOT IN (:list)")
                        .setParameterList("list",this.rolSeleccionado.getSubMenus())
                        .getResultList();
            }
        }

        this.menuSeleccionado=new Menu();
    }

    /*-----------------------------------------------------------------------------------------
     *  guardar: Actualiza el registro del rol seleccionado en la base de datos
     * --------------------------------------------------------------------------------------------
     */
        public void guardar(){
            try{
                con.beginTransaction();
                con.merge(this.rolSeleccionado);
                con.getTransaction().commit();

                addMessage(FacesMessage.SEVERITY_INFO,"Exito !", "Los cambios se han almacenado correctamente");
            }catch (Exception e){

                addMessage(FacesMessage.SEVERITY_ERROR,"Error","El sistema no ha podido completar la transaccion");
            }
        }
    /*-----------------------------------------------------------------------------------------
     *      Metodos secundarios del beans
     *--------------------------------------------------------------------------------------------
     */

    public Rol getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(Rol rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public Menu getMenuSeleccionado() {
        return menuSeleccionado;
    }

    public void setMenuSeleccionado(Menu menuSeleccionado) {
        this.rolSeleccionado.getMenus().add(menuSeleccionado);
        PrimeFaces.current().executeScript("PF('addMenuDlg').hide();");
        this.menuSeleccionado = menuSeleccionado;
    }

    public List<Rol> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Rol> rolesList) {
        this.rolesList = rolesList;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public List<SubMenu> getSubMenuList() {
        return subMenuList;
    }

    public void setSubMenuList(List<SubMenu> subMenuList) {
        this.subMenuList = subMenuList;
    }

    public SubMenu getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(SubMenu subMenu) {
        System.out.println("---------------------Se ejecuta sub menus-----------------------");
        this.rolSeleccionado.getSubMenus().add(subMenu);
        this.subMenuList.remove(subMenu);
        System.out.println("Submenus :"+ this.subMenuList.size());
        this.subMenu = subMenu;
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext context= FacesContext.getCurrentInstance();
        context.addMessage(null,new FacesMessage(severity,summary,detail));

    }
}
