/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Usuario;
import Modelo.control;
import java.io.IOException;
import java.sql.Date;
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
public class UsuarioUpdate {

    private String nickname;
    private String nombre;
    private String apellido;
    private Date fecha_nacimiento;
    private String correo;

    public UsuarioUpdate() {
    }

    public void recuperarUsuario() {
        Usuario u = new Usuario();
        u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Update_Usuario");
        nickname = u.getUsuario();
        nombre = u.getNombre();
        apellido = u.getApellido();
        fecha_nacimiento = (Date) u.getFecha_naci();
        correo = u.getCorreo();
    }

    public void actualizar() throws ClassNotFoundException, IOException {
        control.conectar();
        Usuario u = new Usuario();
        u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Update_Usuario");
        System.out.println("fecha " +fecha_nacimiento);
      
        
        boolean r = control.ejecuteUpdate("update usuario set nick_name='" + nickname + "'"
                + " where cedula=" + u.getCedula());
        if (r) {
            boolean f = control.ejecuteUpdate("update persona set nombre='" + nombre + "',"
                    + " apellido='" + apellido + "',fecha_nacimiento='" + fecha_nacimiento + "',"
                    + " correo='" + correo + "' where cedula=" + u.getCedula());
            if (f) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("USUlistar.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ""));

            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ""));

        }
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
