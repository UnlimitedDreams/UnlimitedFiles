package Control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Modelo.Grupo;
import Modelo.Rol;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USR_Toshiba
 */
@WebServlet(urlPatterns = {"/Usuarios"})
public class Usuarios extends HttpServlet {

    ArrayList<Rol> roles = new ArrayList();
    ArrayList<Grupo> Grupos = new ArrayList();

    public void Traer_rol() throws ClassNotFoundException {
        roles.clear();
        control.conectar();
        control.ejecuteQuery("select * from rol");
        int cod = 0;
        String nom = "";
        try {
            while (control.rs.next()) {
                cod = control.rs.getInt(1);
                nom = control.rs.getString(2);
                roles.add(new Rol(cod, nom));
            }
        } catch (Exception ex) {

        }
    }

    public void Traer_Grupos() throws ClassNotFoundException {
        Grupos.clear();
        control.conectar();
        control.ejecuteQuery("select * from Grupo");
        int cod = 0;
        String nom = "";
        try {
            while (control.rs.next()) {
                cod = control.rs.getInt(1);
                nom = control.rs.getString(2);
                Grupos.add(new Grupo(cod, nom));
            }
        } catch (Exception ex) {

        }
    }

    public boolean insertar_usuario(String Nick, String pass, String rol, String cedula, String nombre,
            String apellido, String fecga, String sexo, String Gruposs) throws ClassNotFoundException {
        control.conectar();
        int codigo = Secuencias.Sequen("select max(idusuario) from usuario");
        int codigo_usuRol = Secuencias.Sequen("select max(idusuariorol) from usuario_rol");
        int codigo_usuGrupo = Secuencias.Sequen("select max(idusuariogrupo) from usuariogrupo");
        boolean ok = false;
        boolean r = control.ejecuteUpdate("insert into persona values('" + cedula + "','" + nombre + "','" + fecga + "','"
                + apellido + "','" + sexo + "')");
        if (r) {
            System.out.println("inserto persona");
            boolean per = control.ejecuteUpdate("insert into usuario values(" + codigo + ",'" + Nick + "','"
                    + pass + "','" + cedula + "')");
            if (per) {
                System.out.println("Inserto usuario");
                String rol2[] = rol.split("-");
                boolean usurol = control.ejecuteUpdate("insert into usuario_rol values(" + codigo_usuRol + ","
                        + codigo + ",'" + rol2[0] + "')");
                if (usurol) {
                    System.out.println("Inserto grupo");
                    String grupo2[] = Gruposs.split("-");
                    boolean usuGru = control.ejecuteUpdate("insert into usuariogrupo values(" + codigo_usuGrupo + ","
                            + codigo + ",'" + grupo2[0] + "')");
                    ok = true;
                }
            }
        }
        return ok;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        String option = request.getParameter("opt");
        if (option.equalsIgnoreCase("1")) {
            Traer_rol();
            Traer_Grupos();
            PrintWriter out = response.getWriter();
            try {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println(" <script src=\"Jquery/jquery-1.11.2.js\"></script>\n"
                        + "        <script>\n"
                        + "            $(document).ready(function() {\n"
                        + "                $(\"#Enviar\").click(function() {\n"
                        + "                    var option = 2;\n"
                        + "                    var Nick = $(\"#Nick\").val();\n"
                        + "                    var pass = $(\"#pas\").val();\n"
                        + "                    var rol = $(\"#Rol\").val();\n"
                        + "                    var cedula = $(\"#ced\").val();\n"
                        + "                    var nombre = $(\"#nom\").val();\n"
                        + "                    var apellido = $(\"#Ape\").val();\n"
                        + "                    var fecha = $(\"#fecha\").val();\n"
                        + "                    var sexo = $(\"#sexx\").val();\n"
                        + "                    var Gruposs = $(\"#Gripos\").val();\n"
                        + "                    $.post('Usuarios', {\n"
                        + "                        opt: option,\n"
                        + "                        Nic: Nick,\n"
                        + "                        pas: pass,\n"
                        + "                        Roles: rol,\n"
                        + "                        ced: cedula,\n"
                        + "                        nom: nombre,\n"
                        + "                        Ape: apellido,\n"
                        + "                        fechas: fecha,\n"
                        + "                        sexx: sexo,\n"
                        + "                        Gripos: Gruposs,\n"
                        + "                    }, function(responseText) {\n"
                        + "                        $('#Respu').html(responseText);\n"
                        + " $(\"#wrapper\").load(\"typography.html\");"
                        + "                    });\n"
                        + "                });\n"
                        + "\n"
                        + "            });\n"
                        + "        </script>");
                out.println("</head>");
                out.println("<body>");
                out.println("<center>");
                out.println("<br><br><br>");
                out.println(" <table>\n"
                        + "                <tr>\n"
                        + "                    <td><label>NickName</label></td>\n"
                        + "                    <td><input type=\"text\" id=\"Nick\"></td>\n"
                        + "                </tr>\n"
                        + "                <tr>\n"
                        + "          \n"
                        + "                    <td><label>Password</label></td>\n"
                        + "                    <td><input type=\"password\" id=\"pas\"></td>\n"
                        + "                </tr>\n"
                        + "                </tr>\n"
                        + "                <tr>\n"
                        + "                    <td><label>Cedula</label></td>\n"
                        + "                    <td><input type=\"text\" id=\"ced\"></td>\n"
                        + "                </tr>\n"
                        + "                <tr>\n"
                        + "                    <td><label>Nombre</label></td>\n"
                        + "                    <td><input type=\"text\" id=\"nom\"></td>\n"
                        + "                </tr>\n"
                        + "                <tr>\n"
                        + "                    <td><label>Apellido</label></td>\n"
                        + "                    <td><input type=\"text\" id=\"Ape\"></td>\n"
                        + "                </tr>\n"
                        + "                <tr>\n"
                        + "                    <td><label>Fecha Nacimiento</label></td>\n"
                        + "                    <td><input type=\"date\" id=\"fecha\"></td>\n"
                        + "                </tr>\n"
                        + "                <tr>\n"
                        + "                    <td><label>Rol</label></td>\n");
                out.println("         <td><select id=\"Rol\">");
                Rol temp = null;
                for (int i = 0; i < roles.size(); i++) {
                    temp = (Rol) roles.get(i);
                    out.println("  <option value=\"" + temp.getCodigo() + "-" + temp.getDescripcion() + "\" >" + temp.getDescripcion() + "</option>");
                }
                out.println(" </select></td>\n"
                        + "                </tr>\n"
                        + "                <tr>\n"
                        + "                    <td><label>Grupo</label></td>\n"
                        + "                    <td><select id=\"Gripos\">\n");
                Grupo temp2 = null;
                for (int i = 0; i < Grupos.size(); i++) {
                    temp2 = (Grupo) Grupos.get(i);
                    out.println("<option value=\"" + temp2.getCodigo() + "-" + temp2.getNombre() + "\">" + temp2.getNombre() + "</option>");
                }

                out.println("  </select>\n"
                        + "                    </td>\n"
                        + "                </tr>\n"
                        + "                <tr>\n"
                        + "                    <td><label>Sexo</label></td>\n"
                        + "                    <td>M<input type=\"checkbox\" id=\"sexx\" value=\"M\">\n"
                        + "                        F<input type=\"checkbox\" id=\"sexx\" value=\"F\"></td>\n"
                        + "                </tr>\n"
                        + "            </table>\n"
                        + "  <input type=\"button\" id=\"Enviar\" value=\"CREAR\">"
                        + " <div id=\"Respu\"></div>");
                out.println("</center>");

                out.println("</body>");
                out.println("</html>");
            } finally {
                out.close();
            }
        } else if (option.equalsIgnoreCase("2")) {
            String Nick = request.getParameter("Nic");
            String pass = request.getParameter("pas");
            String rol = request.getParameter("Roles");
            String cedula = request.getParameter("ced");
            String nombre = request.getParameter("nom");
            String apellido = request.getParameter("Ape");
            String fecga = request.getParameter("fechas");
            String sexo = request.getParameter("sexx");
            String Gruposs = request.getParameter("Gripos");
            System.err.println("nick " + Nick + " rol " + rol + "sexo " + sexo + " grupo " + Gruposs);
            boolean ok = insertar_usuario(Nick, pass, rol, cedula, nombre, apellido, fecga, sexo, Gruposs);
            if (ok) {
                System.out.println("ok");
                PrintWriter out = response.getWriter();

                try {
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<script src=\"Jquery/jquery-1.11.2.js\"></script>\n"
                            + "        <script src=\"js/alertify.min.js\"></script>\n"
                            + "        <link rel=\"stylesheet\" href=\"css/alertify.bootstrap.css\" />\n"
                            + "        <link rel=\"stylesheet\" href=\"css/alertify.default.css\" />\n"
                            + "        <link rel=\"stylesheet\" href=\"css/alertify.core.css\" />\n"
                            + "");
                    out.println("<script>$(document).ready(function() {\n"
                            + "                        alertify.success(\"Insertado con Exito\");\n"
                            + "            });</script>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("</body>");
                    out.println("</html>");
                } finally {
                    out.close();
                }
            } else {
                System.err.println("No ok");
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
