<%-- 
    Document   : alta_usuario
    Created on : 09/10/2014, 19:04:56
    Author     : Peri
--%>

<%@page import="Controladora.CUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="lg" uri="/WEB-INF/tlds/logincheck.tld"%>
<lg:logchk admin="true"/>
<jsp:useBean id="usr0" class="Modelo.Cliente" scope="request"/>
<jsp:setProperty name="usr0" property="id"/>

<%
    if (usr0.getId() != 0) {
        CUsuario cUsr = new CUsuario();     //Lo hago de esta forma porque es mas sencillo debido a la cantidad de atributos de Cliente
        Usuario usuario =  cUsr.read(usr0.getId());
        pageContext.setAttribute("usr0", usuario, PageContext.REQUEST_SCOPE);
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alta de usuario</title>
        <%@ include file="menu.jsp"%> 
        <style>
            .span6 {
                width: 300px;
            }
        </style>
    </head>
    <body>
        <div class="span6 center-block">
            <h1>Nuevo cliente</h1>
            <div class="area">
                <form method="post" action="proc/updusr.jsp" class="form-horizontal">
                    <div class="control-group">
                        <label class="control-label" for="nombre">Nombre</label>
                        <div class="controls">
                            <input name="nombre" placeholder="Ej: Pedro" type="text" value="<c:if test="${usr0.nombre != null}">${usr0.nombre}</c:if>" required>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="apellido">Apellido</label>
                            <div class="controls">
                                <input name="apellido" placeholder="Ej: Martínez" type="text" value="<c:if test="${usr0.apellido != null}">${usr0.apellido}</c:if>" required>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="email">Email</label>

                            <div class="controls">
                                <input name="email" placeholder="Ej: testing@mail.com" type="text" value="<c:if test="${usr0.email != null}">${usr0.email}</c:if>" required>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="username">Username</label>

                            <div class="controls">
                                <input name="username" placeholder="Ej: user123" type="text" value="<c:if test="${usr0.username != null}">${usr0.username}</c:if>" required>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="password">Password</label>

                            <div class="controls">
                                <input name="password" placeholder="Min. 8 caracteres" type="password" pattern=".{8,}"  value="<c:if test="${usr0.password != null}">${usr0.password}</c:if>" required>
                            </div>
                        </div>

                        <!--Datos especìficos del cliente.-->

                        <div class="control-group">
                            <label class="control-label" for="dni">DNI</label>

                            <div class="controls">
                                <input name="dni" placeholder="Ej: 11222333" type="text" pattern="\d+" value="<c:if test="${usr0.dni != null}">${usr0.dni}</c:if>" required>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="direccion">Dirección</label>

                            <div class="controls">
                                <input name="direccion" placeholder="Ej: Calle 123" type="text" value="<c:if test="${usr0.direccion != null}">${usr0.direccion}</c:if>" required>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="telefono">Teléfono</label>

                            <div class="controls">
                                <input name="telefono" placeholder="Ej: 4801111" type="text" value="<c:if test="${usr0.telefono != null}">${usr0.telefono}</c:if>" required>
                            </div>
                        </div>
                        <input type="hidden" name="id" value="${usr0.id}"/>
                    <input type='hidden' name='value' value='${usr0.id == 0 ? "true" : "false"}'/>
                    <label><input type="checkbox" name="activo" <c:if test="${usr0.id == 0 || (usr0.isActivo() && usr0.id != 0)}">checked</c:if>> Activo</label>
                    <div class="control-group">
                        <div class="controls"><br>
                            <button class="btn btn-success" type="submit">Crear/Actualizar</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
