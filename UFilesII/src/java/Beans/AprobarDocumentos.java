/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.ArchivoDesaprobado;
import Modelo.Archivos;
import Modelo.Comite_Revicion;
import Modelo.Mail;
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
public class AprobarDocumentos {

    private String comentario;

    public AprobarDocumentos() {
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void pasar_estados() throws ClassNotFoundException {

        control.conectar();
        ArrayList cod = null;

        cod = (ArrayList) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Cod_revicion");
        System.out.println("codigo de archivo " + cod.get(1));
        control.ejecuteQuery("select comite.cod_comite,revicion.cod_revicion,comite.estado,persona.correo,archivo.titulo from \n"
                + "revicion,comite,usuario,persona,archivo\n"
                + "                where \n"
                + "                persona.cedula=usuario.cedula and \n"
                + "                usuario.cod_usuario=comite.cod_usuario and \n"
                + "                               comite.cod_comite=revicion.cod_comite and\n"
                + "                               revicion.cod_archivo=archivo.cod_archivo and\n"
                + "                                archivo.cod_archivo=" + cod.get(1) + "  order by \n"
                + "                               revicion.cod_revicion ASC  ");
        ArrayList<Comite_Revicion> comi = new ArrayList();
        try {
            while (control.rs.next()) {
                comi.add(new Comite_Revicion(control.rs.getInt(1),
                        control.rs.getInt(2),
                        control.rs.getString(3), true,
                        control.rs.getString(4),
                        control.rs.getString(5)));
            }

            Comite_Revicion temp = null;
            Comite_Revicion temp2 = null;
            Comite_Revicion temp3 = null;
            int prueba = 0;
            System.out.println("Tamaño " + comi.size() + "--- " + cod.get(0));
            for (int i = 0; i < comi.size(); i++) {
                temp = (Comite_Revicion) comi.get(i);
                if (temp.getNivel() == cod.get(0).hashCode()) {
                    System.out.println("entro");
                    System.out.println("+ " + (i + 1) + "<" + comi.size());
                    if ((i + 1) < comi.size()) {
                        System.out.println("pasar a pendiente ");
                        prueba = i;
                        temp2 = (Comite_Revicion) comi.get(i + 1);
                        System.out.println("---- " + temp2.getNivel());
                        boolean fr = control.ejecuteUpdate("update revicion set estado="
                                + "'Pendiente' where cod_revicion=" + temp2.getNivel());
//                        if (fr) {
//                            Mail m = new Mail();
//                            m.SendMail(1, temp2.getCorreo(), "Revisar Proyecto", temp2.getTitulo());
//                            break;
//                        }
                    } else {
                        System.out.println("Documento aprobado");
                        boolean rg = control.ejecuteUpdate("update archivo set cod_estado=2 where"
                                + " cod_archivo=" + cod.get(1));
//                        if (rg) {
//                            EnviarCorreos(temp.getTitulo());
//                            break;
//                        }

                    }
                }
            }
            control.cerrarConexion();
        } catch (Exception ex) {

        }
    }

    public void EnviarCorreos(String titulo) throws ClassNotFoundException {
        control.conectar();
        control.ejecuteQuery("select persona.correo from comite,usuario,persona\n"
                + "where \n"
                + "persona.cedula=usuario.cedula and \n"
                + "usuario.cod_usuario=comite.cod_usuario  and comite.estado='A'");
        ArrayList<String> correo = new ArrayList();
        try {
            while (control.rs.next()) {
                correo.add(control.rs.getString(1));
            }
//            Mail m = new Mail();
//            String corro = "";
//            for (int i = 0; i < correo.size(); i++) {
//                corro = (String) correo.get(i);
//                m.SendMail(2, corro, "Aprobado Proyecto", titulo);
//            }
        } catch (Exception ex) {

        }

    }

    public void EnviarCorreosDesa() throws ClassNotFoundException {
        control.conectar();
        ArrayList cod = null;

        cod = (ArrayList) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Cod_revicion");
        control.ejecuteQuery("select persona.correo,archivo.titulo,archivo.cedula from comite,usuario,persona,revicion,archivo\n"
                + "               where \n"
                + "                persona.cedula=usuario.cedula and \n"
                + "               usuario.cod_usuario=comite.cod_usuario\n"
                + "               and comite.cod_comite=revicion.cod_comite\n"
                + "               and revicion.cod_archivo=archivo.cod_archivo\n"
                + "                and comite.estado='A' and archivo.cod_archivo=" + cod.get(1));
        ArrayList<ArchivoDesaprobado> correo = new ArrayList();
        try {

            while (control.rs.next()) {
                correo.add(new ArchivoDesaprobado(control.rs.getString(1),
                        control.rs.getString(2), control.rs.getString(3)));
            }
            Mail m = new Mail();
            String corro = "";

            ArchivoDesaprobado temp = null;
            for (int i = 0; i < correo.size(); i++) {
                temp = (ArchivoDesaprobado) correo.get(i);
                m.SendMail(3, temp.getCorreo(), "Desaprobado Proyecto", temp.getTitulo());
            }
            System.out.println("Cedula "+correo.get(0).getCedula());
            enviarCorreoPersona(correo.get(0).getCedula(), correo.get(0).getTitulo());
        } catch (Exception ex) {
            System.out.println("Error " +ex.toString());
        }

    }

    public void enviarCorreoPersona(String x, String nombre) throws ClassNotFoundException {
        control.conectar();
        control.ejecuteQuery("select correo from persona where cedula=" + x);
        String correo = "";
        try {
            while (control.rs.next()) {
                correo = control.rs.getString(1);
            }
            Mail m = new Mail();
            m.SendMail(3, correo, "DESAPROBACION DEL PROYECTO", nombre);
        } catch (Exception ex) {

        }
    }

    public void aprobar() throws ClassNotFoundException, IOException {
        ArrayList cod = null;
        if (comentario == "") {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Debes comentar", ""));

        } else {
            cod = (ArrayList) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Cod_revicion");
            control.conectar();
            System.out.println("------ cod_revicion " + cod.get(0));
            boolean r = control.ejecuteUpdate("update revicion set estado='Aprobado',"
                    + "comentario='" + comentario + "' where cod_revicion=" + cod.get(0));
            if (r) {
                pasar_estados();
                FacesContext.getCurrentInstance().getExternalContext().redirect("menuAdm.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro", ""));

            }
        }

    }

    public void desaprobar() throws ClassNotFoundException, IOException {
        ArrayList cod = null;
        if (comentario == "") {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Debes Comentar", ""));

        } else {
            cod = (ArrayList) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Cod_revicion");
            control.conectar();
            boolean r = control.ejecuteUpdate("update revicion set estado='Desaprobado',"
                    + "comentario='" + comentario + "' where cod_revicion=" + cod.get(0));
            if (r) {
//                EnviarCorreosDesa();
                System.out.println("codigo de archivo = " + cod.get(1));
                control.ejecuteUpdate("update archivo set cod_estado=4 where "
                        + " cod_archivo=" + cod.get(1));

                FacesContext.getCurrentInstance().getExternalContext().redirect("menuAdm.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro", ""));

            }
        }

    }

}
