package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Control.control;
import java.util.ArrayList;
import Modelo.Grupo;

public final class crud_005fGrupos_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

ArrayList<Grupo> Mis_grupos = new ArrayList();
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
      out.write("        <!-- <script>\n");
      out.write("             $(document).ready(function() {\n");
      out.write("                 var option = 3;\n");
      out.write("                 $.post('Usuarios', {\n");
      out.write("                     opt: option,\n");
      out.write("                 }, function(responseText) {\n");
      out.write("                     $('#tabla_usuarios').html(responseText);\n");
      out.write("                 });\n");
      out.write("             });</script>-->\n");
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
      out.write("            <center> \n");
      out.write("                <br/><br/><br/>\n");
      out.write("                <table>\n");
      out.write("                    ");

                        Grupo gr = new Grupo();
                        Mis_grupos=gr.cargar_grupos(Mis_grupos);
                        Grupo temp = null;
                        out.println("<tr>");
                        out.println("<td>Codigo</td>");
                        out.println("<td>Descripcion</td>");
                        out.println("</tr>");
                        for (int i = 0; i < Mis_grupos.size(); i++) {
                            temp = (Grupo) Mis_grupos.get(i);
                            out.println("<tr>");
                            out.println("<td>" + temp.getCodigo() + "</td>");
                            out.println("<td>" + temp.getNombre() + "</td>");
                            out.println("<td><input type=\"button\" class=\"btn btn-info\" value=\"Borrar\"  onclick=borrar_grup(" + temp.getCodigo() + ")></td> ");
                            out.println("<td> <a href=\"#modal2\"  class=\"btn btn-success\"   data-toggle=\"modal\""
                                    + " onclick=upd(" + temp.getCodigo() + ")>Update</a> </td>");
                            out.println("</tr>");
                        }
                        control.cerrarConexion();
                    
      out.write("\n");
      out.write("                    <script>\n");
      out.write("                        function borrar_grup(codigo) {\n");
      out.write("                            alert(\"Seguro que desea borrar \");\n");
      out.write("                            $(document).ready(function() {\n");
      out.write("                                var option = 1;\n");
      out.write("                                $.post('Grupos', {\n");
      out.write("                                    opt: option,\n");
      out.write("                                    codigo_borrar: codigo\n");
      out.write("                                }, function(responseText) {\n");
      out.write("                                    window.location.reload();\n");
      out.write("                                });\n");
      out.write("                            });\n");
      out.write("                        }\n");
      out.write("                        function upd(codigo) {\n");
      out.write("                            $(document).ready(function() {\n");
      out.write("                                var option = 3;\n");
      out.write("                                $.post('Grupos', {\n");
      out.write("                                    opt: option,\n");
      out.write("                                    codigo_update: codigo\n");
      out.write("                                }, function(responseText) {\n");
      out.write("                                    window.location.reload();\n");
      out.write("                                });\n");
      out.write("                            });\n");
      out.write("                        }\n");
      out.write("                    </script>\n");
      out.write("                </table>\n");
      out.write("\n");
      out.write("\n");
      out.write("                <a href=\"#modal1\" class=\"btn btn-success\"  data-toggle=\"modal\" >Agregar</a>\n");
      out.write("\n");
      out.write("            </center>\n");
      out.write("\n");
      out.write("            <div id=\"devolucion_grup\"></div>\n");
      out.write("\n");
      out.write("            <br/><br/>\n");
      out.write("            <div id=\"modal2\" class=\"modal fade bs-example-modal-lg\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myLargeModalLabel\" aria-hidden=\"true\">\n");
      out.write("                <div class=\"modal-dialog modal-lg\">\n");
      out.write("                    <div class=\"modal-content\">\n");
      out.write("                        <div class=\"modal-header\">\n");
      out.write("                            <center><h1> UPDATE GRUPOS</h1></center> \n");
      out.write("                        </div>\n");
      out.write("                        <!-- contenido modal-->\n");
      out.write("                        <div class=\"modal-body\">\n");
      out.write("                            <form>\n");
      out.write("                                <center>\n");
      out.write("\n");
      out.write("                                    ");
String nom = (String) session.getAttribute("descripcion_grupo");
                                      out.println("<h1>" + nom + "</h1>");
                                    
      out.write("\n");
      out.write("                                </center>\n");
      out.write("\n");
      out.write("                            </form>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"modal-footer\">\n");
      out.write("                            <button class=\"btn btn-primary\" data-dismiss=\"modal\">cerrar</button>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div id=\"modal1\" class=\"modal fade bs-example-modal-lg\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myLargeModalLabel\" aria-hidden=\"true\">\n");
      out.write("                <div class=\"modal-dialog modal-lg\">\n");
      out.write("                    <div class=\"modal-content\">\n");
      out.write("                        <div class=\"modal-header\">\n");
      out.write("                            <center><h1> CREAR GRUPOS</h1></center> \n");
      out.write("                        </div>\n");
      out.write("                        <!-- contenido modal-->\n");
      out.write("                        <div class=\"modal-body\">\n");
      out.write("                            <form>\n");
      out.write("                                <center>\n");
      out.write("                                    <input type=\"text\" id=\"descrip_grupo\">\n");
      out.write("                                    <br/>\n");
      out.write("                                    <input type=\"button\" value=\"Crear\" onclick=\"Crea()\">\n");
      out.write("                                </center>\n");
      out.write("                                <script>\n");
      out.write("                                    function Crea() {\n");
      out.write("                                        $(document).ready(function() {\n");
      out.write("                                            var option = 2;\n");
      out.write("                                            var desc = $(\"#descrip_grupo\").val();\n");
      out.write("                                            $.post('Grupos', {\n");
      out.write("                                                opt: option,\n");
      out.write("                                                des: desc,\n");
      out.write("                                            }, function(responseText) {\n");
      out.write("                                                window.location.reload();\n");
      out.write("                                            });\n");
      out.write("                                        });\n");
      out.write("                                    }\n");
      out.write("                                </script>\n");
      out.write("                            </form>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"modal-footer\">\n");
      out.write("                            <button class=\"btn btn-primary\" data-dismiss=\"modal\">cerrar</button>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("    <script src=\"Jquery/jquery-1.11.2.js\"></script>\n");
      out.write("\n");
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
