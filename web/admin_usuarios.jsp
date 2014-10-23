<%@page import="java.util.List"%>
<%@page import="Controladora.CUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="proc/error.jsp" %>
<%@taglib prefix="lg" uri="/WEB-INF/tlds/logincheck.tld"%>
<lg:logchk admin="true"/>
<%
    CUsuario cUsr = new CUsuario();
    List<Usuario> usuarios = cUsr.getAll();
    pageContext.setAttribute("usuarios", usuarios, PageContext.PAGE_SCOPE);
    pageContext.setAttribute("admin", "Administrador", PageContext.PAGE_SCOPE);
%>
<html>
    <head>
        <%@ include file="menu.jsp"%> 
        <style>
            .button {
                background:none!important;
                border:none; 
                padding:0!important;
                /*border is optional*/
                border-bottom:1px solid #444; 
                color: blue;
                border-bottom-color: blue;
            }
        </style>
    </head>
    <body>
        <br><br>
        <div class="container">
            <div class="well col-sm-12 col-md-8 col-md-offset-2">
                <c:forEach items="${usuarios}" var="usr">
                    <div class="row user-row">
                        <div class="col-md-1">
                            <img class="img-circle"
                                 src="Imgs/user50.png"
                                 alt="User Pic">
                        </div>
                        <div class="col-md-10">
                            <strong>${usr.username} <c:if test="${!usr.isActivo()}">(inactivo)</c:if></strong><br>
                            <span class="text-muted">Tipo de usuario: <c:choose> <c:when test="${usr.getClass().getSimpleName().equals(admin)}">Administrador</c:when><c:otherwise>Cliente</c:otherwise></c:choose></span>
                                </div>
                                <!-- add .rowname class to attribute data-for (Dropdown!)-->
                                    <div class="col-md-1 dropdown-user" data-for=".${usr.username}">
                            <i class="glyphicon glyphicon-chevron-down text-muted"></i>
                        </div>
                    </div>

                    <!-- Add data-for class to this row -->
                    <div class="row user-infos ${usr.username}">
                        <div class="col-sm-10 col-md-10 col-md-offset-1">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Información del usuario</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-3">
                                            <img class="img-circle"
                                                 src="Imgs/user100.png"
                                                 alt="User Pic">
                                        </div>
                                        <div class="col-md-6">
                                            <strong>${usr.username}</strong><br>
                                            <table class="table table-condensed table-responsive table-user-information">
                                                <tbody>
                                                    <tr>
                                                        <td>Nombre:</td>
                                                        <td>${usr.nombre}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Apellido:</td>
                                                        <td>${usr.apellido}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>E-mail:</td>
                                                        <td>${usr.email}</td>
                                                    </tr>
                                                    <!-- Info específica del cliente -->
                                                    <c:if test="${!usr.getClass().getSimpleName().equals(admin)}">
                                                        <tr>
                                                            <td>DNI:</td>
                                                            <td>${usr.dni}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Teléfono:</td>
                                                            <td>${usr.telefono}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Dirección:</td>
                                                            <td>${usr.direccion}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Compras:</td>                                                        
                                                    <form method="post" action="info_usuario.jsp">
                                                        <input type="hidden" name="id" value="${usr.id}"/>
                                                        <td><button type="submit" class="button" formaction="compras.jsp">Ver</button></td>
                                                        </tr>
                                                    </c:if>
                                                    </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-footer">
                                    <button class="btn btn-sm btn-primary" type="button"
                                            data-toggle="tooltip"
                                            data-original-title="Enviar mensaje a usuario"><i class="glyphicon glyphicon-envelope"></i></button>
                                    <span class="pull-right">
                                        <button class="btn btn-sm btn-warning" type="submit"
                                                data-toggle="tooltip"
                                                data-original-title="Editar usuario"><i class="glyphicon glyphicon-edit"></i></button>
                                    </span>
                                </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div><!-- /.container -->


            <!-- Bootstrap core JavaScript
            ================================================== -->
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="dist/js/userTable.js"></script>
    </body>
</html>
