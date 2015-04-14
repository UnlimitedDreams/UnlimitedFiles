/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Documento;
import Modelo.Grupo;
import java.io.File;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sebastian
 */
@WebServlet(name = "MisGrupos", urlPatterns = {"/MisGrupos"})
public class MisGrupos extends HttpServlet {

    ArrayList<Grupo> grup = new ArrayList();
    ArrayList<Documento> doc = new ArrayList();

    public void cargar_grupos(String id_usu) throws ClassNotFoundException {
        control.conectar();
        grup.clear();
        control.ejecuteQuery("select grupo.* from grupo,usuario,usuariogrupo\n"
                + "where usuario.idusuario=usuariogrupo.idusuario and \n"
                + "usuariogrupo.idgrupo=grupo.idgrupo and \n"
                + "usuario.idusuario=" + id_usu);
        int cod = 0;
        String descrip = "";
        try {
            while (control.rs.next()) {
                cod = control.rs.getInt(1);
                descrip = control.rs.getString(2);
                grup.add(new Grupo(cod, descrip));
            }
        } catch (Exception ex) {

        }
    }

    public void cargar_doc(String cod) throws ClassNotFoundException {
        control.conectar();
        doc.clear();
        control.ejecuteQuery("select rutas.nombrearchivo,rutas.descripcion,rutas.fecha,rutas.ruta,rutas.cod_rutas from grupo,rutas,grupodoc\n"
                + "where grupo.idgrupo=grupodoc.idgrupo and\n"
                + "rutas.cod_rutas=grupodoc.cod_rutas and \n"
                + "grupo.idgrupo=" + cod + "order by fecha ASC");
        System.err.println("select rutas.nombrearchivo,rutas.descripcion,rutas.fecha,rutas.ruta,rutas.cod_rutas from grupo,rutas,grupodoc\n"
                + "where grupo.idgrupo=grupodoc.idgrupo and\n"
                + "rutas.cod_rutas=grupodoc.cod_rutas and \n"
                + "grupo.idgrupo=" + cod + "order by fecha ASC ");
        String nombre = "", descrip = "", ruta = "", fecha = "";
        int codigo = 0;
        try {
            while (control.rs.next()) {
                nombre = control.rs.getString(1);
                descrip = control.rs.getString(2);
                fecha = control.rs.getString(3);
                ruta = control.rs.getString(4);
                codigo = control.rs.getInt(5);
                doc.add(new Documento(codigo, nombre, descrip, fecha, ruta));

            }
        } catch (Exception ex) {

        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession s = request.getSession(true);
        String opt = request.getParameter("opt");
        PrintWriter out = response.getWriter();

        if (opt.equalsIgnoreCase("1")) {
            String idUsu = (String) s.getAttribute("idUsuario");
            cargar_grupos(idUsu);
            try {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("        <script src=\"Jquery/jquery-1.11.2.js\"></script>\n"
                        + "");
                out.println("        <script>\n"
                        + "   function  Codigo(cod) {\n"
                        + "                $(document).ready(function() {\n"
                        + "                    var option=2;\n"
                        + "                    $.post('MisGrupos', {\n"
                        + "                        opt: option,\n"
                        + "                        codi:cod\n"
                        + "                    }, function(responseText) {\n"
                        + "                        $('#Tabla_grupo').html(responseText);\n"
                        + "                    });\n"
                        + "                });\n"
                        + "            }\n"
                        + "   "
                        + "        </script>");
                out.println("</head>");
                out.println("<body>");
                out.println("<ul>");
                Grupo temp = null;
                for (int i = 0; i < grup.size(); i++) {
                    temp = (Grupo) grup.get(i);

                    out.println("<li> <input type=\"button\" value=" + temp.getNombre() + "  onclick=\"Codigo(" + temp.getCodigo() + ")\"></li>");
                }
                out.println("</ul>");
                out.println("</body>");
                out.println("</html>");
            } finally {
                out.close();
            }
        } else if (opt.equalsIgnoreCase("2")) {
            String codigo_grupo = request.getParameter("codi");
            System.err.println("Recivi el codigo " + codigo_grupo);
            cargar_doc(codigo_grupo);
            try {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("<center>");
                out.println("<table class=\"table table-bordered\" >");
                out.println("<tr>");
                out.println("<td><h3>Nombre Archivo</h3></td><td><h3>Descripcion</h3></td> <td><h3>Fecha</h3></td><td><h3>Descargar</h3></td>");
                out.println("</tr>");
                Documento temp = null;
                for (int i = 0; i < doc.size(); i++) {
                    temp = (Documento) doc.get(i);
                    out.println("<tr>");
                    out.println("<td>" + temp.getNombre() + "</td>");
                    out.println("<td>" + temp.getDescrip() + "</td>");
                    out.println("<td>" + temp.getFecha() + "</td>");
                    out.println("<td><a href=" + temp.getRuta() + " class=\"btn btn-danger\" >descargar</a></td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("</center>");
                out.println("</body>");
                out.println("</html>");
            } finally {
                out.close();
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
            Logger.getLogger(MisGrupos.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MisGrupos.class.getName()).log(Level.SEVERE, null, ex);
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
