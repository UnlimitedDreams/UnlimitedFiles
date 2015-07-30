/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Archivos;
import Modelo.Grupo;
import Modelo.Usuario;
import Modelo.control;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author USR_Toshiba
 */
@ManagedBean
@SessionScoped
public class ArchivosList implements Serializable {

    ArrayList<Archivos> archi = new ArrayList();
    private DefaultStreamedContent download;
    ArrayList<Grupo> grup = new ArrayList();
    private int codigo_grupo;
    private String nombre_grupo;
    private String palabra;

    public ArchivosList() {
    }

    public void cargarGrupos() throws ClassNotFoundException {
        grup.clear();
        System.out.println("--------");
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
            System.out.println("Error " + ex.getMessage());
        }
    }

public void cargarTodosGrupos() throws ClassNotFoundException {
        grup.clear();
        System.out.println("--------");
        control.conectar();
        Usuario usu = new Usuario();
        usu = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        control.ejecuteQuery("select * from grupo where estado='A'");

        try {
            while (control.rs.next()) {
                grup.add(new Grupo(control.rs.getInt(1), control.rs.getString(2)));
            }
            control.cerrarConexion();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }

    }
        

    public void buscar() throws ClassNotFoundException {
        System.out.println("palabra " + palabra);
        control.conectar();
        archi.clear();
        control.ejecuteQuery("      select * from archivo,archi_grupo where\n"
                + "                archivo.cod_archivo=archi_grupo.cod_archivo\n"
                + "                and titulo ILIKE ('%" + palabra + "%') and cod_estado=1");

        try {
            while (control.rs.next()) {
                archi.add(new Archivos(control.rs.getInt(1), control.rs.getString(2), control.rs.getString(3),
                        control.rs.getDate(4), control.rs.getString(5)));
            }

            control.cerrarConexion();
            FacesContext.getCurrentInstance().getExternalContext().responseReset();
            System.out.println("---- " + archi.size());
        } catch (Exception ex) {
            System.out.println("error " + ex.getMessage());
        }
    }

    public void listar() throws ClassNotFoundException {
        System.out.println("entroooooo ");
        control.conectar();
        archi.clear();
        control.ejecuteQuery("select * from archivo,archi_grupo where\n"
                + "archivo.cod_archivo=archi_grupo.cod_archivo"
                + " and archi_grupo.cod_grupo=" + codigo_grupo + " and cod_estado=1");

        try {
            while (control.rs.next()) {
                archi.add(new Archivos(control.rs.getInt(1), control.rs.getString(2), control.rs.getString(3),
                        control.rs.getDate(4), control.rs.getString(5)));
            }
            control.cerrarConexion();
            FacesContext.getCurrentInstance().getExternalContext().responseReset();

            System.out.println("---- " + archi.size());
        } catch (Exception ex) {
            System.out.println("error " + ex.getMessage());
        }
//        FacesContext.getCurrentInstance().getExternalContext().responseReset();

    }

    public void listar2() throws ClassNotFoundException {
        System.out.println("entroooooo ");
        control.conectar();
        archi.clear();
        control.ejecuteQuery("select * from archivo,archi_grupo where\n"
                + "archivo.cod_archivo=archi_grupo.cod_archivo"
                + " and archi_grupo.cod_grupo=" + codigo_grupo);

        try {
            while (control.rs.next()) {
                archi.add(new Archivos(control.rs.getInt(1), control.rs.getString(2), control.rs.getString(3),
                        control.rs.getDate(4), control.rs.getString(5)));
            }
            control.cerrarConexion();
            System.out.println("---- " + archi.size());
        } catch (Exception ex) {
            System.out.println("error " + ex.getMessage());
        }
        FacesContext.getCurrentInstance().getExternalContext().responseReset();

    }

    public void setDownload(DefaultStreamedContent download) {
        this.download = download;
    }

    public DefaultStreamedContent getDownload() throws Exception {
        return download;
    }

    public void prepDownload(String date) throws Exception {
        System.out.println("file " + date);
        File file = new File(date);
        InputStream input = new FileInputStream(file);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        setDownload(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
    }

    public ArrayList<Archivos> getArchi() {
        return archi;
    }

    public void setArchi(ArrayList<Archivos> archi) {
        this.archi = archi;
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

    public String getNombre_grupo() {
        return nombre_grupo;
    }

    public void setNombre_grupo(String nombre_grupo) {
        this.nombre_grupo = nombre_grupo;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

}
