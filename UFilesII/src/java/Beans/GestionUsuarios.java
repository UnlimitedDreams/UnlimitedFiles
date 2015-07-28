/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Sequence;
import Modelo.Usuario;
import Modelo.control;
import com.sun.jmx.snmp.BerDecoder;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author USR_Toshiba
 */
@ManagedBean
@SessionScoped
public class GestionUsuarios implements Serializable {

    private String nickname;
    private String contraseña;
    private int rol;
    private int cedula;
    private String nombre;
    private String apellido;
    private Date fecha_nacimiento;
    private String sexo;
    private int grupo;
    private String correo;
    ArrayList<Usuario> usuarios = new ArrayList();

    public GestionUsuarios() {
    }

    public void listaUsuario() throws ClassNotFoundException {
        usuarios.clear();
        control.conectar();
        control.ejecuteQuery("select persona.cedula,nombre,apellido,rol.descripcion,usuario.nick_name\n"
                + ",persona.correo,fecha_nacimiento,usuario.password\n"
                + "from persona,usuario,usuariorol,rol\n"
                + "where\n"
                + "persona.cedula=usuario.cedula and\n"
                + "usuariorol.cod_usuario=usuario.cod_usuario and\n"
                + "usuariorol.cod_rol=rol.cod_rol and\n"
                + "usuario.estado='A'");
        try {
            while (control.rs.next()) {
                usuarios.add(new Usuario(control.rs.getString(1),
                        control.rs.getString(2),
                        control.rs.getString(3),
                        control.rs.getString(4),
                        control.rs.getString(5),
                        control.rs.getString(6),
                        control.rs.getString(8),
                        control.rs.getDate(7)));
            }
        } catch (Exception ex) {

        }

    }

    public void borrar(String cedula) throws ClassNotFoundException, IOException {
        control.conectar();
        boolean r = control.ejecuteQuery("update usuario set estado='I' where cedula=" + cedula);
        if (r) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("menuAdm.xhtml");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro", ""));

        }
    }

    public void update(Usuario u) throws IOException {
        System.out.println("-- " + u.toString());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Update_Usuario", u);
        FacesContext.getCurrentInstance().getExternalContext().redirect("USUpdate.xhtml");
    }

    public void insertarDatos() throws ClassNotFoundException, IOException {
        System.err.println("----------ENTRO-------");
        control.conectar();
        int idUsuario = Sequence.seque("select Max(cod_usuario)  from usuario");
        int idUsuarioGrupo = Sequence.seque("SELECT Max(usuariogrupo) from usuariogrupo");
        int idUsuario_rol = Sequence.seque("select Max(cod_usuariorol)  from usuariorol");

        String sex = "";
        if (sexo.equals("masculino")) {
            sex = "M";
        } else if (sexo.equals("femenino")) {
            sex = "F";
        }

        boolean persona = control.ejecuteUpdate("insert into persona values(" + cedula + ",'" + nombre + "','" + apellido + "','1990-7-30','" + sex + "','A','" + correo + "' );");

        if (persona) {

            boolean usu = control.ejecuteUpdate("insert into usuario values(" + idUsuario + ",'" + nickname + "','" + contraseña + "','A'," + cedula + " )");

            if (usu) {
                boolean r = false;
                r = control.ejecuteUpdate("insert into usuariorol values(" + idUsuario_rol + "," + rol + "," + idUsuario + " );");
                r = control.ejecuteUpdate("insert into usuarioGrupo values(" + idUsuarioGrupo + "," + grupo + "," + idUsuario + " );");
                if (r) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("menuAdm.xhtml");
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ""));
                }
            }
        }
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
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

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "GestionUsuarios{" + "nickname=" + nickname + ", contrase\u00f1a=" + contraseña + ", rol=" + rol + ", cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", fecha_nacimiento=" + fecha_nacimiento + ", sexo=" + sexo + ", grupo=" + grupo + ", correo=" + correo + '}';
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
