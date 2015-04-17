<%-- 
    Document   : main
    Created on : 17/04/2015, 11:42:26 AM
    Author     : usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<xhtml xmlns="http://www.w3.org/1999/xhtml"
       xmlns:h="http://xmlns.jcp.org/jsf/html">

    <h:head>
        <h:meta charset="utf-8"></h:meta>
        <h:title>Unlimited Files</h:title>
        <h:meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <!-- css -->
        <link href="css/bootstrap.min.css" rel="stylesheet" />
        <link  href = "../bootstrap-3.3.4/css/bootstrap.css"  rel = "stylesheet" ></link>
        <script src="../Jquery/jquery-1.11.2.js"></script>
    </h:head>
    <h:body>
        <nav  class = "navbar navbar-inverse navbar-fixed-top" > 
            <div  class= "container-fluid" >
                <div  class= "navbar-header" > 
                    <a  class= "navbar-brand"  href= "#" > 
                        <img src= "../img/text3338.png" style="width: 27px; height: 27px;"></img> 
                    </a> 
                    <ul class="nav navbar-nav"> 
                        <li> <a  href="../inicio.html"><h4 style="color:#006400">Home</h4></a></li>
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
                <h1>GESTION DE ADMINISTRADOR</h1>
                <!--<div class="col-xs-6 col-md-4">
                    <a href="gestionUsuariosAdmon.html" class="thumbnail" >
                        <img src="img/slides/user197.png" style="height: 100px; width: 100px" />
                    </a>
                    <div class="caption">
                        <center> <h3>Gestion de usuarios</h3></center>
                    </div>
                </div>-->

                <div class="col-xs-6 col-md-4">
                    <a href="../Crud_roles.html" class="thumbnail">
                        <img src="../img/slides/network11.png" style="height: 100px; width: 100px" />
                    </a>
                    <div class="caption">
                        <center>  <h3>Gestion de  roles</h3></center>

                    </div>
                </div>

                <div class="col-xs-6 col-md-4">
                    <a href="../crud_Grupos.jsp" class="thumbnail">
                        <img src="../img/slides/group9.png" style="height: 100px ; width: 100px" />
                    </a>
                    <div class="caption">
                        <center>  <h3>Gestion de  grupos de trabajo</h3></center>
                    </div>                   
                </div>
                <div class="col-xs-6 col-md-4">
                    <a href="../Crud_usuario_admon.jsp" class="thumbnail">
                        <img src="../img/slides/group9.png" style="height: 100px ; width: 100px" />
                    </a>
                    <div class="caption">
                        <center>  <h3>Gestion de  Usuarios</h3></center>
                    </div>                   
                </div>
            </div>

            <div class="row">
                <br></br>
                <h1>GESTION OPERARIA</h1>
                <div class="col-xs-6 col-md-4">
                    <a href="../Subir_Documento_2.html" class="thumbnail" >
                        <img src="../img/slides/suitcase6.png" style="height: 100px ; width: 100px" />
                    </a>
                    <div class="caption">
                        <center> <h3>Subir archivos</h3></center>
                    </div>
                </div>
              
                <div class="col-xs-6 col-md-4">
                    <a href="../MisGrupos.html" class="thumbnail">
                        <img src="../img/slides/manager3 .png" style="height: 100px ; width: 100px" />
                    </a>
                    <div class="caption">
                        <center>  <h3>Mis grupos</h3></center>

                    </div>
                </div>
                <div class="col-xs-6 col-md-4">
                    <a href="../MisDatos.jsp" class="thumbnail">
                        <img src="../img/slides/manager3 .png" style="height: 100px ; width: 100px" />
                    </a>
                    <div class="caption">
                        <center>  <h3>Mis Datos</h3></center>

                    </div>
                </div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script src = "../bootstrap-3.3.4/js/bootstrap.min.js" ></script>
    </h:body>
</xhtml>

