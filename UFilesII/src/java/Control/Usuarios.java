package Control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Modelo.Grupo;
import Modelo.Rol;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USR_Toshiba
 */
@WebServlet(urlPatterns = {"/Usuarios"})
public class Usuarios extends HttpServlet {

    ArrayList<Rol> roles = new ArrayList();
    ArrayList<Grupo> Grupos = new ArrayList();
    ArrayList<Usuario> Mis_usuarios = new ArrayList();

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

    public void Traer_Usuarios() throws ClassNotFoundException {
        Mis_usuarios.clear();
        control.conectar();
        control.ejecuteQuery("select * from persona where estado='Activo'");
        int cod = 0;
        String nom = "", apellido = "", sexo = "";
        Date fecha = null;
        try {
            while (control.rs.next()) {
                cod = control.rs.getInt(1);
                nom = control.rs.getString(2);
                fecha = control.rs.getDate(3);
                apellido = control.rs.getString(4);
                sexo = control.rs.getString(5);
                Mis_usuarios.add(new Usuario("" + cod, nom, "" + fecha, apellido, sexo));
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
                + apellido + "','" + sexo + "','Activo')");
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
        PrintWriter out = response.getWriter();
        HttpSession usu = request.getSession(true);
        if (option.equalsIgnoreCase("1")) {
            System.out.println("entro opt 1");
            Traer_rol();
            Traer_Grupos();
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

        } else if (option.equalsIgnoreCase("3")) {
            Traer_Usuarios();
            try {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println(""
                        + "<script src=\"Jquery/jquery-1.11.2.js\"></script>\n");
                out.println("<script>"
                        + " function borrar(codigo) {\n"
                        + "alert(\"Seguro que desea borrar \");"
                        + "                $(document).ready(function() {\n"
                        + "                    var option = 4;\n"
                        + "                    $.post('Usuarios', {\n"
                        + "                        codigo_borrar: codigo,\n"
                        + "                        opt: option,\n"
                        + "                    }, function(responseText) {\n"
                        + "                        $(\"#Prueb_usu\").html(responseText);\n"
                        + "                        $(\"#tabla_usuarios\").load(\"Crud_usuarios.html\");\n"
                        + "                    });\n"
                        + "                });\n"
                        + "            }"
                        + " function update(codigo) {\n"
                        + "                $(document).ready(function() {\n"
                        + "                    var option = 5;\n"
                        + "                    $.post('Usuarios', {\n"
                        + "                        codigo_borrar: codigo,\n"
                        + "                        opt: option,\n"
                        + "                    }, function(responseText) {\n"
                        + "                         $(\"#tabla_usuarios\").hide();"
                        + "                        $(\"#Updat_usu\").html(responseText);\n"
                        + "                    });\n"
                        + "                });\n"
                        + "            }"
                        + "</script>");
                out.println("</head>");
                out.println("<body>");
                out.println("<center>");
                out.println("<br><br><br>");
                out.println("<table>");
                out.println("<tr><td>cedula</td><td>nombre</td><td>apellido</td><td>fecha</td><td>sexo</td><td>Borrar</td>"
                        + "<td>Actualizar</td><tr>");
                Usuario temp = null;
                for (int i = 0; i < Mis_usuarios.size(); i++) {
                    temp = (Usuario) Mis_usuarios.get(i);
                    out.println("<tr>");
                    out.println("<td>" + temp.getCedula() + "</td>");
                    out.println("<td>" + temp.getNombre() + "</td>");
                    out.println("<td>" + temp.getApellido() + "</td>");
                    out.println("<td>" + temp.getFecha_naci() + "</td>");
                    out.println("<td>" + temp.getSexo() + "</td>");
                    out.println("<td><input type=\"button\" class=\"btn btn-info\" value=\"Borrar\" id=\"borr\" onclick=borrar(" + temp.getCedula() + ")></td>");
                    out.println("<td><input type=\"button\"  class=\"btn btn-info\" value=\"Actualizar\"  onclick=update(" + temp.getCedula() + ")></td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("</center>");
                out.println("<div id=\"Prueb_usu\"> </div>");
                out.println("</body>");
                out.println("</html>");
            } catch (Exception ex) {

            }
        } else if (option.equalsIgnoreCase("4")) {
            String cod = request.getParameter("codigo_borrar");
            System.out.println("codigo es" + cod);
            control.conectar();
            control.ejecuteUpdate("update persona set estado='Inactivo' where cedula=" + cod);
            control.cerrarConexion();
        } else if (option.equalsIgnoreCase("5")) {
            String cod = request.getParameter("codigo_borrar");
            System.err.println("codigo es " + cod);
            control.ejecuteQuery("select * from persona where cedula=" + cod);
            String nombre = "", apellido = "", Sexo = "";
            Date fec = null;
            try {
                while (control.rs.next()) {
                    nombre = control.rs.getString(2);
                    apellido = control.rs.getString(4);
                }
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println(""
                        + "<script src=\"Jquery/jquery-1.11.2.js\"></script>\n");
                out.println("<script>"
                        + "function upda(codigo){"
                        + "                $(document).ready(function() {\n"
                        + "                    var option = 6;\n"
                        + "                    var nombre = $(\"#nom\").val();\n"
                        + "                    var apellido = $(\"#ape\").val();\n"
                        + "                    $.post('Usuarios', {\n"
                        + "                        codigo_borrar: codigo,\n"
                        + "                          nom:nombre,"
                        + "                         ape:apellido,"
                        + "                        opt: option,\n"
                        + "                    }, function(responseText) {\n"
                        + "                        $(\"#tabla_usuarios\").html(responseText);\n"
                        + "                        $(\"#row\").load(\"Curd_usuarios.html\");\n"
                        + "                    });\n"
                        + "                });\n"
                        + "}"
                        + "</script>");
                out.println("</head>");
                out.println("<body>");
                out.println("<form>");
                out.println("<table>");
                out.println("<tr><td>Nombre</td><td>Apellido</td></tr>");
                out.println("<td><input type=\"text\" value=" + nombre + " id=\"nom\"> </td>");
                out.println("<td><input type=\"text\" value=" + apellido + " id=\"ape\"> </td>");
                out.println("</table>");
                out.println("<br><br>");
                out.println("<input type=\"button\" onclick=upda(" + cod + ") value=\"Update\"> ");
                out.println("<a href='Crud_usuarios.html'>Atras</a> ");
                out.println("<div id=\"una\"> </div>");
                out.println("</form>");
            } catch (Exception ex) {

            }
        } else if (option.equalsIgnoreCase("6")) {
            System.err.println("Entroo a update ");
            String nom = request.getParameter("nom");
            String ape = request.getParameter("ape");
            String cod = request.getParameter("codigo_borrar");
            control.ejecuteUpdate("update persona set nombre='" + nom + "',apellido='" + ape + "' where cedula=" + cod);
            response.sendRedirect("Crud_usuarios.html");
            try {
                out.println("ok");
            } catch (Exception ex) {

            }
        } else if (option.equalsIgnoreCase("7")) {
            String codigo = null;
            codigo = request.getParameter("codi");
            System.out.println("Entro a update " + codigo);
            control.conectar();
            control.ejecuteQuery("select * from persona where cedula=" + codigo);
            String nom = "", ape = "";
            try {
                while (control.rs.next()) {
                    nom = control.rs.getString(2);
                    ape = control.rs.getString(4);
                }
                System.err.println("traje  " + nom + " y " + ape);
                usu.setAttribute("Usuario_nombre", nom);
                usu.setAttribute("Usuario_Apellido", ape);

            } catch (Exception ex) {

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
