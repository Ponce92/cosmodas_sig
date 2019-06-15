package com.rq.controllers;


import com.rq.models.security.Menu;
import com.rq.models.security.SubMenu;
import com.rq.models.security.Usuario;
import com.rq.security.HibernateUtil;
import org.hibernate.Session;


import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ConversationScoped
public class LoginCtrl  implements Serializable  {


    private Session session;
    private Usuario usuario;


    @PostConstruct
    public void init(){
        session=HibernateUtil.getSessionFactory().openSession();

        this.usuario=new Usuario();
    }
    /*------------------------------------------------------------------------
     * Metodos  de la clase
     *------------------------------------------------------------------------
     */
    public void login(){
        List<Menu> menus;
        List<SubMenu> subMenus =new ArrayList<SubMenu>();

        try{

            this.usuario=(Usuario) session.createQuery("FROM Usuario u WHERE u.username= :us")
                    .setParameter("us",getUsuario().getUsername())
                    .getSingleResult();

            menus=session.createQuery("FROM Menu m WHERE m.rol.id = :id ")
                    .setParameter("id",usuario.getRol().getId())
                    .getResultList();

            for(Menu m : menus){
                List<SubMenu> list;
                list=session.createQuery("FROM SubMenu sm WHERE sm.menuPadre.id = :id")
                        .setParameter("id",m.getId())
                        .getResultList();
                subMenus.addAll(list);
            }

            //Seteamos el beans de session . . .
            System.out.println("----------      Seteando valores------------");
            FacesContext fc=FacesContext.getCurrentInstance();
            HttpSession ss = (HttpSession) fc.getExternalContext().getSession(true);

            ss.setAttribute("user",usuario);
            System.out.println("----------      Fin de seteado ------------");

            //Redireccionamos al home...

            FacesContext.getCurrentInstance().getExternalContext().redirect("views/index.xhtml");
        }catch (Exception e){
            System.out.println(e);
            addMessage("Error en los datos",FacesMessage.SEVERITY_ERROR);
            addMessage(""+e,FacesMessage.SEVERITY_ERROR);
            return;
        }

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

    public void addMessage(String summary, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
