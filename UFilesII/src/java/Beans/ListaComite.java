/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Persona;
import Modelo.control;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Britt
 */
@ManagedBean
@SessionScoped
public class ListaComite {

    private String cedula;
    private String Nombre;
    private String Apellido;
    private int nivel;
    private int usuario;

    ArrayList<Persona> per = new ArrayList();

    public ListaComite() {
    }

    public ListaComite(String cedula, String Nombre, String Apellido, int nivel, int usuario) {
        this.cedula = cedula;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.nivel = nivel;
        this.usuario = usuario;
    }

    public void listar_Comite() throws ClassNotFoundException {
        control.conectar();
        per.clear();
        control.ejecuteQuery("     select persona.cedula,nombre,apellido,comite.nivel,usuario.cod_usuario from comite,usuario,persona\n"
                + "                where\n"
                + "                 usuario.cedula=persona.cedula \n"
                + "                 and comite.cod_usuario=usuario.cod_usuario\n"
                + "                 and comite.estado='A' "
                + " order by comite.nivel DESC");
        try {
            while (control.rs.next()) {
                per.add(new Persona(control.rs.getString(1),
                        control.rs.getString(2),
                        control.rs.getString(3),
                        control.rs.getInt(5),
                        control.rs.getInt(4)));
            }
            control.cerrarConexion();
        } catch (Exception ex) {

        }
    }

    public void borrar(int usuario) throws ClassNotFoundException {
        control.conectar();
        boolean r = control.ejecuteUpdate("update comite set estado='I' where "
                + " cod_usuario=" + usuario);
        if (r) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Executed", ""));
            FacesContext.getCurrentInstance().getExternalContext().responseReset();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", ""));
        }

    }

    public void update(String cedula, String nombre, String apellido, int nivel, int usu) throws IOException {
        ListaComite lista = new ListaComite(cedula, nombre, apellido, nivel, usu);
        System.out.println("Usuariooo " +usu);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ComiteUpdate", lista);
        FacesContext.getCurrentInstance().getExternalContext().redirect("JUNUpdateNivel.xhtml");
    }

    public ArrayList<Persona> getPer() {
        return per;
    }

    public void setPer(ArrayList<Persona> per) {
        this.per = per;
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
