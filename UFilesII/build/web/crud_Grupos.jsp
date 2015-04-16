<%-- 
    Document   : prueba
    Created on : 15/04/2015, 05:25:28 PM
    Author     : USR_Toshiba
--%>

<%@page import="Control.control"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Grupo"%>
<%!ArrayList<Grupo> Mis_grupos = new ArrayList();%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <title>UnlimitedFiles-Solicion Corporativa</title>
        <link href="bootstrap-3.3.4/css/bootstrap.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

        <!-- <script>
             $(document).ready(function() {
                 var option = 3;
                 $.post('Usuarios', {
                     opt: option,
                 }, function(responseText) {
                     $('#tabla_usuarios').html(responseText);
                 });
             });</script>-->
    </head>

    <body>
    <nav class="navbar navbar-inverse navbar-fixed-top"> 
        <div class="container-fluid">
            <div class="navbar-header"> 
                <a class="navbar-brand" href="#"> 
                    <img src="img/text3338.png" style="width: 30px; height: 30px;"/> 
                </a> 
                <ul class="nav navbar-nav"> 
                    <li> <a href="main.html"><h5 style="color:#006400">Home</h5></a></li>
                    <li><a href="http://getbootstrap.com/examples/starter-template/#"><h5 style="color:#006400">Productos</h5></a></li>
                    <li><a href="http://getbootstrap.com/examples/starter-template/#"><h5 style="color:#006400">Contactos</h5></a></li>
                </ul>                 
            </div> 
        </div> 
    </nav>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <div class="container">
        <div class="row">

            <center>
                <h4>UnlimitedFiles</h4>
                <h5>
                    Esta Seccion de Unlimited Files le permitira subir cualquier tipo de documento de una manera 
                    muy segura para su organizacion solo debe darle click
                </h5>
            </center>
            <center> 
                <br/><br/><br/>
                <table>
                    <%
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
                    %>
                    <script>
                        function borrar_grup(codigo) {
                            alert("Seguro que desea borrar ");
                            $(document).ready(function() {
                                var option = 1;
                                $.post('Grupos', {
                                    opt: option,
                                    codigo_borrar: codigo
                                }, function(responseText) {
                                    window.location.reload();
                                });
                            });
                        }
                        function upd(codigo) {
                            $(document).ready(function() {
                                var option = 3;
                                $.post('Grupos', {
                                    opt: option,
                                    codigo_update: codigo
                                }, function(responseText) {
                                    window.location.reload();
                                });
                            });
                        }
                    </script>
                </table>


                <a href="#modal1" class="btn btn-success"  data-toggle="modal" >Agregar</a>

            </center>

            <div id="devolucion_grup"></div>

            <br/><br/>
            <div id="modal2" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <center><h1> UPDATE GRUPOS</h1></center> 
                        </div>
                        <!-- contenido modal-->
                        <div class="modal-body">
                            <form>
                                <center>

                                    <%String nom = (String) session.getAttribute("descripcion_grupo");
                                      out.println("<h1>" + nom + "</h1>");
                                    %>
                                </center>

                            </form>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-primary" data-dismiss="modal">cerrar</button>
                        </div>
                    </div>
                </div>
            </div>
            <div id="modal1" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <center><h1> CREAR GRUPOS</h1></center> 
                        </div>
                        <!-- contenido modal-->
                        <div class="modal-body">
                            <form>
                                <center>
                                    <input type="text" id="descrip_grupo">
                                    <br/>
                                    <input type="button" value="Crear" onclick="Crea()">
                                </center>
                                <script>
                                    function Crea() {
                                        $(document).ready(function() {
                                            var option = 2;
                                            var desc = $("#descrip_grupo").val();
                                            $.post('Grupos', {
                                                opt: option,
                                                des: desc,
                                            }, function(responseText) {
                                                window.location.reload();
                                            });
                                        });
                                    }
                                </script>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-primary" data-dismiss="modal">cerrar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>


    </div>
    <script src="Jquery/jquery-1.11.2.js"></script>

</body>
</html>
