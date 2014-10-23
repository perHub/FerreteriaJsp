<%-- 
    Document   : alta_usuario
    Created on : 09/10/2014, 19:04:56
    Author     : Peri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="lg" uri="/WEB-INF/tlds/logincheck.tld"%>
<lg:logchk admin="true"/>
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
                <form class="form-horizontal">
                    <div class="control-group">
                        <label class="control-label" for="nombre">Nombre</label>
                        <div class="controls">
                            <input id="nombre" placeholder="Ej: Pedro" type="text" required>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="apellido">Apellido</label>
                        <div class="controls">
                            <input id="apellido" placeholder="Ej: Martínez" type="text" required>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="email">Email</label>

                        <div class="controls">
                            <input id="email" placeholder="Ej: testing@mail.com" type="text" required>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="username">Username</label>

                        <div class="controls">
                            <input id="username" placeholder="Ej: user123" type="text" required>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="password">Password</label>

                        <div class="controls">
                            <input id="password" placeholder="Min. 8 caracteres" type="password" pattern=".{8,}" required>
                        </div>
                    </div>
                    
                    <!--Datos especìficos del cliente.-->
                    
                    <div class="control-group">
                        <label class="control-label" for="direccion">Dirección</label>

                        <div class="controls">
                            <input id="direccion" placeholder="Ej: Calle 123" type="text" required>
                        </div>
                    </div>
                    
                    <div class="control-group">
                        <label class="control-label" for="telefono">Teléfono</label>

                        <div class="controls">
                            <input id="telefono" placeholder="Ej: 4801111" type="text" required>
                        </div>
                    </div>
                    
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
