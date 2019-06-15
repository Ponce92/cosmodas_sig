package com.rq.controllers.reportes;

import com.rq.models.share.Inventario;
import com.rq.models.share.Sucursal;
import com.rq.security.HibernateUtil;
import org.hibernate.Session;


import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Named
@ConversationScoped
public class inventariosCtrl implements Serializable {
    private static final long serialVersionUID = 1L;
    private Session con;

    //Atributos
    private Sucursal sucursal;
    private List<Sucursal> sucursalList;
    private float total;
    private float subTotal;
    private Date fecha;


    @PostConstruct
    public void init(){
        con= HibernateUtil.getSessionFactory().openSession();
        this.sucursalList=con.createQuery("FROM Sucursal suc") .getResultList();
        this.total=calcTotal();
        this.fecha=new Date();
    }


    public float calcTotal(){
        List<Inventario> inventarios;
        float total;
        total=0;
        inventarios=con.createQuery("FROM Inventario inv").getResultList();

        for( Inventario inv: inventarios){
            total=total+inv.getExistencia()*inv.getProducto().getPrecionCompra();
        }
        System.out.println("Total generado por el sistema :"+total);
        return total;
    }

    public void calcSubTotal(){
        if(this.sucursal ==null){
            addMessage(FacesMessage.SEVERITY_WARN,"Atencion.","Debe seleccionar una sucursal");
            return;
        }
        List<Inventario> inventarios;
        float sbt;
        sbt=0;
        inventarios=con.createQuery("FROM Inventario inv WHERE inv.sucursal.id = :id")
                .setParameter("id",this.sucursal.getId())
                .getResultList();

        for( Inventario inv: inventarios){
            sbt=sbt+inv.getExistencia()*inv.getProducto().getPrecionCompra();
        }
        this.subTotal=sbt;
    }

    public Date calcFecha(){
        Date date = new Date();

        return date;
    }








    public  void gerenerarPdf(){
        FacesContext facesContext= FacesContext.getCurrentInstance();
        ExternalContext externalContext=facesContext.getExternalContext();
        HttpSession session=(HttpSession) externalContext.getSession(true);
        String url ="http://localhost:8080/cosmodas_sig_war_exploded/views/rep/inventario/repInventario.xhtml;jsessionid="
                +session.getId()+"?pdf=true";


//        try{
//            ITextRenderer renderer=new ITextRenderer();
//            renderer.setDocument(url);
//
//            renderer.layout();
//
//            HttpServletResponse  res =(HttpServletResponse) externalContext.getResponse();
//            res.reset();
//
//            res.setContentType("application/pdf");
//            res.setHeader("Content-Disposition","inline; filename=\"print-file.pdf\"");
//            OutputStream outputStream=res.getOutputStream();
//            renderer.createPDF(outputStream);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        facesContext.responseComplete();
    }


    /*------------------------------------------------------------------------------------------------------
     *      Metodos de segundo orden dela clase
     * -----------------------------------------------------------------------------------------------------
     */
    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public List<Sucursal> getSucursalList() {
        return sucursalList;
    }

    public void setSucursalList(List<Sucursal> sucursalList) {
        this.sucursalList = sucursalList;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext context= FacesContext.getCurrentInstance();
        context.addMessage(null,new FacesMessage(severity,summary,detail));

    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
