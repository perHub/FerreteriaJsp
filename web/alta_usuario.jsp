<%-- 
    Document   : alta_usuario
    Created on : 09/10/2014, 19:04:56
    Author     : Peri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                            <input id="nombre" placeholder="Ej: Pedro" type="text">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="apellido">Apellido</label>
                        <div class="controls">
                            <input id="apellido" placeholder="Ej: Martínez" type="text">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="inputLast">Last</label>
                        <div class="controls">
                            <input id="inputLast" placeholder="E.g. Hegde" type="text">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="inputEmail">Email</label>

                        <div class="controls">
                            <input id="inputEmail" placeholder="E.g. ashwinh@cybage.com" type="text">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="inputUser">Username</label>

                        <div class="controls">
                            <input id="inputUser" placeholder="E.g. ashwinhegde" type="text">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="inputPassword">Password</label>

                        <div class="controls">
                            <input id="inputPassword" placeholder="Min. 8 Characters" type="password">
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
