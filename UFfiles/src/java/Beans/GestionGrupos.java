/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author Microinformatica
 */
import Modelo.control;
import Modelo.grupo_List;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class GestionGrupos implements Serializable {

    ArrayList<grupo_List> lista = new ArrayList();
    private int cod;
    private String nombreGrupo;
    private String estado;

    public ArrayList<grupo_List> getLista() {
        return lista;
    }

    public void setLista(ArrayList<grupo_List> lista) {
        this.lista = lista;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    @Override
    public String toString() {
        return "GestionGrupos{" + "nombreGrupo=" + nombreGrupo + '}';
    }

    public void crearGrupo() throws ClassNotFoundException {
        control.conectar();
        int codigoGrupo = codigoGrupo();
        boolean enviar = control.ejecuteUpdate("insert into grupo values(" + codigoGrupo + ",'" + nombreGrupo + "','true');");

        if (enviar) {
            System.err.println("----inserto----");
        } else {
            System.err.println("error al insert");
        }

    }

    public int codigoGrupo() throws ClassNotFoundException {

        control.conectar();
        int codgru = 0;
        control.ejecuteQuery("select Max(cod_grupo) from grupo");
        try {
            while (control.rs.next()) {
                codgru = control.rs.getInt(1);

            }

        } catch (Exception ex) {

        }
        return codgru + 1;
    }

    public void opcionesGrupo() throws ClassNotFoundException {
        control.conectar();
        boolean grupos = control.ejecuteQuery("select * from grupo");
        lista.clear();

        try {

            while (control.rs.next()) {
                lista.add(new grupo_List(control.rs.getInt(1), control.rs.getString(2), control.rs.getString(3)));
            }
        } catch (Exception ex) {
        }

    }
    
     public void eliminarGrupo(int x) throws ClassNotFoundException {

        control.conectar();
        System.out.println("ENTRO");
        boolean delete = control.ejecuteUpdate("delete from grupo where cod_grupo=" + x + ";");
        System.out.println("codigo =" + x + "Eliminado");
        if (delete) {
            System.out.println("elimino el grupo");
        } else {
            System.err.println("Error al eliminar el grupo indicado");
        }
    }
}
