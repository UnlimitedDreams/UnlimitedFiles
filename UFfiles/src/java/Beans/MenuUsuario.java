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
public class MenuUsuario {

    private DefaultStreamedContent download;
    private String nombre;
    private String comentario;
    ArrayList<Archivos> tareas = new ArrayList();

    public MenuUsuario() {
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

    public void recuperarNom() throws ClassNotFoundException {
        Usuario usu = new Usuario();
        usu = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        nombre = usu.getNombre() + " " + usu.getApellido() + " - " + usu.getRol_nom();
        control.conectar();
        tareas.clear();
//        
        control.ejecuteQuery("select distinct archivo.cod_archivo,titulo,archivo.descripcion,fecha_subida \"Fecha inicial\",\n"
                + "estado.descripcion \"Estado\",archivo.ruta from archivo,estado where\n"
                + "              estado.cod_estado=archivo.cod_estado and \n"
                + "               archivo.cedula=" + usu.getCedula() + " order by "
                + "  archivo.fecha_subida DESC");
        try {
            while (control.rs.next()) {
                tareas.add(new Archivos(control.rs.getInt(1),
                        control.rs.getString(2),
                        control.rs.getString(3),
                        control.rs.getDate(4),
                        control.rs.getString(6),
                        control.rs.getString(5)));
            }
            System.out.println("tareas " + tareas.size());
        } catch (Exception ex) {

        }

    }

    public void detalle(int x) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Codigo_archivo", x);
        FacesContext.getCurrentInstance().getExternalContext().redirect("MenuDetalle_Archivo.xhtml");
    }

    public void aprobar(int cod, int cod_archi) throws ClassNotFoundException, IOException {
        ArrayList<Integer> num = new ArrayList();
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
