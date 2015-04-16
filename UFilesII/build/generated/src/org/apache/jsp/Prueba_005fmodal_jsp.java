package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Prueba_005fmodal_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

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
      out.write("<?xml version='1.0' encoding='UTF-8' ?>\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
      out.write("<xhtml xmlns=\"http://www.w3.org/1999/xhtml\"\n");
      out.write("       xmlns:h=\"http://xmlns.jcp.org/jsf/html\">\n");
      out.write("\n");
      out.write("    <h:head>\n");
      out.write("        <title>Unlimited Files</title>\n");
      out.write("\n");
      out.write("        <!-- css -->\n");
      out.write("        <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\" />\n");
      out.write("        <link  href = \"bootstrap-3.3.4/css/bootstrap.css\"  rel = \"stylesheet\" ></link>\n");
      out.write("    </h:head>\n");
      out.write("\n");
      out.write("    <h:body>\n");
      out.write("\n");
      out.write("        <nav  class = \"navbar navbar-inverse navbar-fixed-top\" > \n");
      out.write("            <div  class= \"container-fluid\" >\n");
      out.write("                <div  class= \"navbar-header\" > \n");
      out.write("                    <a  class= \"navbar-brand\"  href= \"#\" > \n");
      out.write("                        <img src= \"img/text3338.png\" style=\"width: 27px; height: 27px;\"></img> \n");
      out.write("                    </a> \n");
      out.write("                    <ul class=\"nav navbar-nav\"> \n");
      out.write("                        <li> <a  href=\"https://www.facebook.com/sebazz.giraldo\"><h4 style=\"color:#006400\">Home</h4></a></li>\n");
      out.write("                        <li><a href=\"http://getbootstrap.com/examples/starter-template/#\"><h4 style=\"color:#006400\">Productos</h4></a></li>\n");
      out.write("                        <li><a href=\"http://getbootstrap.com/examples/starter-template/#\"><h4 style=\"color:#006400\">Contactos</h4></a></li>\n");
      out.write("                    </ul>                 \n");
      out.write("                </div> \n");
      out.write("            </div> \n");
      out.write("        </nav>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"jumbotron3\" >\n");
      out.write("        </div>\n");
      out.write("        <div class=\"jumbotron2\" >\n");
      out.write("            <div class=\"container\">\n");
      out.write("\n");
      out.write("                <ul class=\"breadcrumb2\">\n");
      out.write("                    <center><h4>Administrador</h4> </center> \n");
      out.write("                </ul></div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <br></br>\n");
      out.write("\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <br></br>\n");
      out.write("                <center><h1>SUBIR ARCHIVO</h1>\n");
      out.write("                    <h4>Esta Seccion de Unlimited Files le permitira subir cualquier tipo de documento de una manera \n");
      out.write("                        muy segura para su organizacion solo debe darle click </h4>\n");
      out.write("                    <br></br>\n");
      out.write("                    <form>\n");
      out.write("                        <table>\n");
      out.write("                            <tr>\n");
      out.write("                                <td>\n");
      out.write("                                    <h4>Nombre</h4> \n");
      out.write("                                </td>\n");
      out.write("\n");
      out.write("                                <td>\n");
      out.write("                                    <input type=\"text\"  class=\"form-control\" style=\"width: 100%;\" name=\"nomArchivo\"> </input>\n");
      out.write("                                </td>\n");
      out.write("\n");
      out.write("                            </tr>\n");
      out.write("                            <tr>\n");
      out.write("                                <td>\n");
      out.write("                                    <h4>Descripcion</h4> \n");
      out.write("                                </td>\n");
      out.write("                                <td>\n");
      out.write("                                    <input type=\"text\"  class=\"form-control\" style=\"width: 100%;\" name=\"descripcion\"> </input>\n");
      out.write("                                </td>\n");
      out.write("                            </tr>\n");
      out.write("                            <tr>\n");
      out.write("                                <td>\n");
      out.write("                                    <h4>Grupos</h4> \n");
      out.write("                                </td>\n");
      out.write("                                <td>\n");
      out.write("                                    <select id=\"Gru\" class=\"form-control\" ></select>\n");
      out.write("                                </td>\n");
      out.write("                            </tr>\n");
      out.write("                        </table> \n");
      out.write("                        <br></br>\n");
      out.write("                        <a href=\"#modal1\"  class=\"btn btn-success\"  data-toggle=\"modal\" onclick=\"mn(67)\">Agregar</a> \n");
      out.write("                    </form></center>\n");
      out.write("                <script>\n");
      out.write("\n");
      out.write("                    function mn(codigo) {\n");
      out.write("                        $(document).ready(function() {\n");
      out.write("                            var option = 7;\n");
      out.write("                            $.post('Usuarios', {\n");
      out.write("                                opt: option,\n");
      out.write("                                codi: codigo,\n");
      out.write("                            }, function(responseText) {\n");
      out.write("                            });\n");
      out.write("                        });\n");
      out.write("                    }\n");
      out.write("                </script>\n");
      out.write("                <div id=\"modal1\" class=\"modal fade bs-example-modal-lg\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myLargeModalLabel\" aria-hidden=\"true\">\n");
      out.write("                    <div class=\"modal-dialog modal-lg\">\n");
      out.write("                        <div class=\"modal-content\">\n");
      out.write("                            <div class=\"modal-header\">\n");
      out.write("                                <center><h1>SUBIR ARCHIVOS</h1></center> \n");
      out.write("                            </div>\n");
      out.write("                            <!-- contenido modal-->\n");
      out.write("                            <div class=\"modal-body\">\n");
      out.write("                                <center>\n");
      out.write("                                    <input type=\"text\" name=\"DNI\" id=\"DNI\" value=\"\"/>\n");
      out.write("\n");
      out.write("                                    ");

                                        String nom = (String) session.getAttribute("Usuario_nombre");
                                        String ape = (String) session.getAttribute("Usuario_Apellido");

                                        out.println("<h1>" + nom + "</h1>");
                                        out.println("<h1>" + ape + "</h1>");

                                    
      out.write("\n");
      out.write("                                </center>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"modal-footer\">\n");
      out.write("                                <button class=\"btn btn-primary\" data-dismiss=\"modal\">cerrar</button>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js\"></script>\n");
      out.write("        <script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js\"></script>\n");
      out.write("        <script src=\"Jquery/jquery-1.11.2.js\"></script>\n");
      out.write("\n");
      out.write("    </h:body>\n");
      out.write("</xhtml>\n");
      out.write("\n");
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
