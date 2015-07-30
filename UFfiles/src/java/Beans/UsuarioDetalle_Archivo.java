/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Comite_Revicion;
import Modelo.control;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Britt
 */
@ManagedBean
@RequestScoped
public class UsuarioDetalle_Archivo {

    /**
     * Creates a new instance of UsuarioDetalle_Archivo
     */
    ArrayList<Comite_Revicion> comi = new ArrayList();

    public UsuarioDetalle_Archivo() {
    }
    
    public void recuperar() throws ClassNotFoundException {
        int cod = 0;
        cod = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Codigo_archivo");
        control.conectar();
        comi.clear();
        control.ejecuteQuery("select persona.nombre,apellido,revicion.comentario,revicion.estado from revicion,comite,usuario,persona where \n"
                + "persona.cedula=usuario.cedula\n"
                + "and usuario.cod_usuario=comite.cod_usuario\n"
                + "and comite.cod_comite=revicion.cod_comite\n"
                + "and cod_archivo=" + cod + " order by revicion.cod_revicion");
        try {
            while (control.rs.next()) {
                comi.add(new Comite_Revicion(control.rs.getString(4),
                        control.rs.getString(1) + " " + control.rs.getString(2),
                        control.rs.getString(3)));
            }
        } catch (Exception ex) {
            
        }
    }

    public ArrayList<Comite_Revicion> getComi() {
        return comi;
    }

    public void setComi(ArrayList<Comite_Revicion> comi) {
        this.comi = comi;
    }
    
}
