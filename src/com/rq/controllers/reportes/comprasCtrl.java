package com.rq.controllers.reportes;

import com.rq.security.HibernateUtil;
import com.rq.models.share.Proveedor;
import org.hibernate.Session;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@ConversationScoped
public class comprasCtrl implements Serializable {
    private Session con;
    private Proveedor proveedor;
    private List<Proveedor> proveedorList;
    private Date fechaInicio;
    private Date FechaFin;

    @PostConstruct
    public void init(){
        con=HibernateUtil.getSessionFactory().openSession();
        this.proveedorList=con.createQuery("FROM Proveedor pr WHERE pr.estado = :est")
                .setParameter("est",true)
                .getResultList();
    }

    public boolean validate(){
        Boolean res=false;
            if(this.FechaFin ==null || this.fechaInicio==null){
                addMessage(FacesMessage.SEVERITY_WARN,"Datos erroneos", "Se debe especificar una fecha" +
                        " de inicio y una fecha de fin prar generar el reporte");


                return false;
            }
        return res;
    }

    public void calcularTotal(){
        if(this.validate()){
            addMessage(FacesMessage.SEVERITY_INFO,"Alert", "Metodo exitoso");
        }
    }



    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<Proveedor> getProveedorList() {
        return proveedorList;
    }

    public void setProveedorList(List<Proveedor> proveedorList) {
        this.proveedorList = proveedorList;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        FechaFin = fechaFin;
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext context= FacesContext.getCurrentInstance();
        context.addMessage(null,new FacesMessage(severity,summary,detail));

    }
}
