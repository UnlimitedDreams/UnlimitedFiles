/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Archivos;
import Modelo.Grupo;
import Modelo.Persona;
import Modelo.Usuario;
import Modelo.control;
import java.io.IOException;
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
public class RevicionesBeans {

    /**
     * Creates a new instance of RevicionesBeans
     */
    private int cod_grupo;
    ArrayList<Grupo> grup = new ArrayList();
    ArrayList<Persona> per = new ArrayList();

    public RevicionesBeans() {
        System.out.println("Hola");
    }

    public void mirarSession() {
        System.out.println("+++++");
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("persona") != null) {
            System.out.println("ya existe");
            per.clear();
        }

    }

    public void cargarRoles() throws ClassNotFoundException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("persona");
        grup.clear();
        System.out.println("entro al grupo");
        control.conectar();
        control.ejecuteQuery("select * from rol where estado='A'");
        try {
            while (control.rs.next()) {
                grup.add(new Grupo(control.rs.getInt(1), control.rs.getString(2)));
            }
            control.cerrarConexion();
        } catch (Exception ex) {

        }
        mirarSession();
    }

    public boolean buscar_Comite(int x) throws ClassNotFoundException {
        boolean r = false;
        control.conectar();
        control.ejecuteQuery("select * from comite where cod_usuario=" + x);
        try {
            while (control.rs.next()) {
                r = true;
            }
            control.cerrarConexion();
        } catch (Exception ex) {

        }

        return r;
    }

    public void ActivarPer(String c, int usuario) throws ClassNotFoundException, IOException {
        Persona p = null;
        Persona p2 = null;
        int may = 0;
        int num = 0;
        if (buscar_Comite(usuario)) {
            System.out.println("ppppp");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ya existe en el comite", ""));
        } else {
            for (int i = 0; i < per.size(); i++) {
                p = (Persona) per.get(i);
                if (p.getCedula().equals(c)) {
                    if (p.isEstado() == false) {
                        p.setEstado(true);
                    } else {
                        p.setEstado(false);
                    }
                }
            }
            may = 0;
            for (int i = 0; i < per.size(); i++) {
                p = (Persona) per.get(i);
                System.out.println("-- " + per.get(i));
            }
            nviar();
        }

    }

    public void nviar() throws IOException {
        ArrayList<Persona> pe = new ArrayList();
        Persona peso = null;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("persona");
        for (int i = 0; i < per.size(); i++) {
            peso = (Persona) per.get(i);
            System.out.println("---- " + per.get(i));
            if (peso.isEstado() == true) {
                pe.add(per.get(i));
            }
        }
        System.out.println("+++++ " + pe.size());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("persona", pe);
        FacesContext.getCurrentInstance().getExternalContext().redirect("JUNagregar2.xhtml");
    }

    public void listar() throws ClassNotFoundException {
        System.out.println("entroooooo ");
        control.conectar();
        per.clear();
        System.out.println("codigo grupo " + cod_grupo);
        control.ejecuteQuery("select distinct persona.cedula,nombre,apellido,rol.descripcion,usuario.cod_usuario \n"
                + "                from usuario,persona,rol,usuariorol\n"
                + "                        where\n"
                + "                            usuario.cedula=persona.cedula \n"
                + "                          and rol.cod_rol=usuariorol.cod_rol\n"
                + "                          and usuario.cod_usuario=usuariorol.cod_usuario\n"
                + "                             and rol.cod_rol=" + cod_grupo);

        try {
            while (control.rs.next()) {
                per.add(new Persona(control.rs.getString(1),
                        control.rs.getString(2),
                        control.rs.getString(3), false,
                        control.rs.getInt(5)));
            }
            control.cerrarConexion();
            System.out.println("per " + per.size());
            FacesContext.getCurrentInstance().getExternalContext().responseReset();

        } catch (Exception ex) {
            System.out.println("error " + ex.getMessage());
        }

    }

    public int getCod_grupo() {
        return cod_grupo;
    }

    public void setCod_grupo(int cod_grupo) {
        this.cod_grupo = cod_grupo;
    }

    public ArrayList<Grupo> getGrup() {
        return grup;
    }

    public void setGrup(ArrayList<Grupo> grup) {
        this.grup = grup;
    }

    public ArrayList<Persona> getPer() {
        return per;
    }

    public void setPer(ArrayList<Persona> per) {
        this.per = per;
    }

}
