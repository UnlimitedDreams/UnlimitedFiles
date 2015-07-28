/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Persona;
import Modelo.Sequence;
import Modelo.control;
import java.io.IOException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Britt
 */
@ManagedBean
@RequestScoped
public class RevicionInfo {

    /**
     * Creates a new instance of RevicionInfo
     */
    ArrayList<Persona> p = new ArrayList();

    public RevicionInfo() {
    }

    public void guardar() throws ClassNotFoundException, IOException {
        control.conectar();
        Persona pr = null;
        boolean r = false;
        this.p = (ArrayList) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("persona");
        for (int i = 0; i < p.size(); i++) {
            int cod_comite = Sequence.seque("select max(cod_comite) from comite");
            System.out.println("---");
            pr = (Persona) p.get(i);
            r = control.ejecuteUpdate("insert into comite values("
                    + cod_comite + ",0," + pr.getCod_usuario() + ",'A')");
        }
        if (r) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Executed", ""));
            FacesContext.getCurrentInstance().getExternalContext().redirect("menuAdm.xhtml");

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ""));
        }
        control.cerrarConexion();
    }

    public void recuperar() {
        this.p = (ArrayList) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("persona");
    }

    public ArrayList<Persona> getP() {
        return p;
    }

    public void setP(ArrayList<Persona> p) {
        this.p = p;
    }

}
