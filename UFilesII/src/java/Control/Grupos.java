package Control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Modelo.Grupo;
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
 * @author USR_Toshiba
 */
@WebServlet(urlPatterns = {"/Grupos"})
public class Grupos extends HttpServlet {
ArrayList<Grupo> grup=new ArrayList();
 
    public boolean insertar_Grupo(String nombre) throws ClassNotFoundException {
        control.conectar();
        int codigo = Secuencias.Sequen("select max(idgrupo) from grupo");
        boolean r = control.ejecuteUpdate("insert into grupo values(" + codigo + ",'" + nombre + "')");
        return r;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String descrip = request.getParameter("Nombre");
        boolean r = insertar_Grupo(descrip);

        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<script src=\"Jquery/jquery-1.11.2.js\"></script>\n"
                    + "        <script src=\"Js/alertify.min.js\"></script>\n"
                    + "        <link rel=\"stylesheet\" href=\"Css/alertify.bootstrap.css\" />\n"
                    + "        <!-- include a theme, can be included into the core instead of 2 separate files -->\n"
                    + "        <link rel=\"stylesheet\" href=\"Css/alertify.default.css\" />\n"
                    + "        <link rel=\"stylesheet\" href=\"Css/alertify.core.css\" />\n"
                    + "");
            out.println("<script>$(document).ready(function() {\n"
//                    + "                alertify.prompt(\"Esto es un <b>prompt</b>, introduce un valor:\", function(e, str) {\n"
//                    + "                    if (e) {\n"
                    + "                        alertify.success(\"Grupo Guardado\");\n"
//                    + "                    } else {\n"
//                    + "                        alertify.error(\"Has pulsado '\" + alertify.labels.cancel + \"'\");\n"
//                    + "                    }\n"
//                    + "                });\n"
                    + "            });</script>");
            out.println("</head>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
            Logger.getLogger(Grupos.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Grupos.class.getName()).log(Level.SEVERE, null, ex);
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
