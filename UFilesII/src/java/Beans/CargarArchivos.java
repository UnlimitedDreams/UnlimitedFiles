/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Comite_Revicion;
import Modelo.Grupo;
import Modelo.Mail;
import Modelo.Sequence;
import Modelo.Usuario;
import Modelo.control;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import static java.util.concurrent.ThreadLocalRandom.current;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Microinformatica
 */
@ManagedBean
@SessionScoped
public class CargarArchivos implements Serializable {

    private String nombre;
    private String descripcion;
    private Date fecha;
    private Part file1;
    private String nombre_grupo;
    private int cod_grupo;
//    ProyectosDao pro = new ProyectosImple();
    ArrayList<CargarArchivos> archi = new ArrayList();
    ArrayList<Grupo> grup = new ArrayList();

    public CargarArchivos() {
    }

    public CargarArchivos(String nombre, String descripcion, Date fecha, Part file1) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.file1 = file1;
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

    public void cargar(FileUploadEvent event) {

    }

    public boolean conectar_revicion(int cod) throws ClassNotFoundException {
        control.conectar();
        boolean r = false;

        ArrayList<Comite_Revicion> comite = new ArrayList();
        control.ejecuteQuery("select comite.cod_comite,persona.correo from comite,usuario,persona where \n"
                + "persona.cedula=usuario.cedula and \n"
                + "usuario.cod_usuario=comite.cod_usuario and comite.estado='A' \n"
                + "              order by comite.nivel ASC");
        try {
            while (control.rs.next()) {
                comite.add(new Comite_Revicion(control.rs.getInt(1),
                        control.rs.getString(2)));
            }
            Date fecha = new Date();
            String estado = "";
            Comite_Revicion num = null;
            for (int i = 0; i < comite.size(); i++) {
                num = (Comite_Revicion) comite.get(i);
                int codigo_revicion = Sequence.seque("select max(cod_revicion) from revicion");
                if (i == 0) {
//                    Mail m = new Mail();
//                    System.out.println("Correooo - ----- " +num.getCorreo());
//                    m.SendMail(1, num.getCorreo(), "Revicion Proyetos", nombre);

                    estado = "Pendiente";
                } else {
                    estado = "En proceso";
                }
                System.out.println("insert into revicion values(" + codigo_revicion + "," + cod
                        + ",'" + fecha + "','" + fecha + "','" + estado + "'," + num.getCod() + ",'-')");
                r = control.ejecuteUpdate("insert into revicion values(" + codigo_revicion + "," + cod
                        + ",'" + fecha + "','" + fecha + "','" + estado + "'," + num.getCod() + ",'-')");

            }
            if (r) {
                System.out.println("Exito");
            } else {
                System.out.println("No Exito");

            }
            control.cerrarConexion();
        } catch (Exception ex) {

        }
        return r;
    }

    public void upload() throws IOException, ClassNotFoundException {
        System.out.println("file" + cod_grupo);
        Date fece = new Date();
        control.conectar();
        Usuario usu = new Usuario();
        usu = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        String ru = creardirectorios(cod_grupo);
        int codigo = Sequence.seque("select max(cod_archivo) from archivo");
        boolean r = control.ejecuteUpdate("insert into archivo values(" + codigo + ",'" + nombre + "','"
                + descripcion + "','" + fece + "','" + ru + "\\" + getFilename(file1) + "',1," + usu.getCedula() + ")");
        System.out.println("r-" + r);
        if (r) {
            System.out.println("r " + r);
            System.out.println("ruta");
            file1.write(ru + "\\" + getFilename(file1));
            int codigo_archi = Sequence.seque("select max(cod_archgrupo) from archi_grupo");
            boolean r2 = control.ejecuteUpdate("insert into archi_grupo values(" + codigo_archi + "," + cod_grupo + "," + codigo + ")");
            if (r2) {
                if (conectar_revicion(codigo) == true) {

                    FacesContext.getCurrentInstance().getExternalContext().redirect("menuUsuario.xhtml");

                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro", ""));
                }
//                boolean r3=control.ejecuteUpdate("insert into revicion values("+cod_revicion+",")
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro", ""));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro", ""));
        }
        control.cerrarConexion();
    }
//

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
//

    public String creardirectorios(int cod) throws ClassNotFoundException {
        String x = "";
        control.conectar();
        control.ejecuteQuery("select * from grupo where cod_grupo=" + cod);
        try {
            while (control.rs.next()) {
                x = control.rs.getString(2);
            }
        } catch (Exception ex) {

        }
        File directorio = new File("C:\\Unlimited\\" + x);
        if (directorio.mkdir()) {
            System.out.println("Se ha creado directorio");

        } else {

        }
        String ruta = "C:\\Unlimited\\" + x;
        return ruta;
    }

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre_grupo() {
        return nombre_grupo;
    }

    public void setNombre_grupo(String nombre_grupo) {
        this.nombre_grupo = nombre_grupo;
    }

    public int getCod_grupo() {
        return cod_grupo;
    }

    public void setCod_grupo(int cod_grupo) {
        this.cod_grupo = cod_grupo;
    }

    public ArrayList<CargarArchivos> getArchi() {
        return archi;
    }

    public void setArchi(ArrayList<CargarArchivos> archi) {
        this.archi = archi;
    }

    public ArrayList<Grupo> getGrup() {
        return grup;
    }

    public void setGrup(ArrayList<Grupo> grup) {
        this.grup = grup;
    }

}
