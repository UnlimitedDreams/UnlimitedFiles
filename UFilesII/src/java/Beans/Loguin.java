/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Usuario;
import Modelo.control;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Microinformatica
 */
@ManagedBean
@SessionScoped
public class Loguin implements Serializable {

    private String usuario;
    private String cla;

    public Loguin() {

    }

    public void entrar() throws ClassNotFoundException {
        System.out.println("Entro");
        control.conectar();
        control.ejecuteQuery("select persona.*,rol.*\n"
                + "                from usuario,usuariorol,rol,persona\n"
                + "                where\n"
                + "                usuario.cod_usuario=usuariorol.cod_usuario and \n"
                + "                rol.cod_rol=usuariorol.cod_rol and\n"
                + "                usuario.cedula=persona.cedula and\n"
                + "                 usuario.nick_name='" + usuario + "'  and \n"
                + "                 usuario.password='" + cla + "'");

        boolean esta = false;
        int rol = 0;
        String codigo = "";
        try {

            while (control.rs.next()) {
                System.out.println("entro a ciclo");
                esta = true;
                System.out.println("rol " + control.rs.getInt(8));
                Usuario usu = new Usuario(control.rs.getString(1), control.rs.getString(2), control.rs.getString(3),
                        control.rs.getInt(8), control.rs.getString(9));
                System.out.println("--- " +usu.toString());
                if (Definir_usu2(usu.getCedula())==true) {
                    System.out.println("1");
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", usu);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("menuAdm.xhtml");
                } else {
                    System.out.println("+++++++ " );
                    if (usu.getRol() == 1) {
                        System.out.println("1");
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", usu);
                        FacesContext.getCurrentInstance().getExternalContext().redirect("menuAdm.xhtml");
                    } else if (usu.getRol() == 2) {
                        System.out.println("2");
                        System.out.println("");
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", usu);
                        FacesContext.getCurrentInstance().getExternalContext().redirect("menuUsuario.xhtml");
                    }

                }

            }

            control.cerrarConexion();

        } catch (Exception ex) {

        }
    }

    public boolean Definir_usu2(String x) throws ClassNotFoundException {
        control.conectar();
        control.ejecuteQuery("               select * from usuario,persona,comite\n"
                + "               where\n"
                + "               persona.cedula=usuario.cedula\n"
                + "               and comite.cod_usuario=usuario.cod_usuario\n"
                + "               and persona.cedula=" + x + "and comite.estado='A'");
        boolean r = false;
        try {
            while (control.rs.next()) {
                r = true;
                System.out.println("aprobo");
            }
        } catch (Exception ex) {

        }
        return r;

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCla() {
        return cla;
    }

    public void setCla(String cla) {
        this.cla = cla;
    }

}
