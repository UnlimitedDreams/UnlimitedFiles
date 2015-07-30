/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.control;
import java.io.IOException;
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
public class UpdateJunta {

    /**
     * Creates a new instance of UpdateJunta
     */
    private String cedula;
    private String Nombre;
    private String Apellido;
    private int nivel;
    private int usuario;

    public UpdateJunta() {
    }

    public void recuperar() {
        ListaComite lista = new ListaComite();
        lista = (ListaComite) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ComiteUpdate");
        cedula = lista.getCedula();
        Nombre = lista.getNombre();
        Apellido = lista.getApellido();
        nivel = lista.getNivel();
        usuario = lista.getUsuario();
    }

    public boolean buscarNivel() throws ClassNotFoundException {
        System.out.println("nivel  " +nivel);
        control.conectar();
        control.ejecuteQuery("select * from comite where nivel=" + nivel);
        boolean r = false;
        try {
            while (control.rs.next()) {
                r = true;
            }
            control.cerrarConexion();
        } catch (Exception x) {

        }
        System.out.println("r " +r);
        return r;
    }

    public void update() throws ClassNotFoundException, IOException {
        if (buscarNivel()==true) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nivel ya se enuentra", ""));
        } else {
            ListaComite lista = new ListaComite();
            lista = (ListaComite) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ComiteUpdate");
            control.conectar();
            boolean r = control.ejecuteUpdate("update comite set nivel=" + nivel + ""
                    + " where cod_usuario=" + lista.getUsuario());
            control.cerrarConexion();
            if (r) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("JUNLista.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ""));
            }
        }

    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

}
