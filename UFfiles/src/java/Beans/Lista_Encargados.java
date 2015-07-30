/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Grupo;
import Modelo.Persona;
import Modelo.Usuario;
import Modelo.control;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Britt
 */
@ManagedBean
@SessionScoped
public class Lista_Encargados {

    ArrayList<Grupo> grup = new ArrayList();
    private int codigo_grupo;
    ArrayList<Persona> per = new ArrayList();

    public Lista_Encargados() {
    }

    public void listar() throws ClassNotFoundException {
        System.out.println("entroooooo ");
        control.conectar();
        per.clear();
        System.out.println("codigo grupo " + codigo_grupo);
        control.ejecuteQuery("select persona.*,usuario.nivel from usuario,persona,usuarioGrupo,Grupo\n"
                + "where\n"
                + "usuario.cedula=persona.cedula \n"
                + "and usuario.cod_usuario=usuarioGrupo.cod_usuario\n"
                + "and usuarioGrupo.cod_grupo=grupo.cod_grupo\n"
                + "and grupo.cod_grupo=" + codigo_grupo + " and usuario.nivel>0");

        try {
            while (control.rs.next()) {
                per.add(new Persona(control.rs.getString(1),
                        control.rs.getString(2),
                        control.rs.getString(3), false, control.rs.getInt(8)));
            }
            control.cerrarConexion();
            System.out.println("per " + per.size());
            FacesContext.getCurrentInstance().getExternalContext().responseReset();

        } catch (Exception ex) {
            System.out.println("error " + ex.getMessage());
        }

    }

    public void cargarGrupos() throws ClassNotFoundException {
        grup.clear();
        control.conectar();
        Usuario usu = new Usuario();
        usu = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        control.ejecuteQuery("select grupo.* from grupo,UsuarioGrupo,usuario,persona\n"
                + "where\n"
                + "grupo.cod_grupo=UsuarioGrupo.cod_grupo \n"
                + "and UsuarioGrupo.cod_usuario=usuario.cod_usuario\n"
                + "and usuario.cedula=persona.cedula\n"
                + "and persona.cedula=" + usu.getCedula());

        try {
            while (control.rs.next()) {
                grup.add(new Grupo(control.rs.getInt(1), control.rs.getString(2)));
            }
            control.cerrarConexion();
        } catch (Exception ex) {

        }
    }

    public ArrayList<Grupo> getGrup() {
        return grup;
    }

    public void setGrup(ArrayList<Grupo> grup) {
        this.grup = grup;
    }

    public int getCodigo_grupo() {
        return codigo_grupo;
    }

    public void setCodigo_grupo(int codigo_grupo) {
        this.codigo_grupo = codigo_grupo;
    }

    public ArrayList<Persona> getPer() {
        return per;
    }

    public void setPer(ArrayList<Persona> per) {
        this.per = per;
    }

}
