package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Control.control;
import java.util.ArrayList;
import Modelo.Usuario;

public final class prueba_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

ArrayList<Usuario> Mis_usuarios = new ArrayList();
  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Frameset//EN\" \"http://www.w3.org/TR/html4/frameset.dtd\">\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <title>UnlimitedFiles-Solicion Corporativa</title>\n");
      out.write("        <link href=\"bootstrap-3.3.4/css/bootstrap.css\" rel=\"stylesheet\">\n");
      out.write("        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js\"></script>\n");
      out.write("        <script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js\"></script>\n");
      out.write("\n");
      out.write("        <script>\n");
      out.write("            $(document).ready(function () {\n");
      out.write("                var option = 3;\n");
      out.write("                $.post('Usuarios', {\n");
      out.write("                    opt: option,\n");
      out.write("                }, function (responseText) {\n");
      out.write("                    $('#tabla_usuarios').html(responseText);\n");
      out.write("                });\n");
      out.write("            });\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("    <nav class=\"navbar navbar-inverse navbar-fixed-top\"> \n");
      out.write("        <div class=\"container-fluid\">\n");
      out.write("            <div class=\"navbar-header\"> \n");
      out.write("                <a class=\"navbar-brand\" href=\"#\"> \n");
      out.write("                    <img src=\"img/text3338.png\" style=\"width: 30px; height: 30px;\"/> \n");
      out.write("                </a> \n");
      out.write("                <ul class=\"nav navbar-nav\"> \n");
      out.write("                    <li> <a href=\"main.html\"><h5 style=\"color:#006400\">Home</h5></a></li>\n");
      out.write("                    <li><a href=\"http://getbootstrap.com/examples/starter-template/#\"><h5 style=\"color:#006400\">Productos</h5></a></li>\n");
      out.write("                    <li><a href=\"http://getbootstrap.com/examples/starter-template/#\"><h5 style=\"color:#006400\">Contactos</h5></a></li>\n");
      out.write("                </ul>                 \n");
      out.write("            </div> \n");
      out.write("        </div> \n");
      out.write("    </nav>\n");
      out.write("    <br/>\n");
      out.write("    <br/>\n");
      out.write("    <br/>\n");
      out.write("    <br/>\n");
      out.write("    <br/>\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"row\">\n");
      out.write("\n");
      out.write("            <center>\n");
      out.write("                <h4>UnlimitedFiles</h4>\n");
      out.write("                <h5>\n");
      out.write("                    Esta Seccion de Unlimited Files le permitira subir cualquier tipo de documento de una manera \n");
      out.write("                    muy segura para su organizacion solo debe darle click\n");
      out.write("                </h5>\n");
      out.write("            </center>\n");
      out.write("           \n");
      out.write("            <br/><br/>\n");
      out.write("            <div  id=\"tabla_usuarios\">\n");
      out.write("\n");
      out.write(" <center> \n");
      out.write("                <br/><br/><br/>\n");
      out.write("                <table>\n");
      out.write("                    ");

                        Mis_usuarios = new Usuario().Traer_Usuarios(Mis_usuarios);
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
                        control.cerrarConexion();
                    
      out.write("\n");
      out.write("                </table>\n");
      out.write("            </center>\n");
      out.write("                <script src=\"Jquery/jquery-1.11.2.js\"></script>\n");
      out.write("\n");
      out.write("<<<<<<< .mine\n");
      out.write("                <script> function borrar(codigo) {\n");
      out.write("                confirm(\"Seguro que desea borrar \");\n");
      out.write("            $(document).ready(function () {\n");
      out.write("                    var option = 4;                 $.post('Usuarios', {\n");
      out.write("=======\n");
      out.write("                    <script src=\"Jquery/jquery-1.11.2.js\"></script>\n");
      out.write("\n");
      out.write("                    <script>\n");
      out.write("            function borrar(codigo) {\n");
      out.write("                alert(\"Seguro que desea borrar \");\n");
      out.write("                $(document).ready(function () {\n");
      out.write("                    var option = 4;\n");
      out.write("                    $.post('Usuarios', {\n");
      out.write(">>>>>>> .r11\n");
      out.write("                        codigo_borrar: codigo,\n");
      out.write("                        opt: option,\n");
      out.write("                    }, function (responseText) {\n");
      out.write("                        $(\"#Prueb_usu\").html(responseText);\n");
      out.write("                        $(\"#tabla_usuarios\").load(\"Crud_usuarios.html\");\n");
      out.write("                });\n");
      out.write("                    });\n");
      out.write("        }\n");
      out.write("            function update(codigo) {\n");
      out.write("<<<<<<< .mine\n");
      out.write("            $(document).ready(function () {\n");
      out.write("                var option = 5;\n");
      out.write("=======\n");
      out.write("                $(document).ready(function () {\n");
      out.write("                    var option = 5;\n");
      out.write(">>>>>>> .r11\n");
      out.write("                    $.post('Usuarios', {\n");
      out.write("                    codigo_borrar: codigo,\n");
      out.write("                        opt: option,\n");
      out.write("<<<<<<< .mine\n");
      out.write("                        }, function (responseText) {\n");
      out.write("                    $(\"#tabla_usuarios\").hide();\n");
      out.write("=======\n");
      out.write("                    }, function (responseText) {\n");
      out.write("                        $(\"#tabla_usuarios\").hide();\n");
      out.write(">>>>>>> .r11\n");
      out.write("                        $(\"#Updat_usu\").html(responseText);\n");
      out.write("                        });\n");
      out.write("            });\n");
      out.write("        }</script>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
