package com.rq.security;


import com.rq.models.security.Menu;
import com.rq.models.security.SubMenu;
import com.rq.models.security.Usuario;
import com.rq.models.share.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;


import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class SessionCtrl implements Serializable {
    private Session         con;
    private Usuario         user;
    private List<Menu>      menus;
    private List<SubMenu>   submenus;
    private String username;
    private String pass;
    private Boolean logueado;

    @PostConstruct
    public void init(){
            con=HibernateUtil.getSessionFactory().openSession();
    }



    public void login(){
        if(!validate()){return;}
        try{
            this.user=(Usuario) con.createQuery("FROM Usuario where username = :us")
                    .setParameter("us",this.username)
                    .getSingleResult();

            String psw= DigestUtils.sha1Hex(this.pass);

            if(psw.equals(this.user.getPassword())){
                this.logueado=true;
                FacesContext.getCurrentInstance().getExternalContext().redirect("views/index.xhtml");

            }else{
                addMessage(FacesMessage.SEVERITY_ERROR,"Error de autenticacion","El password es incorrecto");
            }


        }  catch (Exception e){
            addMessage(FacesMessage.SEVERITY_ERROR,"Error de autenticacion","Credenciales ingresadas invalidas");
        }
    }

    private boolean validate(){

        if(this.username==null || this.username ==""){
            addMessage(FacesMessage.SEVERITY_WARN,"Error","Ingrese un usuario");
            return false;
        }
        if(this.pass==null || this.pass ==""){

            addMessage(FacesMessage.SEVERITY_WARN,"Error","Ingrese un password");
            return false;
        }

        return true;
    }

    //====================================================================
    //====================================================================

    public void addSucursales(){

        Sucursal s;
        s=new Sucursal();
        s.setCodigo("SUCSS");
        s.setNombre("San Salvador");
        s.setDescripcion("scdavdbghtnyjmkiolikuyhgrewsdegtryujiol");
        s.setEstado(true);

        con.beginTransaction();
        con.save(s);
        con.getTransaction().commit();

        s=new Sucursal();
        s.setCodigo("SUCSM");
        s.setNombre("San San miguel");
        s.setDescripcion("scdavdbghtnyjmkiolikuyhgrewsdegtryujiol");
        s.setEstado(true);
        con.beginTransaction();
        con.save(s);
        con.getTransaction().commit();

        s=new Sucursal();
        s.setCodigo("SUCSA");
        s.setNombre("San Santa ana");
        s.setDescripcion("scdavdbghtnyjmkiolikuyhgrewsdegtryujiol");
        s.setEstado(true);
        con.beginTransaction();
        con.save(s);
        con.getTransaction().commit();

        s=new Sucursal();
        s.setCodigo("SUCMR");
        s.setNombre("Morazan");
        s.setDescripcion("scfghjkjdavdbghtnyjmkiolikuyhgrewsdegtryujiol");
        s.setEstado(true);

        con.beginTransaction();
        con.save(s);
        con.getTransaction().commit();

        s=new Sucursal();
        s.setCodigo("SUCST");
        s.setNombre("Sonsonate");
        s.setDescripcion("scfghjkjdavdbghtnfegtryujigrewsdegtryujiol");
        s.setEstado(true);
        con.beginTransaction();
        con.save(s);
        con.getTransaction().commit();
        addMessage(FacesMessage.SEVERITY_INFO,"OK","asdfasdfasdfad");
    }

    public void addInventario(){
        List<Sucursal> sucursales;
        List<Producto> productos;

        sucursales=con.createQuery("From Sucursal ").getResultList();
        productos=con.createQuery("FROM Producto ").getResultList();

        for(Sucursal suc : sucursales){
            for(Producto pro : productos){
                Inventario inv=new Inventario();
                inv.setProducto(pro);
                inv.setSucursal(suc);
                inv.setEstado(true);
                inv.setExistencia((int) (Math.random()*1000));

                con.beginTransaction();
                con.save(inv);
                con.getTransaction().commit();
            }
        }
        addMessage(FacesMessage.SEVERITY_INFO,"OK","asdfasdfasdfad");
    }

    public void addClientes(){
        Cliente cliente=new Cliente();

        cliente.setCodigo("PQ1901");
        cliente.setNombre("Johanna Lopez");

        con.beginTransaction();
        con.save(cliente);
        con.getTransaction().commit();

        cliente=new Cliente();
        cliente.setCodigo("EG2309");
        cliente.setNombre("Efrain Gutierrez");
        con.beginTransaction();
        con.save(cliente);
        con.getTransaction().commit();

        cliente=new Cliente();
        cliente.setCodigo("MG4501");
        cliente.setNombre("Magaly Gutierrez");
        con.beginTransaction();
        con.save(cliente);
        con.getTransaction().commit();

        cliente=new Cliente();
        cliente.setCodigo("GF2345");
        cliente.setNombre("Gerardo Flamenco");
        con.beginTransaction();
        con.save(cliente);
        con.getTransaction().commit();


        cliente=new Cliente();
        cliente.setCodigo("AP1209");
        cliente.setNombre("Alejandro Pineda");
        con.beginTransaction();
        con.save(cliente);
        con.getTransaction().commit();

        cliente=new Cliente();
        cliente.setCodigo("AZ2340");
        cliente.setNombre("Alejandro Zapata");
        con.beginTransaction();
        con.save(cliente);
        con.getTransaction().commit();

        cliente=new Cliente();
        cliente.setCodigo("DH2309");
        cliente.setNombre("Daniel Hernadez");
        con.beginTransaction();
        con.save(cliente);
        con.getTransaction().commit();

        cliente=new Cliente();
        cliente.setCodigo("HR2535");
        cliente.setNombre("Hemmanuel Rosales");
        con.beginTransaction();
        con.save(cliente);
        con.getTransaction().commit();

        cliente=new Cliente();
        cliente.setCodigo("UI0964");
        cliente.setNombre("Ursula Infante");
        con.beginTransaction();
        con.save(cliente);
        con.getTransaction().commit();

        cliente=new Cliente();
        cliente.setCodigo("TY8744");
        cliente.setNombre("Tomas Yandere");
        con.beginTransaction();
        con.save(cliente);
        con.getTransaction().commit();

        cliente=new Cliente();
        cliente.setCodigo("RG3450");
        cliente.setNombre("Regina Guirola");
        con.beginTransaction();
        con.save(cliente);
        con.getTransaction().commit();

    }

    public void addProveedores(){
        Proveedor proveedor=new Proveedor();

        proveedor.setCodigo("PRV001");
        proveedor.setEstado(true);
        proveedor.setNombre("Industrias  La Constancia S.A de C.V");
        con.beginTransaction();
        con.save(proveedor);
        con.getTransaction().commit();

        proveedor=new Proveedor();
        proveedor.setCodigo("PRV002");
        proveedor.setEstado(true);
        proveedor.setNombre("Industrias San Flesh S.A DE C.V");
        con.beginTransaction();
        con.save(proveedor);
        con.getTransaction().commit();

        proveedor=new Proveedor();
        proveedor.setCodigo("PRV003");
        proveedor.setEstado(true);
        proveedor.setNombre("Industrias  Miguel S.A de C.V");
        con.beginTransaction();
        con.save(proveedor);
        con.getTransaction().commit();

        proveedor=new Proveedor();
        proveedor.setCodigo("PRV004");
        proveedor.setEstado(true);
        proveedor.setNombre("Industrias  El Buen Vivir S.A de C.V");
        con.beginTransaction();
        con.save(proveedor);
        con.getTransaction().commit();

        proveedor=new Proveedor();
        proveedor.setCodigo("PRV005");
        proveedor.setEstado(true);
        proveedor.setNombre("Industrias  La Ceibita S.A de C.V");
        con.beginTransaction();
        con.save(proveedor);
        con.getTransaction().commit();
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {

        this.menus = menus;
    }

    public List<SubMenu> getSubmenus() {
        return submenus;
    }

    public void setSubmenus(List<SubMenu> submenus) {
        this.submenus = submenus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext context= FacesContext.getCurrentInstance();
        context.addMessage(null,new FacesMessage(severity,summary,detail));

    }
}
