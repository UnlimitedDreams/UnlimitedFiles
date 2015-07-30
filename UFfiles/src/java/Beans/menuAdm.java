/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Archivos;
import Modelo.Usuario;
import Modelo.control;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author Microinformatica
 */
@ManagedBean
@SessionScoped
public class menuAdm {

    private DefaultStreamedContent download;
    private String nombre;
    private String comentario;
    ArrayList<Archivos> tareas = new ArrayList();

    public menuAdm() {
    }

    public int Definir_usu(String x) throws ClassNotFoundException {
        System.out.println("entro");
        control.conectar();
        control.ejecuteQuery("    select * from usuario,persona\n"
                + "               where\n"
                + "               persona.cedula=usuario.cedula\n"
                + "               and persona.cedula=" + x);
        int valo = 0;
        try {
            while (control.rs.next()) {
                valo = 1;
            }
            if (valo == 1) {
                boolean r = Definir_usu2(x);
                if (r) {
                    valo = 2;
                }
            }
//            control.cerrarConexion();
        } catch (Exception ex) {

        }
        return valo;
    }

    public void setDownload(DefaultStreamedContent download) {
        this.download = download;
    }

    public DefaultStreamedContent getDownload() throws Exception {
        return download;
    }

    public void prueba() {
        System.out.println("------------");
    }

    public void prepDownload(String date) throws Exception {
        System.out.println("ruta " + date);
        File file = new File(date);
        InputStream input = new FileInputStream(file);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        setDownload(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
    }

    public boolean Definir_usu2(String x) throws ClassNotFoundException {
        control.conectar();
        control.ejecuteQuery("               select * from usuario,persona,comite\n"
                + "               where\n"
                + "               persona.cedula=usuario.cedula\n"
                + "               and comite.cod_usuario=usuario.cod_usuario\n"
                + "               and persona.cedula=" + x);
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

    public void recuperarNom() throws ClassNotFoundException {
        Usuario usu = new Usuario();
        usu = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        nombre = usu.getNombre() + " " + usu.getApellido() + " - " + usu.getRol_nom();
        control.conectar();
        tareas.clear();

        control.ejecuteQuery("select distinct revicion.cod_revicion,archivo.cod_archivo,titulo,fecha_subida \"Fecha inicial\",revicion.estado\n"
                + "                 ,archivo.ruta   \n"
                + "                 from archivo,revicion,comite,usuario,persona\n"
                + "                                 where\n"
                + "                                 usuario.cedula=persona.cedula and\n"
                + "                                 usuario.cod_usuario=comite.cod_usuario and \n"
                + "                                 comite.cod_comite=revicion.cod_comite  and\n"
                + "                                 archivo.cod_archivo=revicion.cod_archivo\n"
                + "                                  and persona.cedula="+usu.getCedula()+" and revicion.estado='Pendiente'");
        try {
            while (control.rs.next()) {
                tareas.add(new Archivos(control.rs.getInt(1),
                        control.rs.getInt(2),
                        control.rs.getString(3),
                        control.rs.getDate(4),
                        control.rs.getString(5), "Revisar",
                        control.rs.getString(6)));
            }
            control.cerrarConexion();
        } catch (Exception ex) {

        }
        

    }

    public void aprobar(int cod,int cod_archi) throws ClassNotFoundException, IOException {
        ArrayList<Integer>num=new ArrayList();
        num.add(cod);
        num.add(cod_archi);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Cod_revicion", num);
        FacesContext.getCurrentInstance().getExternalContext().redirect("JUNaprobarDesaprobar.xhtml");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Archivos> getTareas() {
        return tareas;
    }

    public void setTareas(ArrayList<Archivos> tareas) {
        this.tareas = tareas;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}
