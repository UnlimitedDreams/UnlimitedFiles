package Control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Modelo.Rol;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/Crear_Rol"})
public class Crear_Rol extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    ArrayList<Rol> roles = new ArrayList();

    public boolean insertar_rol(String nombre) throws ClassNotFoundException {
        control.conectar();
        int codigo = Secuencias.Sequen("select max(cod_rol) from rol");
        boolean r = control.ejecuteUpdate("insert into rol values(" + codigo + ",'" + nombre + "')");
        return r;
    }

    public void traer_rol(String codigo) throws ClassNotFoundException {
        roles.clear();
        control.conectar();
        control.ejecuteQuery("select * from rol where cod_rol=" + codigo);
        String nombre = "";
        int cod = 0;
        try {
            while (control.rs.next()) {

                nombre = control.rs.getString(2);
                roles.add(new Rol(Integer.parseInt(codigo), nombre));
            }
        } catch (Exception ex) {

        }
    }

    public boolean verificar_rol(String codigo) throws ClassNotFoundException {
        control.conectar();
        control.ejecuteQuery("select * from rol,usuario_rol\n"
                + "where rol.cod_rol=usuario_rol.cod_rol and rol.cod_rol=" + codigo);

        boolean esta = false;
        try {
            while (control.rs.next()) {
                esta = true;
            }
        } catch (Exception ex) {

        }
        return esta;
    }
//
//    public void Borrar_rol(String codigo) throws ClassNotFoundException {
//
//    }

    public boolean delete(String codigo) throws ClassNotFoundException {
        control.conectar();
        boolean Rol_delete = false;
        if (verificar_rol(codigo) == true) {
            Rol_delete = false;
        } else {
            Rol_delete = control.ejecuteUpdate("delete from rol where cod_rol=" + codigo);
            System.err.println("rol  " + Rol_delete);

        }

        return Rol_delete;

    }

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        PrintWriter out = response.getWriter();

        response.setContentType("text/html;charset=UTF-8");
        String option = request.getParameter("opt");
        if (option.equalsIgnoreCase("1")) {
            Traer_rol();
            try {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println(""
//                        + "<script src=\"http://code.jquery.com/jquery.js\"></script>\n"
//                        + "<script src = \"bootstrap-3.3.4 / js / bootstrap.min.js\" ></script><link  href = \"bootstrap-3.3.4/css/bootstrap.css\"  rel = \"stylesheet\" ></link>\n"
//                        + "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js\"></script>\n"
//                        + "<script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js\"></script>"
                        + "<script src=\"Jquery/jquery-1.11.2.js\"></script>\n"
                        + "<script src=\"Js/alertify.min.js\"></script>\n");
                out.println("<script>"
                        + " function borrar(codigo) {\n"
                        + "alert(\"Seguro que desea borrar \");"
                        + "                $(document).ready(function() {\n"
                        + "                    var option = 3;\n"
                        + "                    $.post('Crear_Rol', {\n"
                        + "                        codigo_borrar: codigo,\n"
                        + "                        opt: option,\n"
                        + "                    }, function(responseText) {\n"
                        + "                        $(\"#Prueb\").html(responseText);\n"
                        + "                        $(\"#row\").load(\"Crud_roles.html\");\n"
                        + "                    });\n"
                        + "                });\n"
                        + "            }"
                        + " function update(codigo) {\n"
                        + "                $(document).ready(function() {\n"
                        + "                    var option = 4;\n"
                        + "                    $.post('Crear_Rol', {\n"
                        + "                        codigo_borrar: codigo,\n"
                        + "                        opt: option,\n"
                        + "                    }, function(responseText) {\n"
                        + "                        $(\"#Prueb\").html(responseText);\n"
                        + "                        $(\"#tabla\").load(\"Crud_roles.html\");\n"
                        + "                    });\n"
                        + "                });\n"
                        + "            }"
                        + "</script>");
                out.println("</head>");
                out.println("<body>");
//                out.println("<div class=\"container\">");
                out.println("<center>");
                out.println("<table  ");
                out.println("<tr><td><h3>Codigo</h3></td><td><h3>Rol</h3></td>"
                        + "<td><h3>Borrar</h3></td><td><h3>Actualizar</h3></td></tr>");
                Rol temp = null;
                for (int i = 0; i < roles.size(); i++) {
                    temp = (Rol) roles.get(i);
                    out.println("<tr>");
                    out.println("<td>" + temp.getCodigo() + "</td>");
                    out.println("<td>" + temp.getDescripcion() + "</td>");
                    out.println("<td><input type=\"button\" value=\"Borrar\" id=\"borr\" onclick=borrar(" + temp.getCodigo() + ")/></td>");
                    out.println("<td><input type=\"button\"  class=\"btn btn-info\" value=\"Actualizar\"  onclick=update(" + temp.getCodigo() + ")/></td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("</center>");
                out.println("<div id=\"Prueb\"> </div>");
//                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            } finally {
                out.close();
            }
        } else if (option.equalsIgnoreCase("2")) {
            String nombre = request.getParameter("Nombre");
            boolean r = insertar_rol(nombre);
            if (r) {
                try {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<script src=\"Jquery/jquery-1.11.2.js\"></script>\n"
                            + "        <script src=\"js/alertify.min.js\"></script>\n"
                            + "        <link rel=\"stylesheet\" href=\"Css/Notificacion/alertify.bootstrap.css\" />\n"
                            + "        <link rel=\"stylesheet\" href=\"Css/Notificacion/alertify.default.css\" />\n"
                            + "        <link rel=\"stylesheet\" href=\"Css/Notificacion/alertify.core.css\" />\n"
                            + "");
                    out.println("<script>$(document).ready(function() {\n"
                            + "                        alertify.success(\"Rol Guardado\");\n"
                            + "            });</script>");
                    out.println("</head>");
                    out.println("<body>");
                    //out.println("Ok");
                    out.println("</body>");
                    out.println("</html>");
                    // response.sendRedirect("Crud_roles.html");
                } finally {
                    out.close();
                }
            } else {
                try {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
//                    out.println("<script src=\"Jquery/jquery-1.11.2.js\"></script>\n"
//                            + "        <script src=\"Js/alertify.min.js\"></script>\n"
//                            + "        <link rel=\"stylesheet\" href=\"Css/alertify.bootstrap.css\" />\n"
//                            + "        <!-- include a theme, can be included into the core instead of 2 separate files -->\n"
//                            + "        <link rel=\"stylesheet\" href=\"Css/alertify.default.css\" />\n"
//                            + "        <link rel=\"stylesheet\" href=\"Css/alertify.core.css\" />\n"
//                            + "");
//                    out.println("<script>$(document).ready(function() {\n"
//                            //                    + "                alertify.prompt(\"Esto es un <b>prompt</b>, introduce un valor:\", function(e, str) {\n"
//                            //                    + "                    if (e) {\n"
//                            + " alertify.error(\"Has pulsado '\" + alertify.labels.cancel + \"'\");"
//                            //                    + "                    } else {\n"
//                            //                    + "                        alertify.error(\"Has pulsado '\" + alertify.labels.cancel + \"'\");\n"
//                            //                    + "                    }\n"
//                            //                    + "                });\n"
//                            + "            });</script>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("Ok");
                    out.println("</body>");
                    out.println("</html>");
                } finally {
                    out.close();
                }
            }
        } else if (option.equalsIgnoreCase("3")) {

            String cod = request.getParameter("codigo_borrar");
            System.out.println("Entro a borrar " + cod);
            boolean borrado = delete(cod);
            if (borrado == true) {
                try {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
//                    out.println("<script src=\"Jquery/jquery-1.11.2.js\"></script>\n"
//                            + "        <script src=\"Js/alertify.min.js\"></script>\n"
//                            + "        <link rel=\"stylesheet\" href=\"Css/alertify.bootstrap.css\" />\n"
//                            + "        <!-- include a theme, can be included into the core instead of 2 separate files -->\n"
//                            + "        <link rel=\"stylesheet\" href=\"Css/alertify.default.css\" />\n"
//                            + "        <link rel=\"stylesheet\" href=\"Css/alertify.core.css\" />\n"
//                            + "");
//                    out.println("<script>$(document).ready(function() {\n"
//                            //                    + "                alertify.prompt(\"Esto es un <b>prompt</b>, introduce un valor:\", function(e, str) {\n"
//                            //                    + "                    if (e) {\n"
//                            + " alertify.error(\"Has pulsado '\" + alertify.labels.cancel + \"'\");"
//                            //                    + "                    } else {\n"
//                            //                    + "                        alertify.error(\"Has pulsado '\" + alertify.labels.cancel + \"'\");\n"
//                            //                    + "                    }\n"
//                            //                    + "                });\n"
//                            + "            });</script>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("Borrado");
                    out.println("</body>");
                    out.println("</html>");
                } finally {
                    out.close();
                }
            } else {
                System.err.println("No borrado");

            }

        } else if (option.equalsIgnoreCase("4")) {
            String codigo = request.getParameter("codigo_borrar");
            traer_rol(codigo);
        }
        // Set response content type

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
            Logger.getLogger(Crear_Rol.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Crear_Rol.class
                    .getName()).log(Level.SEVERE, null, ex);
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
