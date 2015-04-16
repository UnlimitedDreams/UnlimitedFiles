<%-- 
    Document   : Prueba_modal
    Created on : 16/04/2015, 10:34:55 AM
    Author     : USR_Toshiba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<xhtml xmlns="http://www.w3.org/1999/xhtml"
       xmlns:h="http://xmlns.jcp.org/jsf/html">

    <h:head>
        <title>Unlimited Files</title>

        <!-- css -->
        <link href="css/bootstrap.min.css" rel="stylesheet" />
        <link  href = "bootstrap-3.3.4/css/bootstrap.css"  rel = "stylesheet" ></link>
    </h:head>

    <h:body>

        <nav  class = "navbar navbar-inverse navbar-fixed-top" > 
            <div  class= "container-fluid" >
                <div  class= "navbar-header" > 
                    <a  class= "navbar-brand"  href= "#" > 
                        <img src= "img/text3338.png" style="width: 27px; height: 27px;"></img> 
                    </a> 
                    <ul class="nav navbar-nav"> 
                        <li> <a  href="https://www.facebook.com/sebazz.giraldo"><h4 style="color:#006400">Home</h4></a></li>
                        <li><a href="http://getbootstrap.com/examples/starter-template/#"><h4 style="color:#006400">Productos</h4></a></li>
                        <li><a href="http://getbootstrap.com/examples/starter-template/#"><h4 style="color:#006400">Contactos</h4></a></li>
                    </ul>                 
                </div> 
            </div> 
        </nav>


        <div class="jumbotron3" >
        </div>
        <div class="jumbotron2" >
            <div class="container">

                <ul class="breadcrumb2">
                    <center><h4>Administrador</h4> </center> 
                </ul></div>
        </div>

        <br></br>

        <div class="container">
            <div class="row">
                <br></br>
                <center><h1>SUBIR ARCHIVO</h1>
                    <h4>Esta Seccion de Unlimited Files le permitira subir cualquier tipo de documento de una manera 
                        muy segura para su organizacion solo debe darle click </h4>
                    <br></br>
                    <form>
                        <table>
                            <tr>
                                <td>
                                    <h4>Nombre</h4> 
                                </td>

                                <td>
                                    <input type="text"  class="form-control" style="width: 100%;" name="nomArchivo"> </input>
                                </td>

                            </tr>
                            <tr>
                                <td>
                                    <h4>Descripcion</h4> 
                                </td>
                                <td>
                                    <input type="text"  class="form-control" style="width: 100%;" name="descripcion"> </input>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <h4>Grupos</h4> 
                                </td>
                                <td>
                                    <select id="Gru" class="form-control" ></select>
                                </td>
                            </tr>
                        </table> 
                        <br></br>
                        <a href="#modal1"  class="btn btn-success"  data-toggle="modal" onclick="mn(67)">Agregar</a> 
                    </form></center>
                <script>

                    function mn(codigo) {
                        $(document).ready(function() {
                            var option = 7;
                            $.post('Usuarios', {
                                opt: option,
                                codi: codigo,
                            }, function(responseText) {
                            });
                        });
                    }
                </script>
                <div id="modal1" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <center><h1>SUBIR ARCHIVOS</h1></center> 
                            </div>
                            <!-- contenido modal-->
                            <div class="modal-body">
                                <center>
                                    <input type="text" name="DNI" id="DNI" value=""/>

                                    <%
                                        String nom = (String) session.getAttribute("Usuario_nombre");
                                        String ape = (String) session.getAttribute("Usuario_Apellido");

                                        out.println("<h1>" + nom + "</h1>");
                                        out.println("<h1>" + ape + "</h1>");

                                    %>
                                </center>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-primary" data-dismiss="modal">cerrar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script src="Jquery/jquery-1.11.2.js"></script>

    </h:body>
</xhtml>
